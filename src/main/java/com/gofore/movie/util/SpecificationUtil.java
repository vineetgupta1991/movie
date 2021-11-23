package com.gofore.movie.util;

import com.gofore.movie.model.dto.RequestCriteriaDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationUtil {

    public static <T> Specification<T> createSpecification(RequestCriteriaDto input) {
        switch (input.getOperationType()){

            case EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(input.getKey()),
                                castToRequiredType(root.get(input.getKey()).getJavaType(),
                                        input.getValues().get(0)));

            case NOT_EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(input.getKey()),
                                castToRequiredType(root.get(input.getKey()).getJavaType(),
                                        input.getValues().get(0)));

            case LIKE:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getKey()),
                                "%"+input.getValues().get(0)+"%");

            case IN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(input.getKey()))
                                .value(castToRequiredType(
                                        root.get(input.getKey()).getJavaType(),
                                        input.getValues()));

            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }

    private static Object castToRequiredType(Class fieldType, String value) {
        if(fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if(fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if(Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        } else if(String.class.isAssignableFrom(fieldType)) {
            return String.valueOf(value);
        }
        return null;
    }

    private static Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }
}
