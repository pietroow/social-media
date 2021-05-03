package br.com.frwk.socialmedia.domain.publicacao.dto;

import br.com.frwk.socialmedia.domain.comentario.Comentario;
import br.com.frwk.socialmedia.domain.comentario.ComentarioDTO;
import br.com.frwk.socialmedia.domain.imagem.Imagem;
import br.com.frwk.socialmedia.domain.imagem.ImagemDTO;
import br.com.frwk.socialmedia.domain.publicacao.Publicacao;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class PublicacaoListaDTO {

    private final UUID publicacaoId;
    private final String texto;
    private final UUID criadorId;
    private final List<ComentarioDTO> comentarios;
    private final List<ImagemDTO> imagens;
    private final LocalDateTime criadoEm;

    public PublicacaoListaDTO(Publicacao publicacao) {
        this.publicacaoId = publicacao.getId();
        this.texto = publicacao.getTexto();
        this.criadorId = publicacao.getIdCriador();
        this.comentarios = mapComentarios(publicacao.getComentarios());
        this.imagens = mapImagens(publicacao.getImagens());
        this.criadoEm = publicacao.getCreatedAt();
    }

    private List<ImagemDTO> mapImagens(Set<Imagem> imagens) {
        return imagens.stream()
                .map(ImagemDTO::new)
                .collect(Collectors.toList());
    }

    private List<ComentarioDTO> mapComentarios(Set<Comentario> comentarios) {
        return comentarios.stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());

    }

}
