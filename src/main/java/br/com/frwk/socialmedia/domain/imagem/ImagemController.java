package br.com.frwk.socialmedia.domain.imagem;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static br.com.frwk.socialmedia.ConstanteUsuario.USUARIO_ID;

@RestController
@RequestMapping("/imagens")
@RequiredArgsConstructor
public class ImagemController {

    private ImagemService imagemService;

    @PostMapping("/adicionar-imagem/publicacao/{publicacaoId}")
    public void adicionarImagemPublicacao(@PathVariable("publicacaoId") UUID publicacaoId,
                                          @RequestParam MultipartFile file) {
        imagemService.uploadImage(publicacaoId, file, USUARIO_ID);
    }

    @PostMapping("/adicionar-imagem/album-foto/{albumId}")
    public void adicionarImagemAlbum(@PathVariable("albumId") UUID albumId,
                                     @RequestParam MultipartFile file) {
        imagemService.uploadImageAlbumFoto(albumId, file, USUARIO_ID);
    }

}
