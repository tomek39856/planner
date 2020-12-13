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
            registerRepository.save(new Register("test", BigDecimal.TEN));
            registerRepository.save(new Register("test2", BigDecimal.TEN));
            registerRepository.save(new Register("test3", BigDecimal.TEN));
        }
    }

    private boolean registersAreNotInitialized() {
        return registerRepository.count() == 0;
    }
}
