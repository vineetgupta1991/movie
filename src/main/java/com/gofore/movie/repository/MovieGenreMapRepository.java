package com.gofore.movie.repository;

import com.gofore.movie.model.entity.MovieGenreMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreMapRepository extends PagingAndSortingRepository<MovieGenreMapEntity, Long>, JpaRepository<MovieGenreMapEntity, Long>,
        JpaSpecificationExecutor<MovieGenreMapEntity> {
}
