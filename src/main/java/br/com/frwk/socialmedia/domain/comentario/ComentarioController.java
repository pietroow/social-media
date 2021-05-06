package br.com.frwk.socialmedia.domain.comentario;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private ComentarioService comentarioService;

    @PostMapping("/adicionar/{publicacaoId}")
    public void adicionarComentario(@PathVariable("publicacaoId") UUID publicacaoId,
                                    @RequestBody String comentario,
                                    Authentication authentication) {
        comentarioService.adicionarComentario(publicacaoId, comentario, authentication);
    }

    @DeleteMapping("/remover/{comentarioId}")
    public void removerComentario(@PathVariable UUID comentarioId, Authentication authentication) {
        comentarioService.removerComentario(comentarioId, authentication);
    }

}
