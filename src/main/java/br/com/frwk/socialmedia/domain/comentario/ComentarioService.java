package br.com.frwk.socialmedia.domain.comentario;

import br.com.frwk.socialmedia.domain.comentario.repository.ComentarioRepository;
import br.com.frwk.socialmedia.domain.publicacao.Publicacao;
import br.com.frwk.socialmedia.domain.publicacao.repository.PublicacaoRepository;
import br.com.frwk.socialmedia.domain.usuario.BuscarUsuarioComponent;
import br.com.frwk.socialmedia.domain.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioService {

    private final PublicacaoRepository publicacaoRepository;
    private final BuscarUsuarioComponent buscarUsuarioComponent;
    private final ComentarioRepository comentarioRepository;

    public void adicionarComentario(UUID publicacaoId, String comentario, Authentication authentication) {
        Usuario usuario = buscarUsuarioComponent.getUsuario(authentication);
        Publicacao publicacao = publicacaoRepository.findById(publicacaoId);
        Comentario novoComentario = new Comentario(comentario, usuario);
        publicacao.adicionarComentario(novoComentario);
    }

    public void removerComentario(UUID comentarioId, Authentication authentication) {
        Usuario usuario = buscarUsuarioComponent.getUsuario(authentication);
        Comentario comentario = comentarioRepository.findById(comentarioId);
        Publicacao publicacao = comentario.getPublicacao();

        if (!isUsuarioCriador(usuario, publicacao)) {
            throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
        }

        comentario.excluir();
        comentarioRepository.save(comentario);
    }

    private boolean isUsuarioCriador(Usuario usuario, Publicacao publicacao) {
        UUID idCriador = publicacao.getIdCriador();
        UUID requisicaoId = usuario.getId();
        return idCriador.equals(requisicaoId);
    }

}
