package br.com.frwk.socialmedia.domain.imagem;

import br.com.frwk.socialmedia.domain.publicacao.Publicacao;
import br.com.frwk.socialmedia.domain.publicacao.repository.PublicacaoRepository;
import br.com.frwk.socialmedia.domain.usuario.Usuario;
import br.com.frwk.socialmedia.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImagemService {

    private final PublicacaoRepository publicacaoRepository;
    private final UsuarioRepository usuarioRepository;

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

}
