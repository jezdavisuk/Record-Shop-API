package com.example.Record_Shop.service;

import com.example.Record_Shop.data.Album;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> getAllAlbums();
    Optional<Album> getAlbumById(Long id);
}
