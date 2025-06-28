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
    public Transacao enviarMoedas(String idRemetente, String idDestinatario, BigDecimal valor, String descricao) {
        // Validação do saldo do remetente (normalmente envolveria chamada a outro serviço)
        // Por enquanto, assumimos que a verificação é feita pelo chamador

        Transacao transacao = new Transacao();
        transacao.setIdRemetente(idRemetente);
        transacao.setIdDestinatario(idDestinatario);
        transacao.setValor(valor);
        transacao.setDescricao(descricao);
        transacao.setTipo(TipoTransacao.PROFESSOR_PARA_ALUNO);

        Transacao transacaoSalva = transacaoRepository.save(transacao);

        // Enviar notificação
        enviarNotificacaoTransacao(transacaoSalva);

        return transacaoSalva;
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