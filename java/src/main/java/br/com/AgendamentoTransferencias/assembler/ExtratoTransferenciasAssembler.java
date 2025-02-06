package br.com.AgendamentoTransferencias.assembler;

import br.com.AgendamentoTransferencias.dto.listagem.ExtratoTransferenciasDto;
import br.com.AgendamentoTransferencias.entity.Agendamento;
import br.com.AgendamentoTransferencias.utils.ConversorUtil;
import br.com.AgendamentoTransferencias.utils.DataUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Mapeamento de Entidade para DTO ou o inverso
 */
@Component
public class ExtratoTransferenciasAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ExtratoTransferenciasDto entidadeParaDto(Agendamento entity){
        ExtratoTransferenciasDto dto = this.modelMapper.map(entity, ExtratoTransferenciasDto.class);
        dto.setValorBase(ConversorUtil.formatarParaBRL(entity.getValorBase()));
        dto.setValorTransferencia(ConversorUtil.formatarParaBRL(entity.getValorTransferencia()));
        dto.setValorFinal(ConversorUtil.formatarParaBRL(entity.getValorFinal()));
        dto.setTaxa(entity.getTaxa() + " %");
        dto.setDataTransferencia(DataUtil.formatar(entity.getDataTransferencia(),"dd/MM/yyyy"));
        dto.setDataAgendamento(DataUtil.formatar(entity.getDataAgendamento(),"dd/MM/yyyy"));
        return dto;
    }

    public List<ExtratoTransferenciasDto> mapearLista(List<Agendamento> list) {
        return list.stream().map(this::entidadeParaDto).collect(Collectors.toList());
    }
}
