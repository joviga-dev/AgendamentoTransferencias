package br.com.AgendamentoTransferencias.assembler;

import br.com.AgendamentoTransferencias.dto.listagem.ListarTaxaDto;
import br.com.AgendamentoTransferencias.entity.Taxa;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Mapeamento de Entidade para DTO ou o inverso
 */
@Component
@Data
public class ListarTaxaDtoAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ListarTaxaDto entidadeParaDto(Taxa entity){
        return this.modelMapper.map(entity, ListarTaxaDto.class);
    }

    public List<ListarTaxaDto> mapearLista(List<Taxa> list) {
        return list.stream().map(this::entidadeParaDto).collect(Collectors.toList());
    }
}
