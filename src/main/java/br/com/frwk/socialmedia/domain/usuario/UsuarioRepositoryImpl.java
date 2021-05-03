package br.com.frwk.socialmedia.domain.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioRepositoryJPA usuarioRepositoryJPA;

    @Override
    public Usuario findById(UUID usuarioId) {
        Optional<Usuario> usuarioOptional = usuarioRepositoryJPA.findById(usuarioId);
        return usuarioOptional.orElseThrow(() -> new EntityNotFoundException("USUÁRIO NÃO ENCONTRADO."));
    }

}
