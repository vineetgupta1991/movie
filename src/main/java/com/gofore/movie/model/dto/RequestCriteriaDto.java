package com.gofore.movie.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gofore.movie.enums.QueryOperator;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
public class RequestCriteriaDto implements Serializable {
	@NotNull(message = "key cannot be empty")
	private String key;
	@NotNull(message = "value cannot be empty")
	private List<String> values;
	@JsonIgnoreProperties
	private QueryOperator operationType;
}