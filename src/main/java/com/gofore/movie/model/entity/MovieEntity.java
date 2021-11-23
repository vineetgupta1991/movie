package com.gofore.movie.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private String movieName;

    @Column()
    private Date releaseDate;

    @Column()
    private int ageRecommendation;

    @Column()
    private int rating;

    @Column()
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movieEntity", cascade = CascadeType.ALL)
    private List<MovieCharacterMapEntity> movieCharacterMapEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movieEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieGenreMapEntity> movieGenreMapEntityList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "MovieCharacterMap",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<PersonEntity> personEntityList;
}
