package com.example.Record_Shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.Record_Shop.data.Album;
import com.example.Record_Shop.data.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.Record_Shop.service.AlbumServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
class RecordManagerControllerTests {

    @Mock
    private AlbumServiceImpl mockAlbumServiceImpl;

    @InjectMocks
    private RecordManagerController recordManagerController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(recordManagerController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testGetAllAlbums() throws Exception {

        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Total Life Forever", "Foals", 2010, Genre.INDIE, 15, true));
        albums.add(new Album(2L, "Settle", "Disclosure", 2013, Genre.HOUSE, 21, true));
        albums.add(new Album(3L, "Curtis", "Curtis Mayfield", 1970, Genre.SOUL, 0, false));
        albums.add(new Album(4L, "Cher Lloyd", "Cher Lloyd", 2011, Genre.SOUL, 100, true));
        albums.add(new Album(5L, "Meteora", "Linkin Park", 2003, Genre.METAL, 29, true));

        when(mockAlbumServiceImpl.getAllAlbums()).thenReturn(albums);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/records/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value("INDIE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].recordName").value("Settle"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantityInStock").value(21))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].artist").value("Curtis Mayfield"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].yearOfRelease").value(1970))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].artist").value("Cher Lloyd"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4].recordName").value("Meteora"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4].genre").value("METAL"));
    }

    @Test
    public void testGetAlbumById() throws Exception {

        List<Optional<Album>> albums = new ArrayList<>();
        albums.add(Optional.of(new Album(1L, "Total Life Forever", "Foals", 2010, Genre.INDIE, 15, true)));
        albums.add(Optional.of(new Album(2L, "Settle", "Disclosure", 2013, Genre.HOUSE, 21, true)));
        albums.add(Optional.of(new Album(3L, "Curtis", "Curtis Mayfield", 1970, Genre.SOUL, 0, false)));
        albums.add(Optional.of(new Album(4L, "Cher Lloyd", "Cher Lloyd", 2011, Genre.SOUL, 100, true)));
        albums.add(Optional.of(new Album(5L, "Meteora", "Linkin Park", 2003, Genre.METAL, 29, true)));

        when(mockAlbumServiceImpl.getAlbumById(2L)).thenReturn(albums.get(1));
        when(mockAlbumServiceImpl.getAlbumById(3L)).thenReturn(albums.get(2));

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/records/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Disclosure"));

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/records/3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Curtis Mayfield"));
    }

    @Test
    public void testPostMappingAddAlbum() throws Exception {

        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Total Life Forever", "Foals", 2010, Genre.INDIE, 15, true));
        albums.add(new Album(2L, "Settle", "Disclosure", 2013, Genre.HOUSE, 21, true));
        albums.add(new Album(3L, "Curtis", "Curtis Mayfield", 1970, Genre.SOUL, 0, false));
        albums.add(new Album(4L, "Cher Lloyd", "Cher Lloyd", 2011, Genre.SOUL, 100, true));
        albums.add(new Album(5L, "Meteora", "Linkin Park", 2003, Genre.METAL, 29, true));

        when(mockAlbumServiceImpl.insertAlbum(albums.get(2))).thenReturn(albums.get(2));
        when(mockAlbumServiceImpl.insertAlbum(albums.get(3))).thenReturn(albums.get(3));

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/records/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(albums.get(2))))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Curtis Mayfield"));

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/records/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(albums.get(3))))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Cher Lloyd"));

        verify(mockAlbumServiceImpl, times(1)).insertAlbum(albums.get(2));
        verify(mockAlbumServiceImpl, times(1)).insertAlbum(albums.get(3));
    }
}