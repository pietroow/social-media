package br.com.frwk.socialmedia.domain.album_foto;

import br.com.frwk.socialmedia.domain.imagem.Imagem;
import br.com.frwk.socialmedia.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_album_foto", schema = "social_media")
@Where(clause = "deleted_at IS NULL")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class AlbumFoto {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario criador;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_album_foto_imagem", joinColumns = @JoinColumn(name = "album_foto_id"),
            inverseJoinColumns = @JoinColumn(name = "imagem_id"), schema = "social_media")
    private Set<Imagem> imagens;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public AlbumFoto(CriarAlbumFotoDTO criarAlbumFotoDTO, Usuario criador) {
        this.id = UUID.randomUUID();
        this.titulo = criarAlbumFotoDTO.getTitulo();
        this.criador = criador;
    }

    public void adicionarImagem(Imagem imagem) {
        this.imagens.add(imagem);
    }

    public void excluir() {
        this.deletedAt = LocalDateTime.now();
    }

}
