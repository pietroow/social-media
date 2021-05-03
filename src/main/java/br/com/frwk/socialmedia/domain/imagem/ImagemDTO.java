package br.com.frwk.socialmedia.domain.imagem;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ImagemDTO {

    private final UUID id;
    private final byte[] imagem;

    public ImagemDTO(Imagem imagem) {
        this.id = imagem.getId();
        this.imagem = imagem.getImagem();
    }

}
