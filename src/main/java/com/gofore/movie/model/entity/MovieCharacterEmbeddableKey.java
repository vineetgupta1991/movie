package com.gofore.movie.model.entity;

import com.gofore.movie.enums.Genre;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MovieCharacterEmbeddableKey implements Serializable {

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "movie_id")
    private Long movieId;
}
