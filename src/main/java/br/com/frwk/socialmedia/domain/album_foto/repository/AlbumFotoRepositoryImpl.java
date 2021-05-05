package br.com.frwk.socialmedia.domain.album_foto.repository;

import br.com.frwk.socialmedia.domain.album_foto.AlbumFoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Repository
class AlbumFotoRepositoryImpl implements AlbumFotoRepository {

    @Autowired
    private AlbumFotoRepositoryJPA albumFotoRepositoryJPA;

    @Override
    public AlbumFoto save(AlbumFoto albumFoto) {
        return albumFotoRepositoryJPA.save(albumFoto);
    }

    @Override
    public AlbumFoto findById(UUID albumId) {
        Optional<AlbumFoto> albumFotoOptional = albumFotoRepositoryJPA.findById(albumId);
        return albumFotoOptional.orElseThrow(() -> new EntityNotFoundException("ALBUM DE FOTOS NÃO ENCONTRADO."));
    }

}
