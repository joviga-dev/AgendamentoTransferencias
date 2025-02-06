package br.com.AgendamentoTransferencias.assembler;

import br.com.AgendamentoTransferencias.dto.TaxaAplicadaDto;
import br.com.AgendamentoTransferencias.entity.Taxa;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapeamento de Entidade para DTO ou o inverso
 */
@Data
@Component
public class BuscarTaxaDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public TaxaAplicadaDto entidadeParaDto(Taxa entity){
        return this.modelMapper.map(entity, TaxaAplicadaDto.class);
    }
}
