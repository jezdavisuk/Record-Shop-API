package com.example.Record_Shop.controller;

import com.example.Record_Shop.data.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        Album newAlbum = albumService.insertAlbum(album);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("album", "/api/v1/records/" + newAlbum.getId().toString());
        return new ResponseEntity<>(newAlbum, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbumById(@PathVariable Long id, @RequestBody Album album) {
        Album newAlbum = albumService.updateAlbumById(id, album);
        return new ResponseEntity<>(newAlbum, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbumById(@PathVariable Long id) {
        albumService.deleteAlbumById(id);
        return new ResponseEntity<>("Record deleted successfully.", HttpStatus.OK);
    }
}
