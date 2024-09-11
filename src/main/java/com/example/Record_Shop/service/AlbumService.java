package com.example.Record_Shop.service;

import com.example.Record_Shop.data.Album;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface AlbumService {

    List<Album> getAllAlbums();

    Album getAlbumById(Long id);

    Album insertAlbum(Album album);

    Album updateAlbumById(Long id, Album album);

    Boolean deleteAlbumById(Long id);
}
