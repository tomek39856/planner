package com.github.tomek39856.planner.control;

import com.github.tomek39856.planner.boundary.CreateRegisterApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreateRegisterUseCaseTest extends ComponentTest {
    @Autowired
    CreateRegisterUseCase createRegisterUseCase;
    @Autowired
    RegisterRepository registerRepository;

    @BeforeEach
    void setUp() {
       registerRepository.deleteAll();
    }

    @Test
    @DisplayName("should create new register when correct data provided")
    void shouldCreateNewRegister() {
        // given:
        CreateRegisterApi createRegisterApi = new CreateRegisterApi(
                "test register",
                BigDecimal.valueOf(111)
        );

        // when:
        createRegisterUseCase.execute(createRegisterApi);

        // then:
        assertEquals(1, registerRepository.count());
    }
}