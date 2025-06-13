package com.lab.transaction_service.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MensagemNotificacaoTransacao {
    private String idUsuario;
    private String emailUsuario;
    private String telefoneUsuario;
    private BigDecimal valor;
    private String descricao;
    private String tipoTransacao;
} 