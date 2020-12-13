package com.github.tomek39856.planner.control;

import com.github.tomek39856.planner.boundary.RegisterApi;
import com.github.tomek39856.planner.boundary.CreateSupplyApi;
import com.github.tomek39856.planner.entity.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ChargeRegisterUseCaseTest extends ComponentTest {
    @Autowired
    ChargeRegisterUseCase chargeRegisterUseCase;
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    TransferRepository transferRepository;

    @BeforeEach
    void setUp() {
        transferRepository.deleteAll();
        registerRepository.deleteAll();
    }

    @Test
    @DisplayName("should charge when given amount of money provided")
    void shouldCharge() {
        // given:
        Register register = new Register("test", BigDecimal.TEN);
        registerRepository.save(register);

        // when:
        chargeRegisterUseCase.charge(register.toApi().getId(), new CreateSupplyApi(BigDecimal.TEN));

        // then:
        BigDecimal balance = getBalance(register);
        assertEquals(BigDecimal.valueOf(20).longValue(), balance.longValue());
    }

    private BigDecimal getBalance(Register register) {
        return registerRepository.findById(register.toApi().getId())
                .map(Register::toApi)
                .map(RegisterApi::getBalance)
                .get();
    }
}