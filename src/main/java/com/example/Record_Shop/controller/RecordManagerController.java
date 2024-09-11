package com.example.Record_Shop.controller;

import com.example.Record_Shop.data.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Record_Shop.service.AlbumService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/records")
public class RecordManagerController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() { return new ResponseEntity<>(albumService.getAllAlbums(), HttpStatus.OK); }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(albumService.getAlbumById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        Album newAlbum = albumService.insertAlbum(album);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("album", "/api/v1/records/" + newAlbum.getId().toString());
        return new ResponseEntity<>(newAlbum, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> placeAlbumById(@PathVariable("id") Long id, @RequestBody Album album) {
        return new ResponseEntity<>(albumService.placeAlbumById(id, album), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Album> updateAlbumById(@PathVariable("id") Long id, @RequestBody Album album) {
        return new ResponseEntity<>(albumService.updateAlbumById(id, album), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbumById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(albumService.deleteAlbumById(id), HttpStatus.OK);
    }
}
