package br.com.frwk.socialmedia.domain.publicacao.repository;

import br.com.frwk.socialmedia.domain.publicacao.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface PublicacaoRepositoryJPA extends JpaRepository<Publicacao, UUID> {

    List<Publicacao> findByCriadorId(UUID usuarioId);

}
