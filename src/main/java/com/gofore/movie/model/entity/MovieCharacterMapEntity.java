package com.gofore.movie.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MovieCharacterMap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieCharacterMapEntity {

    @EmbeddedId
    private MovieCharacterEmbeddableKey movieCharacterEmbeddableKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;
}
