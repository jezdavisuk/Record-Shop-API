package com.example.Record_Shop.repository;

import com.example.Record_Shop.data.Album;
import com.example.Record_Shop.data.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlbumRepositoryTests {
    @Autowired
    private AlbumRepository albumRepository;

    @Test
    public void testGetAllAlbums() {

        List<Album> expectedList = new ArrayList<>();
        expectedList.add(new Album(1L, "Total Life Forever", "Foals", 2010, Genre.INDIE, 15, true));
        expectedList.add(new Album(2L, "Settle", "Disclosure", 2013, Genre.HOUSE, 21, true));
        expectedList.add(new Album(3L, "Curtis", "Curtis Mayfield", 1970, Genre.SOUL, 0, false));
        expectedList.add(new Album(4L, "Cher Lloyd", "Cher Lloyd", 2011, Genre.SOUL, 100, true));
        expectedList.add(new Album(5L, "Meteora", "Linkin Park", 2003, Genre.METAL, 29, true));


        Album album1 = new Album(1L, "Total Life Forever", "Foals", 2010, Genre.INDIE, 15, true);
        albumRepository.save(album1);
        Album album2 = new Album(2L, "Settle", "Disclosure", 2013, Genre.HOUSE, 21, true);
        albumRepository.save(album2);
        Album album3 = new Album(3L, "Curtis", "Curtis Mayfield", 1970, Genre.SOUL, 0, false);
        albumRepository.save(album3);
        Album album4 = new Album(4L, "Cher Lloyd", "Cher Lloyd", 2011, Genre.SOUL, 100, true);
        albumRepository.save(album4);
        Album album5 = new Album(5L, "Meteora", "Linkin Park", 2003, Genre.METAL, 29, true);
        albumRepository.save(album5);

        Iterable<Album> albums = albumRepository.findAll();

        assertThat(albums).hasSize(5);
        assertThat(expectedList).isEqualTo(albums);
    }
}