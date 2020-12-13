package com.github.tomek39856.planner.control;

import com.github.tomek39856.planner.boundary.CreateTransferApi;
import com.github.tomek39856.planner.boundary.RegisterApi;
import com.github.tomek39856.planner.entity.NotEnoughMoneyInRegisterException;
import com.github.tomek39856.planner.entity.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteTransferUseCaseTest extends ComponentTest {
    @Autowired
    ExecuteTransferUseCase executeTransferUseCase;
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
    @DisplayName("should transfer money between registers when fund available")
    void shouldTransfer() {
        // given:
        RegisterApi register = saveRegisterWithNameAndBalance("register1", BigDecimal.valueOf(1500));
        RegisterApi register2 =saveRegisterWithNameAndBalance("register2", BigDecimal.valueOf(200));

        // when:
        executeTransferUseCase.execute(new CreateTransferApi(register.getId(), register2.getId(), BigDecimal.valueOf(100)));

        // then:
        assertEquals(1400l, getBalance(register.getId()).longValue());
        assertEquals(300l, getBalance(register2.getId()).longValue());
    }

    @Test
    @DisplayName("should not transfer money and rollback whole operation when not enough found")
    void shouldThrowWhenNotEnoughFunds() {
        // given:
        NotEnoughMoneyInRegisterException e = null;
        RegisterApi register = saveRegisterWithNameAndBalance("register1", BigDecimal.valueOf(1500));
        RegisterApi register2 =saveRegisterWithNameAndBalance("register2", BigDecimal.valueOf(200));

        // when:
        try {
            executeTransferUseCase.execute(new CreateTransferApi(register.getId(), register2.getId(), BigDecimal.valueOf(10000)));
        } catch (NotEnoughMoneyInRegisterException ex) {
            e = ex;
        }

        // then:
        assertNotNull(e);
        assertEquals(1500l, getBalance(register.getId()).longValue());
        assertEquals(200l, getBalance(register2.getId()).longValue());
    }

    @Test
    @DisplayName("should throw when register not found")
    void shouldThrowWhenRegisterNotFound() {
        // given:
        CreateTransferApi api = new CreateTransferApi("fake-id", "fake-id", BigDecimal.valueOf(10000));

        // expect:
        assertThrows(RegisterNotFoundException.class, () -> executeTransferUseCase.execute(api));
    }

    private RegisterApi saveRegisterWithNameAndBalance(String name, BigDecimal balance) {
        Register register = new Register(name, balance);
        registerRepository.save(register);
        return register.toApi();
    }

    private BigDecimal getBalance(String registerId) {
        return registerRepository.findById(registerId)
                .map(Register::toApi)
                .map(RegisterApi::getBalance)
                .get();
    }
}