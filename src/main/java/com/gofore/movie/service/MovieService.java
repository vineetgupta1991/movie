package com.gofore.movie.service;

import com.gofore.movie.enums.Genre;
import com.gofore.movie.enums.QueryOperator;
import com.gofore.movie.mapper.MovieMapper;
import com.gofore.movie.model.dto.MovieDto;
import com.gofore.movie.model.dto.RequestCriteriaDto;
import com.gofore.movie.model.entity.MovieEntity;
import com.gofore.movie.model.entity.MovieGenreMapEntity;
import com.gofore.movie.repository.MovieCharacterMapRepository;
import com.gofore.movie.repository.MovieGenreMapRepository;
import com.gofore.movie.repository.MovieRepository;
import com.gofore.movie.util.SpecificationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gofore.movie.util.CriteriaUtil.generateRequestCriteria;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieGenreMapRepository movieGenreMapRepository;

    @Autowired
    private MovieCharacterMapRepository movieCharacterMapRepository;

    @Autowired
    private MovieMapper movieMapper;

    public void addMovie(MovieDto movieDto) {
        MovieEntity movieEntity = movieMapper.dtoToEntity(movieDto);
        List<MovieGenreMapEntity> movieGenreMapEntityList = movieMapper.movieEntityToMovieMapEntity(movieEntity);
        movieEntity.setMovieGenreMapEntityList(movieGenreMapEntityList);
        movieRepository.save(movieEntity);
    }

    public Page<MovieDto> listMovies(String movieName, Pageable pageable) {
        Specification<MovieEntity> specification = Specification.where(null);
        if (StringUtils.isNotBlank(movieName)) {
            RequestCriteriaDto requestCriteria = generateRequestCriteria("movieName", List.of(movieName), QueryOperator.EQUALS);
            specification = Specification.where(SpecificationUtil.createSpecification(requestCriteria));
        }

        return movieMapper.entityToDto(movieRepository.findAll(specification, pageable));
    }

    public Page<MovieDto> getMoviesByGenre(Genre genre, Pageable pageable) {
        RequestCriteriaDto requestCriteria = generateRequestCriteria("genre", List.of(genre.toString()), QueryOperator.EQUALS);
        Specification<MovieGenreMapEntity> specification = Specification.where(SpecificationUtil.createSpecification(requestCriteria));

        return movieMapper.entityToDto(movieGenreMapRepository.findAll(specification, pageable).map(MovieGenreMapEntity::getMovieEntity));
    }

    public MovieDto updateMovie(long id, MovieDto movieDto) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found");
        }

        MovieEntity movieEntity = movieMapper.dtoToEntity(movieDto);
        List<MovieGenreMapEntity> movieGenreMapEntityList = movieMapper.movieEntityToMovieMapEntity(movieEntity);
        movieEntity.setMovieGenreMapEntityList(movieGenreMapEntityList);
        movieEntity.setId(id);
        MovieEntity movieEntityUpdated = movieRepository.save(movieEntity);
        MovieDto movieResponse = movieMapper.entityToDto(movieEntityUpdated);
        movieResponse.setGenres(movieMapper.entityToDto(movieEntityUpdated.getMovieGenreMapEntityList()));
        return movieResponse;
    }

    public void deleteMovie(long id) {
        movieCharacterMapRepository.deleteByMovieId(id);
        movieRepository.deleteById(id);
    }
}
