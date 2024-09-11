package com.example.Record_Shop.repository;

import com.example.Record_Shop.data.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {}
