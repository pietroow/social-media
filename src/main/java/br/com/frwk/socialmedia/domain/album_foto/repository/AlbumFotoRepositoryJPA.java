package br.com.frwk.socialmedia.domain.album_foto.repository;

import br.com.frwk.socialmedia.domain.album_foto.AlbumFoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface AlbumFotoRepositoryJPA extends JpaRepository<AlbumFoto, UUID> {

}
