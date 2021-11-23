package com.gofore.movie.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams(
		{
				@ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "Results page you want to retrieve. (default: 0)", example = "0"),
				@ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "Number of records per page. (default: 20)", example = "20"),
				@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property.")
		}
)
public @interface ApiPageable {
}
