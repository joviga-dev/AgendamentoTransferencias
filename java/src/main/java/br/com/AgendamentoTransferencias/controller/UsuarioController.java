package br.com.AgendamentoTransferencias.controller;

import br.com.AgendamentoTransferencias.exception.EntidadeNaoEncontradaException;
import br.com.AgendamentoTransferencias.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Classe que recebe as requisições HTTP relacionadas a Usuários
 */
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Retorna o saldo do usuário através do id")
    @GetMapping("/id/{id}/saldo")
    private String retornarSaldo(@PathVariable int id) throws EntidadeNaoEncontradaException {
        return this.usuarioService.retornarSaldo(id);
    }

}
