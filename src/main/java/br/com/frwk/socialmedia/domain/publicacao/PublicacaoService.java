package br.com.frwk.socialmedia.domain.publicacao;

import br.com.frwk.socialmedia.domain.publicacao.dto.CriarPublicacaoDTO;
import br.com.frwk.socialmedia.domain.publicacao.dto.PublicacaoListaDTO;
import br.com.frwk.socialmedia.domain.publicacao.repository.PublicacaoRepository;
import br.com.frwk.socialmedia.domain.usuario.BuscarUsuarioComponent;
import br.com.frwk.socialmedia.domain.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;
    private final BuscarUsuarioComponent buscarUsuarioComponent;

    public Publicacao criarPublicacao(CriarPublicacaoDTO criarPublicacaoDTO, Authentication authentication) {
        Usuario usuario = buscarUsuarioComponent.getUsuario(authentication);
        Publicacao publicacao = new Publicacao(criarPublicacaoDTO, usuario);
        return publicacaoRepository.save(publicacao);
    }

    public List<PublicacaoListaDTO> findAllPublicacoesByUsuario(UUID usuarioId) {
        List<Publicacao> publicacoes = publicacaoRepository.findByUsuarioId(usuarioId);
        return publicacoes.stream()
                .map(PublicacaoListaDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteById(UUID publicacaoId, Authentication authentication) {
        Usuario usuario = buscarUsuarioComponent.getUsuario(authentication);
        Publicacao publicacao = publicacaoRepository.findById(publicacaoId);

        if (!isUsuarioCriador(usuario, publicacao)) {
            throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
        }

        publicacao.excluir();
        publicacaoRepository.save(publicacao);
    }

    private boolean isUsuarioCriador(Usuario usuario, Publicacao publicacao) {
        UUID idCriador = publicacao.getIdCriador();
        UUID requisicaoId = usuario.getId();
        return idCriador.equals(requisicaoId);
    }

}
