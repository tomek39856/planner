package com.github.tomek39856.planner.boundary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.tomek39856.planner.boundary.ValidationErrorPathExtractor.getPropertyPaths;
import static org.junit.jupiter.api.Assertions.*;

class CreateRegisterApiValidationTest {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("should fail when null values provided")
    void shouldFailOnNullValues() {
        // given:
        CreateRegisterApi createRegisterApi = new CreateRegisterApi(
                null,
                null
        );

        // when:
        Set<ConstraintViolation<CreateRegisterApi>> validationResults = validator.validate(createRegisterApi);

        // then:
        assertEquals(2, validationResults.size());
        assertTrue(getPropertyPaths(validationResults).contains("name"));
        assertTrue(getPropertyPaths(validationResults).contains("balance"));
    }

    @Test
    @DisplayName("should fail when negative balance provided")
    void shouldFailOnNegativeBalance() {
        // given:
        CreateRegisterApi createRegisterApi = new CreateRegisterApi(
                "test",
                BigDecimal.valueOf(-10)
        );

        // when:
        Set<ConstraintViolation<CreateRegisterApi>> validationResults = validator.validate(createRegisterApi);

        // then:
        assertEquals(1, validationResults.size());
        assertTrue(getPropertyPaths(validationResults).contains("balance"));
    }

    @Test
    @DisplayName("should pass when correct values provided")
    void shouldPassOnCorrectValues() {
        // given:
        CreateRegisterApi createRegisterApi = new CreateRegisterApi(
                "test",
                BigDecimal.TEN
        );

        // when:
        Set<ConstraintViolation<CreateRegisterApi>> validationResults = validator.validate(createRegisterApi);

        // then:
        assertEquals(0, validationResults.size());
    }
}