package br.com.AgendamentoTransferencias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO que representa uma taxa aplicada
 */
@Data
@AllArgsConstructor
public class TaxaAplicadaDto {
    private BigDecimal valorBase;
    private BigDecimal taxa;
    private BigDecimal valorFinal;
}
