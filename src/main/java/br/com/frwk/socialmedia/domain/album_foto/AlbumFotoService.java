package br.com.frwk.socialmedia.domain.album_foto;

import br.com.frwk.socialmedia.domain.album_foto.repository.AlbumFotoRepository;
import br.com.frwk.socialmedia.domain.usuario.Usuario;
import br.com.frwk.socialmedia.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AlbumFotoService {

    private final UsuarioRepository usuarioRepository;
    private final AlbumFotoRepository albumFotoRepository;

    public AlbumFoto criarAlbumFoto(CriarAlbumFotoDTO criarAlbumFotoDTO, UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        AlbumFoto albumFoto = new AlbumFoto(criarAlbumFotoDTO, usuario);
        return albumFotoRepository.save(albumFoto);
    }

    public void removerAlbumFoto(UUID albumId, UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);

        AlbumFoto albumFoto = albumFotoRepository.findById(albumId);
        Usuario criador = albumFoto.getCriador();

        if (!isUsuarioCriador(usuario, criador)) {
            throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
        }

        albumFoto.excluir();
        albumFotoRepository.save(albumFoto);
    }

    private boolean isUsuarioCriador(Usuario usuarioRequisicao, Usuario usuarioCriador) {
        UUID requisicaoId = usuarioRequisicao.getId();
        UUID usuarioCriadorId = usuarioCriador.getId();
        return requisicaoId.equals(usuarioCriadorId);
    }

}
