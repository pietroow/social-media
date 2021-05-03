package br.com.frwk.socialmedia.domain.imagem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static br.com.frwk.socialmedia.ConstanteUsuario.USUARIO_ID;

@Slf4j
@RestController
@RequestMapping("/imagens")
@RequiredArgsConstructor
public class ImagemController {

    private ImagemService imagemService;

    @PostMapping("/adicionar-imagem/{publicacaoId}")
    public void adicionarImagem(@PathVariable("publicacaoId") UUID publicacaoId,
                                @RequestParam MultipartFile file) {
        imagemService.uploadImage(publicacaoId, file, USUARIO_ID);
    }

}
