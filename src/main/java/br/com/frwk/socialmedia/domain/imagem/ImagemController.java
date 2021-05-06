package br.com.frwk.socialmedia.domain.imagem;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/imagens")
@RequiredArgsConstructor
public class ImagemController {

    private ImagemService imagemService;

    @PostMapping("/adicionar-imagem/publicacao/{publicacaoId}")
    public void adicionarImagemPublicacao(@PathVariable("publicacaoId") UUID publicacaoId,
                                          @RequestParam MultipartFile file,
                                          Authentication authentication) {
        imagemService.uploadImage(publicacaoId, file, authentication);
    }

    @PostMapping("/adicionar-imagem/album-foto/{albumId}")
    public void adicionarImagemAlbum(@PathVariable("albumId") UUID albumId,
                                     @RequestParam MultipartFile file,
                                     Authentication authentication) {
        imagemService.uploadImageAlbumFoto(albumId, file, authentication);
    }

}
