package br.com.AgendamentoTransferencias.controller;

import br.com.AgendamentoTransferencias.dto.cadastro.AgendarTransferenciaDto;
import br.com.AgendamentoTransferencias.dto.listagem.ExtratoTransferenciasDto;
import br.com.AgendamentoTransferencias.exception.EntidadeNaoEncontradaException;
import br.com.AgendamentoTransferencias.exception.SaldoInsuficienteException;
import br.com.AgendamentoTransferencias.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

/**
 * Classe que recebe as requisições HTTP para agendamento de transferências
 */
@RestController
@RequestMapping("/api/agendamento")
public class AgendamentoController {

    @Autowired
    public AgendamentoService agendamentoService;

    @Operation(summary = "Rota para agendamento de transferências")
    @PostMapping("/agendar-transferencia")
    private void agendarTransferencia(@RequestBody @Valid AgendarTransferenciaDto dto) throws SaldoInsuficienteException, EntidadeNaoEncontradaException {
        this.agendamentoService.agendar(dto);
    }

    @Operation(summary = "Rota que lista os agendamentos de uma conta")
    @GetMapping("/conta-origem/{contaOrigem}/listar")
    private List<ExtratoTransferenciasDto> listarAgendamentos(@PathVariable String contaOrigem){
        return this.agendamentoService.listar(contaOrigem);
    }
}
