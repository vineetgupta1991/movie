package com.gofore.movie.repository;

import com.gofore.movie.model.entity.MovieCharacterMapEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MovieCharacterMapRepository extends CrudRepository<MovieCharacterMapEntity, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM MovieCharacterMapEntity m WHERE m.movieEntity.id=:id")
    void deleteByMovieId(Long id);
}
