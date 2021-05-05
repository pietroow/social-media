package br.com.frwk.socialmedia.domain.comentario.repository;

import br.com.frwk.socialmedia.domain.comentario.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ComentarioRepositoryJPA extends JpaRepository<Comentario, UUID> {

}
