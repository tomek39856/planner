package com.github.tomek39856.planner.control;

import com.github.tomek39856.planner.entity.Register;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("demo")
public class DemoRegisterInitializer implements InitializingBean {
    private final RegisterRepository registerRepository;

    public DemoRegisterInitializer(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (registersAreNotInitialized()) {
            registerRepository.save(new Register("Wallet", BigDecimal.valueOf(1000)));
            registerRepository.save(new Register("Savings", BigDecimal.valueOf(5000)));
            registerRepository.save(new Register("Insurance policy", BigDecimal.ZERO));
            registerRepository.save(new Register("Food Expenses", BigDecimal.ZERO));
        }
    }

    private boolean registersAreNotInitialized() {
        return registerRepository.count() == 0;
    }
}
