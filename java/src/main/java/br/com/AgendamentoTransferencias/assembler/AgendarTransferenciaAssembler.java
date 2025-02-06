package br.com.AgendamentoTransferencias.assembler;

import br.com.AgendamentoTransferencias.dto.cadastro.AgendarTransferenciaDto;
import br.com.AgendamentoTransferencias.entity.Agendamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapeamento de Entidade para DTO ou o inverso
 */
@Component
public class AgendarTransferenciaAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public Agendamento dtoParaEntidade(AgendarTransferenciaDto dto){
        return this.modelMapper.map(dto, Agendamento.class);
    }
}
