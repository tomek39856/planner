package com.github.tomek39856.planner.boundary;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationErrorPathExtractor {
    public static <K> List<String> getPropertyPaths(Set<ConstraintViolation<K>> validationResults) {
        return validationResults.stream()
                .map(ConstraintViolation::getPropertyPath)
                .map(Path::toString)
                .collect(Collectors.toList());
    }
}
