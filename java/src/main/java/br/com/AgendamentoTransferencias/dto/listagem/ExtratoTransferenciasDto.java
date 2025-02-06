package br.com.AgendamentoTransferencias.dto.listagem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO para retornar extrato das transferências
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoTransferenciasDto {
    private Integer id;

    private String contaOrigem;

    private String contaDestino;

    private String dataAgendamento;

    private String dataTransferencia;

    private String valorFinal;

    private String valorBase;

    private String valorTransferencia;

    private String taxa;
}
