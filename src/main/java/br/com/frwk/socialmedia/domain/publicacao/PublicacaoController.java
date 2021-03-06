package br.com.frwk.socialmedia.domain.publicacao;

import br.com.frwk.socialmedia.domain.publicacao.dto.CriarPublicacaoDTO;
import br.com.frwk.socialmedia.domain.publicacao.dto.PublicacaoListaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static br.com.frwk.socialmedia.ConstanteUsuario.USUARIO_ID;

@RestController
@RequestMapping("/publicacao")
@RequiredArgsConstructor
public class PublicacaoController {

    private final PublicacaoService publicacaoService;

    @PostMapping
    public ResponseEntity<Publicacao> criarPublicacao(@Valid @RequestBody CriarPublicacaoDTO criarPublicacaoDTO) {
        Publicacao publicacao = publicacaoService.criarPublicacao(criarPublicacaoDTO, USUARIO_ID);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(publicacao.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{usuarioId}")
    public List<PublicacaoListaDTO> findAllPublicacoesByUsuario(@PathVariable("usuarioId") UUID usuarioId) {
        return publicacaoService.findAllPublicacoesByUsuario(usuarioId);
    }

    @DeleteMapping("/{publicacaoId}")
    public void deleteById(@PathVariable("publicacaoId") UUID publicacaoId) {
        publicacaoService.deleteById(publicacaoId, USUARIO_ID);
    }

}
