package br.com.frwk.socialmedia.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface UsuarioRepositoryJPA extends JpaRepository<Usuario, UUID> {

}
