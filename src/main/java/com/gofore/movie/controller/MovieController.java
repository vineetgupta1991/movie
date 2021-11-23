package com.gofore.movie.controller;

import com.gofore.movie.annotation.ApiPageable;
import com.gofore.movie.enums.Genre;
import com.gofore.movie.model.dto.MovieDto;
import com.gofore.movie.service.MovieService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Movie")
@RestController
@RequestMapping("v1/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @ApiOperation(value = "Set movie's data")
    @ApiImplicitParam(name = "language", required = true, dataType = "string", paramType = "header", value = "", defaultValue = "eng")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Validation Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })

    @PostMapping(consumes={"application/json"})
    public void addMovie(@Valid @RequestBody MovieDto movieDto) {
        movieService.addMovie(movieDto);
    }

    @ApiOperation(value = "Get movie's data")
    @GetMapping()
    @ApiPageable
    public Page<MovieDto> listMovies(String movieName, Pageable pageable) {
       return movieService.listMovies(movieName, pageable);
    }

    @ApiOperation(value = "Get movie's data by Genre")
    @GetMapping("/genre/{genre}")
    @ApiPageable
    public Page<MovieDto> listMovies(@PathVariable("genre") Genre genre, Pageable pageable) {
        return movieService.getMoviesByGenre(genre, pageable);
    }

    @ApiOperation(value = "Update movie's data")
    @PutMapping()
    public MovieDto updateMovie(long id, @Valid @RequestBody MovieDto movieDto) {
        return movieService.updateMovie(id, movieDto);
    }

    @ApiOperation(value = "Delete movie")
    @DeleteMapping()
    public void deleteMovie(long id) {
        movieService.deleteMovie(id);
    }
}
