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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
class AlbumServiceTests {

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

    @Test
    public void testGetAlbumById() throws Exception {

        List<Optional<Album>> albums = new ArrayList<>();
        albums.add(Optional.of(new Album(1L, "Total Life Forever", "Foals", 2010, Genre.INDIE, 15, true)));
        albums.add(Optional.of(new Album(2L, "Settle", "Disclosure", 2013, Genre.HOUSE, 21, true)));
        albums.add(Optional.of(new Album(3L, "Curtis", "Curtis Mayfield", 1970, Genre.SOUL, 0, false)));
        albums.add(Optional.of(new Album(4L, "Cher Lloyd", "Cher Lloyd", 2011, Genre.SOUL, 100, true)));
        albums.add(Optional.of(new Album(5L, "Meteora", "Linkin Park", 2003, Genre.METAL, 29, true)));

        when(mockAlbumRepository.findById(2L)).thenReturn(albums.get(1));
        when(mockAlbumRepository.findById(3L)).thenReturn(albums.get(2));

        assertThat(albumServiceImpl.getAlbumById(2L)).isEqualTo(albums.get(1));
        assertThat(albumServiceImpl.getAlbumById(3L)).isEqualTo(albums.get(2));
    }

    @Test
    public void testInsertAlbum() {

        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Total Life Forever", "Foals", 2010, Genre.INDIE, 15, true));
        albums.add(new Album(2L, "Settle", "Disclosure", 2013, Genre.HOUSE, 21, true));
        albums.add(new Album(3L, "Curtis", "Curtis Mayfield", 1970, Genre.SOUL, 0, false));
        albums.add(new Album(4L, "Cher Lloyd", "Cher Lloyd", 2011, Genre.SOUL, 100, true));
        albums.add(new Album(5L, "Meteora", "Linkin Park", 2003, Genre.METAL, 29, true));

        when(mockAlbumRepository.save(albums.get(0))).thenReturn(albums.get(0));
        when(mockAlbumRepository.save(albums.get(4))).thenReturn(albums.get(4));

        assertThat(albumServiceImpl.insertAlbum(albums.get(0))).isEqualTo(albums.get(0));
        assertThat(albumServiceImpl.insertAlbum(albums.get(4))).isEqualTo(albums.get(4));
    }

    @Test
    public void testUpdateAlbumById() {

        List<Optional<Album>> albums = new ArrayList<>();
        albums.add(Optional.of(new Album(1L, "Total Life Forever", "Foals", 2010, Genre.INDIE, 15, true)));
        albums.add(Optional.of(new Album(2L, "Settle", "Disclosure", 2013, Genre.HOUSE, 21, true)));
        albums.add(Optional.of(new Album(3L, "Curtis", "Curtis Mayfield", 1970, Genre.SOUL, 0, false)));
        albums.add(Optional.of(new Album(4L, "Cher Lloyd", "Cher Lloyd", 2011, Genre.SOUL, 100, true)));
        albums.add(Optional.of(new Album(5L, "Meteora", "Linkin Park", 2003, Genre.METAL, 29, true)));

        Album newAlbum = new Album(1L, "Isolation", "Kali Uchis", 2018, Genre.RHYTHM_AND_BLUES, 74, true);

        when(mockAlbumRepository.findById(1L)).thenReturn(albums.getFirst());
        when(mockAlbumRepository.save(newAlbum)).thenReturn(newAlbum);

        assertThat(albumServiceImpl.updateAlbumById(1L, newAlbum)).isEqualTo(newAlbum);
    }

    @Test
    public void testDeleteAlbumById() {

        List<Optional<Album>> albums = new ArrayList<>();
        albums.add(Optional.of(new Album(1L, "Total Life Forever", "Foals", 2010, Genre.INDIE, 15, true)));
        albums.add(Optional.of(new Album(2L, "Settle", "Disclosure", 2013, Genre.HOUSE, 21, true)));
        albums.add(Optional.of(new Album(3L, "Curtis", "Curtis Mayfield", 1970, Genre.SOUL, 0, false)));
        albums.add(Optional.of(new Album(4L, "Cher Lloyd", "Cher Lloyd", 2011, Genre.SOUL, 100, true)));
        albums.add(Optional.of(new Album(5L, "Meteora", "Linkin Park", 2003, Genre.METAL, 29, true)));

        when(mockAlbumRepository.findById(1L)).thenReturn(albums.getFirst());
        when(mockAlbumRepository.findById(2L)).thenReturn(Optional.empty());

        assertThat(albumServiceImpl.deleteAlbumById(1L)).isEqualTo(1);
        assertThat(albumServiceImpl.deleteAlbumById(2L)).isEqualTo(0);
    }
}