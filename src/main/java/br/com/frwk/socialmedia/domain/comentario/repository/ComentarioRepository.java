package br.com.frwk.socialmedia.domain.comentario.repository;

import br.com.frwk.socialmedia.domain.comentario.Comentario;

import java.util.UUID;

public interface ComentarioRepository {

    Comentario findById(UUID comentarioId);

    Comentario save(Comentario comentario);

}
