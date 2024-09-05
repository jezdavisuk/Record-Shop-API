package data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
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
    private String genre;

    @Column
    private int quantityInStock;

    @Column
    private boolean isAvailable;

}
