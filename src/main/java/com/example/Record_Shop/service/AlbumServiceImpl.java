package com.example.Record_Shop.service;

import com.example.Record_Shop.data.Album;
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
    public Optional<Album> getAlbumById(Long id) { return albumRepository.findById(id); }

    @Override
    public Album insertAlbum(Album album) {
        return albumRepository.save(album);
    }
}
