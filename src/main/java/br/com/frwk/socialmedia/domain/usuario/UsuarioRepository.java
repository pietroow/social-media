package br.com.frwk.socialmedia.domain.usuario;

import java.util.UUID;

public interface UsuarioRepository {

    Usuario findById(UUID usuarioId);

}
