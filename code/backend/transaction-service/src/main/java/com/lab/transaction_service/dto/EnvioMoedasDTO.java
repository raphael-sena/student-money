package com.lab.transaction_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class EnvioMoedasDTO {
    @NotBlank(message = "O ID do remetente é obrigatório")
    private String idRemetente;

    @NotBlank(message = "O ID do destinatário é obrigatório")
    private String idDestinatario;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;
} 