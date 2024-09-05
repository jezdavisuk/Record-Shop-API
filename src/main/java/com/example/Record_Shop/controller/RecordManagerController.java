package com.example.Record_Shop.controller;

import com.example.Record_Shop.data.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Record_Shop.service.AlbumService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/records")
public class RecordManagerController {

    @Autowired
    AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Album>> getAlbumById(@PathVariable Long id) {
        Optional<Album> albums = albumService.getAlbumById(id);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }
}
