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
    public Album insertAlbum(Album album) { return albumRepository.save(album); }

    public Album placeAlbumById(Long id, Album album) {
        Optional<Album> record = albumRepository.findById(id);
        Album newRecord;
        if (record.isPresent()) {
            newRecord = record.get();
            newRecord.setRecordName(album.getRecordName());
            newRecord.setArtist(album.getArtist());
            newRecord.setYearOfRelease(album.getYearOfRelease());
            newRecord.setGenre(album.getGenre());
            newRecord.setQuantityInStock(album.getQuantityInStock());
            newRecord.setAvailable(album.getQuantityInStock() > 0);
            return albumRepository.save(newRecord);
        } else {
            album.setId(id);
            album.setAvailable(album.getQuantityInStock() > 0);
            return albumRepository.save(album);
        }
    }

    @Override
    public Album updateAlbumById(Long id, Album album) {
        Optional<Album> record = albumRepository.findById(id);
        Album newRecord;
        if (record.isPresent()) {
            newRecord = record.get();
            newRecord.setRecordName(album.getRecordName());
            newRecord.setArtist(album.getArtist());
            newRecord.setYearOfRelease(album.getYearOfRelease());
            newRecord.setGenre(album.getGenre());
            newRecord.setQuantityInStock(album.getQuantityInStock());
            newRecord.setAvailable(album.getQuantityInStock() > 0);
            return albumRepository.save(newRecord);
        } else {
            throw new ItemNotFoundException(String.format("Album with id '%s' cannot be located.", id));
        }
    }

    @Override
    public String deleteAlbumById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if(album.isPresent()) {
            albumRepository.deleteById(id);
            return String.format("Album with id '%s' has been deleted successfully.", id);
        } else {
            throw new ItemNotFoundException(String.format("Album with id '%s' cannot be located.", id));
        }
    }
}
