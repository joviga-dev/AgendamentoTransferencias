package br.com.AgendamentoTransferencias.repository;

import br.com.AgendamentoTransferencias.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Integer> {

    Optional<Agendamento> findById(Integer id);

    List<Agendamento> findAllByContaOrigem(String contaOrigem);
}
