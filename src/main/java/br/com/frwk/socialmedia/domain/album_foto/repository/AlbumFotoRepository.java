package br.com.frwk.socialmedia.domain.album_foto.repository;

import br.com.frwk.socialmedia.domain.album_foto.AlbumFoto;

import java.util.UUID;

public interface AlbumFotoRepository {

    AlbumFoto save(AlbumFoto albumFoto);

    AlbumFoto findById(UUID albumId);

}
