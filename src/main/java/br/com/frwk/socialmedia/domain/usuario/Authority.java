package br.com.frwk.socialmedia.domain.usuario;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_authority", schema = "social_media")
public class Authority {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private String code;
    private String description;

    public Authority() {
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

}
