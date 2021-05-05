package br.com.frwk.socialmedia.domain.comentario;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.frwk.socialmedia.ConstanteUsuario.USUARIO_ID;

@Slf4j
@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private ComentarioService comentarioService;

    @PostMapping("/adicionar/{publicacaoId}")
    public void adicionarComentario(@PathVariable("publicacaoId") UUID publicacaoId,
                                    @RequestBody String comentario) {
        comentarioService.adicionarComentario(publicacaoId, comentario, USUARIO_ID);
    }

    @DeleteMapping("/remover/{publicacaoId}")
    public void removerComentario(@RequestParam UUID comentarioId) {
        comentarioService.removerComentario(comentarioId, USUARIO_ID);
    }

}
