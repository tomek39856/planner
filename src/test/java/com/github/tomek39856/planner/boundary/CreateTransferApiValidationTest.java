package com.github.tomek39856.planner.boundary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

import static com.github.tomek39856.planner.boundary.ValidationErrorPathExtractor.getPropertyPaths;
import static org.junit.jupiter.api.Assertions.*;

class CreateTransferApiValidationTest {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("should fail when null values provided")
    void shouldFailOnNullValues() {
        // given:
        CreateTransferApi createTransferApi = new CreateTransferApi(
                null,
                null,
                null
        );

        // when:
        Set<ConstraintViolation<CreateTransferApi>> validationResults = validator.validate(createTransferApi);

        // then:
        assertEquals(3, validationResults.size());
        assertTrue(getPropertyPaths(validationResults).contains("from"));
        assertTrue(getPropertyPaths(validationResults).contains("to"));
        assertTrue(getPropertyPaths(validationResults).contains("amount"));
    }

    @Test
    @DisplayName("should fail when negative balance provided")
    void shouldFailOnNegativeBalance() {
        // given:
        CreateTransferApi createTransferApi = new CreateTransferApi(
                "HG",
                "HJGJ",
                BigDecimal.valueOf(-10)
        );

        // when:
        Set<ConstraintViolation<CreateTransferApi>> validationResults = validator.validate(createTransferApi);

        // then:
        assertEquals(1, validationResults.size());
        assertTrue(getPropertyPaths(validationResults).contains("amount"));
    }

    @Test
    @DisplayName("should pass when correct values provided")
    void shouldPassOnCorrectValues() {
        // given:
        CreateTransferApi createTransferApi = new CreateTransferApi(
                "tes",
                "test",
                BigDecimal.valueOf(10)
        );

        // when:
        Set<ConstraintViolation<CreateTransferApi>> validationResults = validator.validate(createTransferApi);

        // then:
        assertEquals(0, validationResults.size());
    }
}