package com.example.Record_Shop.service;

import com.example.Record_Shop.data.Album;
import com.example.Record_Shop.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Record_Shop.repository.AlbumRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Album getAlbumById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if(album.isPresent()) {
            return album.get();
        } else {
            throw new ItemNotFoundException(String.format("Album with id '%s' cannot be located.", id));
        }
    }

    @Override
    public Album insertAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album updateAlbumById(Long id, Album album) {
        Album oldAlbum = albumRepository.findById(id).get();
        oldAlbum.setRecordName(album.getRecordName());
        oldAlbum.setArtist(album.getArtist());
        oldAlbum.setYearOfRelease(album.getYearOfRelease());
        oldAlbum.setGenre(album.getGenre());
        oldAlbum.setQuantityInStock(album.getQuantityInStock());
        oldAlbum.setAvailable(album.isAvailable());
        return albumRepository.save(oldAlbum);
    }

    @Override
    public Boolean deleteAlbumById(Long id) {
        Optional<Album> album = getAlbumById(id);
        Boolean status = album.isPresent();
        if (status) albumRepository.deleteById(id);
        return status;
    }
}
