package br.com.frwk.socialmedia.domain.publicacao;

import br.com.frwk.socialmedia.domain.publicacao.dto.CriarPublicacaoDTO;
import br.com.frwk.socialmedia.domain.publicacao.dto.PublicacaoListaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/publicacao")
@RequiredArgsConstructor
public class PublicacaoController {

    private final PublicacaoService publicacaoService;

    public static final UUID USUARIO_ID = UUID.fromString("f9196784-68c2-4c77-925f-697736bfa4be");

    @PostMapping
    public ResponseEntity<Publicacao> criarPublicacao(@Valid @RequestBody CriarPublicacaoDTO criarPublicacaoDTO) {
        Publicacao publicacao = publicacaoService.criarPublicacao(criarPublicacaoDTO, USUARIO_ID);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(publicacao.getId())
                .toUri();
        log.info("Criado novo cliente com id: {}", publicacao.getId());
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/adicionar-imagem/{publicacaoId}")
    public void uploadImage(@PathVariable("publicacaoId") UUID publicacaoId,
                            @RequestParam MultipartFile file) {
        publicacaoService.uploadImage(publicacaoId, file, USUARIO_ID);
    }

    @PostMapping("/adicionar-comentario/{publicacaoId}")
    public void adicionarComentario(@PathVariable("publicacaoId") UUID publicacaoId,
                                    @RequestBody String comentario) {
        publicacaoService.adicionarComentario(publicacaoId, comentario, USUARIO_ID);
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
