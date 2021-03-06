package br.com.frwk.socialmedia.domain.imagem;

import br.com.frwk.socialmedia.domain.album_foto.AlbumFoto;
import br.com.frwk.socialmedia.domain.album_foto.repository.AlbumFotoRepository;
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

    private final AlbumFotoRepository albumFotoRepository;
    private final PublicacaoRepository publicacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public void uploadImage(UUID publicacaoId, MultipartFile file, UUID id) {
        Usuario usuario = usuarioRepository.findById(id);
        Publicacao publicacao = publicacaoRepository.findById(publicacaoId);
        Imagem imagem = getImagem(file, usuario);
        publicacao.adicionarImagem(imagem);
        publicacaoRepository.save(publicacao);
    }

    public void uploadImageAlbumFoto(UUID albumId, MultipartFile file, UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        AlbumFoto albumFoto = albumFotoRepository.findById(albumId);
        Imagem imagem = getImagem(file, usuario);
        albumFoto.adicionarImagem(imagem);
        albumFotoRepository.save(albumFoto);
    }

    private Imagem getImagem(MultipartFile file, Usuario usuario) {
        try {
            byte[] bytes = file.getBytes();
            return new Imagem(bytes, usuario);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
