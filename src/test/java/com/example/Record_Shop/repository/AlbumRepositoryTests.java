package com.example.Record_Shop.repository;

import com.example.Record_Shop.data.Album;
import com.example.Record_Shop.data.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        albumRepository.saveAll(expectedList);

        Iterable<Album> albums = albumRepository.findAll();

        assertThat(albums).hasSize(5);
        assertThat(expectedList).isEqualTo(albums);
    }

    @Test
    public void testGetAlbumByID() {

        List<Album> expectedList = new ArrayList<>();
        expectedList.add(new Album(1L, "Total Life Forever", "Foals", 2010, Genre.INDIE, 15, true));
        expectedList.add(new Album(2L, "Settle", "Disclosure", 2013, Genre.HOUSE, 21, true));
        expectedList.add(new Album(3L, "Curtis", "Curtis Mayfield", 1970, Genre.SOUL, 0, false));
        expectedList.add(new Album(4L, "Cher Lloyd", "Cher Lloyd", 2011, Genre.SOUL, 100, true));
        expectedList.add(new Album(5L, "Meteora", "Linkin Park", 2003, Genre.METAL, 29, true));

        albumRepository.saveAll(expectedList);

        assertThat(albumRepository.findById(3L)).isEqualTo(Optional.of(expectedList.get(2)));
        assertThat(albumRepository.findById(5L)).isEqualTo(Optional.of(expectedList.get(4)));
    }

}