package br.com.AgendamentoTransferencias.service;

import br.com.AgendamentoTransferencias.assembler.BuscarTaxaDtoAssembler;
import br.com.AgendamentoTransferencias.assembler.ListarTaxaDtoAssembler;
import br.com.AgendamentoTransferencias.dto.TaxaAplicadaDto;
import br.com.AgendamentoTransferencias.dto.input.AplicarTaxaInput;
import br.com.AgendamentoTransferencias.dto.listagem.ListarTaxaDto;
import br.com.AgendamentoTransferencias.entity.Taxa;
import br.com.AgendamentoTransferencias.exception.EntidadeNaoEncontradaException;
import br.com.AgendamentoTransferencias.exception.SemTaxaAplicavelException;
import br.com.AgendamentoTransferencias.repository.TaxaRepository;
import br.com.AgendamentoTransferencias.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

/**
 * Classe responsável pelas regras de negócio relacionadas a taxas
 */
@Service
public class TaxaService {

    @Autowired
    private TaxaRepository taxaRepository;

    @Autowired
    private BuscarTaxaDtoAssembler buscarTaxaDtoAssembler;

    @Autowired
    private ListarTaxaDtoAssembler listarTaxaDtoAssembler;

    public TaxaAplicadaDto aplicar(AplicarTaxaInput input) throws EntidadeNaoEncontradaException, SemTaxaAplicavelException {
        Taxa taxa = this.findByIntervaloDias(input.getIntervaloInicio(), input.getIntervaloFim());
        BigDecimal taxaPorcentagem;
        BigDecimal taxaMultiplicada;
        BigDecimal valorFinal;
        if (!Objects.equals(taxa.getTaxa(), BigDecimal.ZERO)) {
            taxaPorcentagem = taxa.getTaxa().divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN);
            taxaMultiplicada = input.getValorTransferencia().multiply(taxaPorcentagem);
            valorFinal = input.getValorTransferencia().add(taxaMultiplicada.add(taxa.getValorBase()));
        } else {
            valorFinal = input.getValorTransferencia().add(taxa.getValorBase());
        }
        return new TaxaAplicadaDto(taxa.getValorBase(), taxa.getTaxa(), valorFinal);
    }

    public Taxa findByIntervaloDias(String intervaloInicio, String intervaloFim) throws EntidadeNaoEncontradaException, SemTaxaAplicavelException {
        int intervalo = DataUtil.calcularDiferencaEmDias(intervaloInicio, intervaloFim);
        if(intervalo > 50){
            throw new SemTaxaAplicavelException();
        }
        return this.taxaRepository.findByIntervalo(intervalo).orElseThrow(EntidadeNaoEncontradaException::new);
    }

    public List<ListarTaxaDto> listarTodasTaxas() {
        return this.listarTaxaDtoAssembler.mapearLista(this.taxaRepository.findAll());
    }
}
