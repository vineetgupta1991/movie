package com.gofore.movie.mapper;

import com.gofore.movie.enums.Genre;
import com.gofore.movie.model.dto.MovieDto;
import com.gofore.movie.model.entity.MovieEntity;
import com.gofore.movie.model.entity.MovieGenreMapEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "personEntityList", source = "characters")
    @Mapping(target = "movieGenreMapEntityList", source = "genres")
    MovieEntity dtoToEntity(MovieDto movieDto);

    MovieGenreMapEntity dtoToEntity(Genre genre);

    default List<MovieGenreMapEntity> movieEntityToMovieMapEntity(MovieEntity movieEntity) {
        return movieEntity.getMovieGenreMapEntityList().stream().map(movieGenreMapEntity -> {
            movieGenreMapEntity.setMovieEntity(movieEntity);
            return movieGenreMapEntity;
        }).collect(Collectors.toList());
    }

    default List<MovieGenreMapEntity> dtoToEntity(List<Genre> genres) {
        return genres.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    @Mapping(target = "characters", source = "personEntityList")
    MovieDto entityToDto(MovieEntity movieEntity);

    default Genre movieGenreEntityToDto(MovieGenreMapEntity movieGenreMapEntity) {
        return movieGenreMapEntity.getGenre();
    }

    default List<Genre> entityToDto(List<MovieGenreMapEntity> movieGenreMapEntityList) {
        return movieGenreMapEntityList.stream().map(this::movieGenreEntityToDto).collect(Collectors.toList());
    }

    default Page<MovieDto> entityToDto(Page<MovieEntity> movieEntityList) {
        return movieEntityList.map(movieEntity -> {
            MovieDto movieDto = entityToDto(movieEntity);
            movieDto.setGenres(entityToDto(movieEntity.getMovieGenreMapEntityList()));
            return movieDto;
        });
    }
}
