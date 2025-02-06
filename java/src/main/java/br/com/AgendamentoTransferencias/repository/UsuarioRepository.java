package br.com.AgendamentoTransferencias.repository;

import br.com.AgendamentoTransferencias.entity.Usuario;
import br.com.AgendamentoTransferencias.service.UsuarioService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByConta(String conta);
}
