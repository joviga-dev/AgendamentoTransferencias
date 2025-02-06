package br.com.AgendamentoTransferencias.dto.cadastro;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para receber agendamento de transferências
 */
@Data
public class AgendarTransferenciaDto {
    @NotBlank
    private String contaOrigem;

    @NotBlank
    private String contaDestino;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAgendamento;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataTransferencia;

    @NotNull
    private BigDecimal valorFinal;

    @NotNull
    private BigDecimal valorTransferencia;

    @NotNull
    private BigDecimal valorBase;

    @NotNull
    private BigDecimal taxa;
}
