package com.gofore.movie.model.dto;

import com.gofore.movie.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private String movieName;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date releaseDate;
    private List<Genre> genres;

    @Min(1)
    private int ageRecommendation;
    @Max(10)
    @Min(1)
    private int rating;
    private List<PersonDto> characters;
    @Size(max = 500)
    private String description;
}
