package br.com.AgendamentoTransferencias.controller;


import br.com.AgendamentoTransferencias.dto.TaxaAplicadaDto;
import br.com.AgendamentoTransferencias.dto.input.AplicarTaxaInput;
import br.com.AgendamentoTransferencias.dto.listagem.ListarTaxaDto;
import br.com.AgendamentoTransferencias.exception.EntidadeNaoEncontradaException;
import br.com.AgendamentoTransferencias.exception.SemTaxaAplicavelException;
import br.com.AgendamentoTransferencias.service.TaxaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe que recebe as requisições HTTP relacionadas a taxas
 */
@RestController
@RequestMapping("/api/taxa")
public class TaxaController {

    @Autowired
    private TaxaService taxaService;

    @Operation(summary = "Rota para aplicar uma taxa")
    @PostMapping("/buscar")
    private TaxaAplicadaDto aplicarTaxa(@RequestBody AplicarTaxaInput input) throws EntidadeNaoEncontradaException, SemTaxaAplicavelException {
        return this.taxaService.aplicar(input);
    }

    @Operation(summary = "Rota para listar todas as taxas")
    @GetMapping("/listar")
    private List<ListarTaxaDto> listarTaxas() {
        return this.taxaService.listarTodasTaxas();
    }


}
