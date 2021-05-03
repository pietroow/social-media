package br.com.frwk.socialmedia.domain.comentario;

import br.com.frwk.socialmedia.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_comentario", schema = "social_media")
@Where(clause = "deleted_at IS NULL")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Comentario {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private String texto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario criador;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Comentario(String texto, Usuario criador) {
        this.id = UUID.randomUUID();
        this.texto = texto;
        this.criador = criador;
    }

    public UUID getIdCriador() {
        return criador.getId();
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", criador=" + criador +
                '}';
    }

}
