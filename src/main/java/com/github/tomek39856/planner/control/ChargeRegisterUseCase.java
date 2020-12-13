package com.github.tomek39856.planner.control;

import com.github.tomek39856.planner.boundary.CreateSupplyApi;
import com.github.tomek39856.planner.entity.Register;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ChargeRegisterUseCase {
    private final RegisterRepository registerRepository;

    public ChargeRegisterUseCase(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Transactional
    public void charge(String id, CreateSupplyApi supply) {
        Register register = registerRepository.findById(id).orElseThrow(RegisterNotFoundException::new);
        register.charge(BigDecimal.valueOf(supply.getAmount()));
    }
}
