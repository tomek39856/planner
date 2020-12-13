package com.github.tomek39856.planner.control;

import com.github.tomek39856.planner.boundary.CreateRegisterApi;
import com.github.tomek39856.planner.entity.Register;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateRegisterUseCase {
  private final RegisterRepository registerRepository;

  public CreateRegisterUseCase(RegisterRepository registerRepository) {
    this.registerRepository = registerRepository;
  }

  @Transactional
  public void execute(CreateRegisterApi registerApi) {
    registerRepository.save(Register.ofApi(registerApi));
  }
}
