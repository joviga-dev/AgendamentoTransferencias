package br.com.AgendamentoTransferencias.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "agendamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @NotNull
    private String contaOrigem;

    @NotNull
    private String contaDestino;

    @NotNull
    private LocalDate dataAgendamento;

    @NotNull
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
