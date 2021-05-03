package br.com.frwk.socialmedia.domain.publicacao;

import br.com.frwk.socialmedia.domain.comentario.Comentario;
import br.com.frwk.socialmedia.domain.imagem.Imagem;
import br.com.frwk.socialmedia.domain.publicacao.dto.CriarPublicacaoDTO;
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
@Table(name = "tb_publicacao", schema = "social_media")
@Where(clause = "deleted_at IS NULL")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Publicacao {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private String texto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario criador;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_publicacao_comentario", joinColumns = @JoinColumn(name = "publicacao_id"),
            inverseJoinColumns = @JoinColumn(name = "comentario_id"), schema = "social_media")
    private Set<Comentario> comentarios;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_publicacao_imagem", joinColumns = @JoinColumn(name = "publicacao_id"),
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

    public Publicacao(CriarPublicacaoDTO criarPublicacaoDTO, Usuario usuario) {
        this.id = UUID.randomUUID();
        this.texto = criarPublicacaoDTO.getTexto();
        this.criador = usuario;
    }

    public void excluir() {
        this.deletedAt = LocalDateTime.now();
    }

    public void adicionarImagem(Imagem imagem) {
        this.imagens.add(imagem);
    }

    public void adicionarComentario(Comentario novoComentario) {
        this.comentarios.add(novoComentario);
    }

    public UUID getIdCriador() {
        return criador.getId();
    }

    @Override
    public String toString() {
        return "Publicacao{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", criador=" + criador +
                '}';
    }

}
