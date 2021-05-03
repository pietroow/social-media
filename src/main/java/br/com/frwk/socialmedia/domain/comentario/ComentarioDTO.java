package br.com.frwk.socialmedia.domain.comentario;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class ComentarioDTO {

    private final UUID id;
    private final String texto;
    private final UUID criadoId;
    private final LocalDateTime criadoEm;

    public ComentarioDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.texto = comentario.getTexto();
        this.criadoId = comentario.getIdCriador();
        this.criadoEm = comentario.getCreatedAt();
    }

}
