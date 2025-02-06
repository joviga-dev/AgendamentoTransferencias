package br.com.AgendamentoTransferencias.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO para aplicar uma taxa sobre um valor
 */
@Data
public class AplicarTaxaInput {
    @NotBlank
    private String intervaloInicio;
    @NotBlank
    private String intervaloFim;
    @NotNull
    private BigDecimal valorTransferencia;
}
