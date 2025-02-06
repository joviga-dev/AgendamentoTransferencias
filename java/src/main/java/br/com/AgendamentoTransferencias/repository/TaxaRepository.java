package br.com.AgendamentoTransferencias.repository;

import br.com.AgendamentoTransferencias.entity.Agendamento;
import br.com.AgendamentoTransferencias.entity.Taxa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxaRepository  extends JpaRepository<Taxa,Integer> {
    @Query("SELECT t FROM Taxa t WHERE :dias BETWEEN t.intervaloInicio AND t.intervaloFim")
    Optional<Taxa> findByIntervalo(int dias);
}
