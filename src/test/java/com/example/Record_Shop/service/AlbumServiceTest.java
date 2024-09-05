package com.example.Record_Shop.service;

import com.example.Record_Shop.data.Album;
import com.example.Record_Shop.data.Genre;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.Record_Shop.repository.AlbumRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
class AlbumServiceTest {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumServiceImpl albumServiceImpl;

    @Test
    public void testGetAllAlbums() throws Exception {

        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Total Life Forever", "Foals", 2010, Genre.INDIE, 15, true));
        albums.add(new Album(2L, "Settle", "Disclosure", 2013, Genre.HOUSE, 21, true));
        albums.add(new Album(3L, "Curtis", "Curtis Mayfield", 1970, Genre.SOUL, 0, false));
        albums.add(new Album(4L, "Cher Lloyd", "Cher Lloyd", 2011, Genre.SOUL, 100, true));
        albums.add(new Album(5L, "Meteora", "Linkin Park", 2003, Genre.METAL, 29, true));

        when(mockAlbumRepository.findAll()).thenReturn(albums);

        List<Album> actualResult = albumServiceImpl.getAllAlbums();

        assertThat(actualResult).hasSize(5);
        assertThat(actualResult).isEqualTo(albums);
    }
}