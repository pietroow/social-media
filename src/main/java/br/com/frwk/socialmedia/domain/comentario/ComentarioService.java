package br.com.frwk.socialmedia.domain.comentario;

import br.com.frwk.socialmedia.domain.publicacao.Publicacao;
import br.com.frwk.socialmedia.domain.publicacao.repository.PublicacaoRepository;
import br.com.frwk.socialmedia.domain.usuario.Usuario;
import br.com.frwk.socialmedia.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioService {

    private final PublicacaoRepository publicacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public void adicionarComentario(UUID publicacaoId, String comentario, UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        Publicacao publicacao = publicacaoRepository.findById(publicacaoId);
        Comentario novoComentario = new Comentario(comentario, usuario);
        publicacao.adicionarComentario(novoComentario);
    }

}
