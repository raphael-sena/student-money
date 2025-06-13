package com.lab.transaction_service.mapper;

import com.lab.transaction_service.dto.TransacaoDTO;
import com.lab.transaction_service.model.Transacao;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {
    
    public TransacaoDTO paraDTO(Transacao transacao) {
        if (transacao == null) {
            return null;
        }

        TransacaoDTO dto = new TransacaoDTO();
        dto.setId(transacao.getId());
        dto.setIdRemetente(transacao.getIdRemetente());
        dto.setIdDestinatario(transacao.getIdDestinatario());
        dto.setValor(transacao.getValor());
        dto.setDescricao(transacao.getDescricao());
        dto.setTipo(transacao.getTipo());
        dto.setDataHora(transacao.getDataHora());
        
        return dto;
    }
} 