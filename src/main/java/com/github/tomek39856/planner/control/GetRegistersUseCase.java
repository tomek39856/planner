package com.github.tomek39856.planner.control;

import com.github.tomek39856.planner.boundary.RegisterApi;
import com.github.tomek39856.planner.entity.Register;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GetRegistersUseCase {
    private final RegisterRepository registerRepository;

    public GetRegistersUseCase(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Transactional
    public List<RegisterApi> execute() {
        return StreamSupport.stream(registerRepository.findAll().spliterator(), false)
                .map(Register::toApi)
                .collect(Collectors.toList());
    }
}
