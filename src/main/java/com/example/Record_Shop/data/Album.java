package com.example.Record_Shop.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Records")
@Builder
public class Album {

    @Id
    @GeneratedValue
    @Column(updatable=false,nullable=false)
    private Long id;

    @Column
    private String recordName;

    @Column
    private String artist;

    @Column
    private int yearOfRelease;

    @Column
    private Genre genre;

    @Column
    private int quantityInStock;

    @Column
    private boolean isAvailable;

}
