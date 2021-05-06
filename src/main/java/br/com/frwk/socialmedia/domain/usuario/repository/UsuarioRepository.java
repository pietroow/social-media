package br.com.frwk.socialmedia.domain.usuario.repository;

import br.com.frwk.socialmedia.domain.usuario.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {

    Usuario findById(UUID usuarioId);

    Usuario save(Usuario usuario);

    Optional<Usuario> findByEmailIgnoreCase(String email);

}
