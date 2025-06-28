package com.lab.transaction_service.service;

import com.lab.transaction_service.model.Transacao;
import com.lab.transaction_service.model.TipoTransacao;
import com.lab.transaction_service.model.TransactionNotificationMessage;
import com.lab.transaction_service.repository.TransacaoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.transacao}")
    private String exchangeTransacao;

    @Value("${rabbitmq.routingkey.notificacao-transacao}")
    private String routingKeyNotificacao;

    public TransacaoService(TransacaoRepository transacaoRepository, RabbitTemplate rabbitTemplate) {
        this.transacaoRepository = transacaoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public Transacao sendCoins(String senderId, String recipientId, BigDecimal amount, String description) {
        validateTransactionAmount(amount);
        Transacao transacao = new Transacao();
        transacao.setIdRemetente(senderId);
        transacao.setIdDestinatario(recipientId);
        transacao.setValor(amount);
        transacao.setDescricao(description);
        transacao.setTipo(TipoTransacao.PROFESSOR_PARA_ALUNO);
        Transacao transacaoSalva = transacaoRepository.save(transacao);
        enviarNotificacaoTransacao(transacaoSalva);
        return transacaoSalva;
    }

    private void validateTransactionAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }

    public List<Transacao> obterHistoricoTransacoes(String idUsuario) {
        List<Transacao> transacoesEnviadas = transacaoRepository.findByIdRemetenteOrderByDataHoraDesc(idUsuario);
        List<Transacao> transacoesRecebidas = transacaoRepository.findByIdDestinatarioOrderByDataHoraDesc(idUsuario);
        
        // Combinar e ordenar transações
        transacoesEnviadas.addAll(transacoesRecebidas);
        transacoesEnviadas.sort((t1, t2) -> t2.getDataHora().compareTo(t1.getDataHora()));
        
        return transacoesEnviadas;
    }

    public BigDecimal obterSaldo(String idUsuario) {
        List<Transacao> transacoes = obterHistoricoTransacoes(idUsuario);
        return transacoes.stream()
                .map(t -> {
                    if (t.getIdRemetente().equals(idUsuario)) {
                        return t.getValor().negate();
                    } else {
                        return t.getValor();
                    }
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void enviarNotificacaoTransacao(Transacao transacao) {
        // Criar mensagem de notificação
        TransactionNotificationMessage mensagem = new TransactionNotificationMessage();
        mensagem.setUserId(transacao.getIdDestinatario());
        mensagem.setAmount(transacao.getValor());
        mensagem.setDescription(transacao.getDescricao());
        mensagem.setTransactionType(transacao.getTipo().name());

        // Enviar para o serviço de notificação
        rabbitTemplate.convertAndSend(exchangeTransacao, routingKeyNotificacao, mensagem);
    }
} 