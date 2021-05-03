package br.com.frwk.socialmedia.domain.publicacao;

import br.com.frwk.socialmedia.domain.comentario.Comentario;
import br.com.frwk.socialmedia.domain.imagem.Imagem;
import br.com.frwk.socialmedia.domain.publicacao.dto.CriarPublicacaoDTO;
import br.com.frwk.socialmedia.domain.publicacao.dto.PublicacaoListaDTO;
import br.com.frwk.socialmedia.domain.publicacao.repository.PublicacaoRepository;
import br.com.frwk.socialmedia.domain.usuario.Usuario;
import br.com.frwk.socialmedia.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public Publicacao criarPublicacao(CriarPublicacaoDTO criarPublicacaoDTO, UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        Publicacao publicacao = new Publicacao(criarPublicacaoDTO, usuario);
        return publicacaoRepository.save(publicacao);
    }

    public void uploadImage(UUID publicacaoId, MultipartFile file, UUID id) {
        Usuario usuario = usuarioRepository.findById(id);
        Publicacao publicacao = publicacaoRepository.findById(publicacaoId);

        try {
            byte[] bytes = file.getBytes();
            Imagem imagem = new Imagem(bytes, usuario);
            publicacao.adicionarImagem(imagem);
        } catch (IOException e) {
            e.printStackTrace();
        }

        publicacaoRepository.save(publicacao);
    }

    public void adicionarComentario(UUID publicacaoId, String comentario, UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        Publicacao publicacao = publicacaoRepository.findById(publicacaoId);
        Comentario novoComentario = new Comentario(comentario, usuario);
        publicacao.adicionarComentario(novoComentario);
    }

    public List<PublicacaoListaDTO> findAllPublicacoesByUsuario(UUID usuarioId) {
        List<Publicacao> publicacoes = publicacaoRepository.findByUsuarioId(usuarioId);
        return publicacoes.stream()
                .map(PublicacaoListaDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteById(UUID publicacaoId, UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        Publicacao publicacao = publicacaoRepository.findById(publicacaoId);

        if(!isUsuarioCriador(usuario, publicacao)){
            throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
        }

        publicacao.excluir();
        publicacaoRepository.save(publicacao);
    }

    private boolean isUsuarioCriador(Usuario usuario, Publicacao publicacao){
        UUID idCriador = publicacao.getIdCriador();
        UUID requisicaoId = usuario.getId();
        return idCriador.equals(requisicaoId);
    }

}
