package br.com.frwk.socialmedia.domain.album_foto;

import br.com.frwk.socialmedia.domain.publicacao.Publicacao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

import static br.com.frwk.socialmedia.ConstanteUsuario.USUARIO_ID;

@RestController
@RequestMapping("/album-fotos")
@RequiredArgsConstructor
public class AlbumFotoController {

    private final AlbumFotoService albumFotoService;

    @PostMapping
    public ResponseEntity<Publicacao> criarAlbumFoto(@Valid @RequestBody CriarAlbumFotoDTO criarAlbumFotoDTO) {
        AlbumFoto albumFoto = albumFotoService.criarAlbumFoto(criarAlbumFotoDTO, USUARIO_ID);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(albumFoto.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/remover/{albumId}")
    public void removerAlbumFoto(@RequestParam UUID albumId) {
        albumFotoService.removerAlbumFoto(albumId, USUARIO_ID);
    }

}
