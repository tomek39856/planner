package com.github.tomek39856.planner.control;

import com.github.tomek39856.planner.boundary.RegisterApi;
import com.github.tomek39856.planner.entity.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetRegistersUseCaseTest extends ComponentTest {
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    GetRegistersUseCase getRegistersUseCase;

    @BeforeEach
    void setUp() {
        transferRepository.deleteAll();
        registerRepository.deleteAll();
    }

    @Test
    @DisplayName("should return all registers")
    void shouldReturnRegisters() {
        // given:
        Register firstRegister = new Register("test", BigDecimal.TEN);
        registerRepository.save(firstRegister);
        Register secondRegister = new Register("test2", BigDecimal.TEN);
        registerRepository.save(secondRegister);

        // when:
        List<RegisterApi> result = getRegistersUseCase.execute();

        // then:
        assertEquals(2, result.size());
        assertTrue(result.stream()
                .anyMatch(register -> register.getId().equals(firstRegister.toApi().getId()))
        );
        assertTrue(result.stream()
                .anyMatch(register -> register.getId().equals(secondRegister.toApi().getId()))
        );
    }

}