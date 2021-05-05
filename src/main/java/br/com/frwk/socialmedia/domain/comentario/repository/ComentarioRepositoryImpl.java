package br.com.frwk.socialmedia.domain.comentario.repository;

import br.com.frwk.socialmedia.domain.comentario.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Repository
class ComentarioRepositoryImpl implements ComentarioRepository {

    @Autowired
    private ComentarioRepositoryJPA comentarioRepositoryJPA;

    @Override
    public Comentario findById(UUID comentarioId) {
        Optional<Comentario> comentarioOptional = comentarioRepositoryJPA.findById(comentarioId);
        return comentarioOptional.orElseThrow(() -> new EntityNotFoundException("COMENTÁRIO NÃO ENCONTRADO."));
    }

    @Override
    public Comentario save(Comentario comentario) {
        return comentarioRepositoryJPA.save(comentario);
    }

}
