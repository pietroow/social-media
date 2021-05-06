package br.com.frwk.socialmedia.domain.usuario.repository;

import br.com.frwk.socialmedia.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface UsuarioRepositoryJPA extends JpaRepository<Usuario, UUID> {

}
