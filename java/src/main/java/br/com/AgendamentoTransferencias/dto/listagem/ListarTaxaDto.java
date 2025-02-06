package br.com.AgendamentoTransferencias.dto.listagem;

import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO para listar as taxas atuais
 */
@Data
public class ListarTaxaDto {
    private Integer id;

    private int intervaloInicio;

    private int intervaloFim;

    private BigDecimal valorBase;

    private BigDecimal taxa;
}
