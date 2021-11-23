package com.gofore.movie.repository;

import com.gofore.movie.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, Long>, JpaRepository<MovieEntity, Long>,
        JpaSpecificationExecutor<MovieEntity> {
}
