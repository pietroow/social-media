package br.com.frwk.socialmedia.domain.comentario;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.frwk.socialmedia.ConstanteUsuario.USUARIO_ID;

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

    @DeleteMapping("/remover/{comentarioId}")
    public void removerComentario(@PathVariable UUID comentarioId) {
        comentarioService.removerComentario(comentarioId, USUARIO_ID);
    }

}
