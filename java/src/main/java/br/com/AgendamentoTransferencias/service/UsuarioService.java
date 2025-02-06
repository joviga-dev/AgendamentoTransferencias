package br.com.AgendamentoTransferencias.service;

import br.com.AgendamentoTransferencias.entity.Usuario;
import br.com.AgendamentoTransferencias.exception.EntidadeNaoEncontradaException;
import br.com.AgendamentoTransferencias.repository.UsuarioRepository;
import br.com.AgendamentoTransferencias.utils.ConversorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Classe responsável pelas regras de negócio relacionadas a Usuários
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarOuFalhar(int id) throws EntidadeNaoEncontradaException {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(id));
    }

    public Usuario buscarOuFalhar(String conta) throws EntidadeNaoEncontradaException {
        return this.usuarioRepository.findByConta(conta).orElseThrow(EntidadeNaoEncontradaException::new);
    }

    public String retornarSaldo(int id) throws EntidadeNaoEncontradaException {
        return ConversorUtil.formatarParaBRL(this.buscarOuFalhar(id).getSaldo());
    }

    public boolean validarSaldoAtual(String conta, BigDecimal valor) throws EntidadeNaoEncontradaException {
        return this.buscarOuFalhar(conta).getSaldo().compareTo(valor) == 1;
    }

    public void abaterSaldo(String conta, BigDecimal valor) throws EntidadeNaoEncontradaException {
        Usuario usuario = this.buscarOuFalhar(conta);
        usuario.setSaldo(usuario.getSaldo().subtract(valor));
        this.usuarioRepository.save(usuario);
    }
}
