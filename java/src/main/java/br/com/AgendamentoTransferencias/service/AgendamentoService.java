package br.com.AgendamentoTransferencias.service;

import br.com.AgendamentoTransferencias.assembler.AgendarTransferenciaAssembler;
import br.com.AgendamentoTransferencias.assembler.ExtratoTransferenciasAssembler;
import br.com.AgendamentoTransferencias.dto.cadastro.AgendarTransferenciaDto;
import br.com.AgendamentoTransferencias.dto.listagem.ExtratoTransferenciasDto;
import br.com.AgendamentoTransferencias.entity.Agendamento;
import br.com.AgendamentoTransferencias.exception.EntidadeNaoEncontradaException;
import br.com.AgendamentoTransferencias.exception.SaldoInsuficienteException;
import br.com.AgendamentoTransferencias.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe responsável pelas regras de negócio relacionadas a agendamentos
 */
@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AgendarTransferenciaAssembler agendarTransferenciaAssembler;

    @Autowired
    private ExtratoTransferenciasAssembler extratoTransferenciasAssembler;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Busca um agentamendo pelo ID, em caso de falha lança uma exception
     * @param id
     * @return Agendamento
     * @throws EntidadeNaoEncontradaException
     */
    public Agendamento buscarOuFalhar(Integer id) throws EntidadeNaoEncontradaException {
        return this.agendamentoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(id));
    }

    /**
     * Agenda uma transferência
     * @param dto
     */
    public void agendar(AgendarTransferenciaDto dto) throws SaldoInsuficienteException, EntidadeNaoEncontradaException {
        if (this.usuarioService.validarSaldoAtual(dto.getContaOrigem(), dto.getValorFinal())) {
            Agendamento agendamento = this.agendarTransferenciaAssembler.dtoParaEntidade(dto);
            this.agendamentoRepository.save(agendamento);
            this.usuarioService.abaterSaldo(dto.getContaOrigem(), dto.getValorFinal());
        } else {
            throw new SaldoInsuficienteException();
        }
    }

    /**
     * Lista todos os agendamentos
     */
    public List<ExtratoTransferenciasDto> listar(String contaOrigem) {
        return extratoTransferenciasAssembler.mapearLista(agendamentoRepository.findAllByContaOrigem(contaOrigem));
    }
}
