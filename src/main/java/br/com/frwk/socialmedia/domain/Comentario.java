package br.com.frwk.socialmedia.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
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
public class Comentario {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private String texto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario criador;

    @ManyToOne(fetch = FetchType.LAZY)
    private Publicacao publicacao;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Comentario() {
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", criador=" + criador +
                ", publicacao=" + publicacao +
                '}';
    }

}
