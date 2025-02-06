package br.com.AgendamentoTransferencias.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Taxa {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private int intervaloInicio;

    private int intervaloFim;

    private BigDecimal valorBase;

    private BigDecimal taxa;
}
