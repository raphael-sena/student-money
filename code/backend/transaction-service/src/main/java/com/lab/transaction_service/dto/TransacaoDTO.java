package com.lab.transaction_service.dto;

import com.lab.transaction_service.model.TipoTransacao;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransacaoDTO {
    private Long id;
    private String idRemetente;
    private String idDestinatario;
    private BigDecimal valor;
    private String descricao;
    private TipoTransacao tipo;
    private LocalDateTime dataHora;
} 