package com.gofore.movie.util;

import com.gofore.movie.enums.QueryOperator;
import com.gofore.movie.model.dto.RequestCriteriaDto;

import java.util.List;

public class CriteriaUtil {

    public static RequestCriteriaDto generateRequestCriteria(String key, List<String> value, QueryOperator queryOperator) {
          return RequestCriteriaDto.builder().key(key)
                    .operationType(queryOperator).values(value).build();
        }
}
