package br.com.frwk.socialmedia.domain.imagem;

import br.com.frwk.socialmedia.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_imagem", schema = "social_media")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Imagem {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Lob
    private byte[] imagem;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario criador;

    public Imagem(byte[] imagem, Usuario criador) {
        this.id = UUID.randomUUID();
        this.imagem = imagem;
        this.criador = criador;
    }

}
