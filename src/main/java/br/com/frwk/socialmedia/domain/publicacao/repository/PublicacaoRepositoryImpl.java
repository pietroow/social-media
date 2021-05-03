package br.com.frwk.socialmedia.domain.publicacao.repository;

import br.com.frwk.socialmedia.domain.publicacao.Publicacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class PublicacaoRepositoryImpl implements PublicacaoRepository {

    @Autowired
    private PublicacaoRepositoryJPA publicacaoRepositoryJPA;

    @Override
    public Publicacao save(Publicacao publicacao) {
        return publicacaoRepositoryJPA.save(publicacao);
    }

    @Override
    public Publicacao findById(UUID publicacaoId) {
        Optional<Publicacao> publicacaoOptional = publicacaoRepositoryJPA.findById(publicacaoId);
        return publicacaoOptional.orElseThrow(() -> new EntityNotFoundException("PUBLICAÇÃO NÃO ENCONTRADA."));
    }

    @Override
    public List<Publicacao> findByUsuarioId(UUID usuarioId) {
        return publicacaoRepositoryJPA.findByCriadorId(usuarioId);
    }

}
