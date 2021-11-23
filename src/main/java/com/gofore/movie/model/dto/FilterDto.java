package com.gofore.movie.model.dto;

import com.gofore.movie.enums.Genre;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Filter data")
public class FilterDto {

    @ApiModelProperty(value = "movieName")
    private String movieName;

    @ApiModelProperty(value = "genre")
    private Genre genre;
}
