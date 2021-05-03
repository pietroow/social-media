package br.com.frwk.socialmedia.domain.publicacao.repository;

import br.com.frwk.socialmedia.domain.publicacao.Publicacao;

import java.util.List;
import java.util.UUID;

public interface PublicacaoRepository {

    Publicacao save(Publicacao publicacao);

    Publicacao findById(UUID publicacaoId);

    List<Publicacao> findByUsuarioId(UUID usuarioId);

}
