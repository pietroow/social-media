package br.com.frwk.socialmedia.domain.album_foto;

import br.com.frwk.socialmedia.domain.publicacao.Publicacao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/album-fotos")
@RequiredArgsConstructor
public class AlbumFotoController {

    private final AlbumFotoService albumFotoService;

    @PostMapping
    public ResponseEntity<Publicacao> criarAlbumFoto(@Valid @RequestBody CriarAlbumFotoDTO criarAlbumFotoDTO,
                                                     Authentication authentication) {
        AlbumFoto albumFoto = albumFotoService.criarAlbumFoto(criarAlbumFotoDTO, authentication);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(albumFoto.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/remover/{albumId}")
    public void removerAlbumFoto(@RequestParam UUID albumId,
                                 Authentication authentication) {
        albumFotoService.removerAlbumFoto(albumId, authentication);
    }

}
