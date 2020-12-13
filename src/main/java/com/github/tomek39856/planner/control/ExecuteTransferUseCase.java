package com.github.tomek39856.planner.control;

import com.github.tomek39856.planner.boundary.CreateTransferApi;
import com.github.tomek39856.planner.entity.Register;
import com.github.tomek39856.planner.entity.Transfer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ExecuteTransferUseCase {
  private final TransferRepository transferRepository;
  private final RegisterRepository registerRepository;

  public ExecuteTransferUseCase(TransferRepository transferRepository, RegisterRepository registerRepository) {
    this.transferRepository = transferRepository;
    this.registerRepository = registerRepository;
  }

  @Transactional
  public void execute(CreateTransferApi createTransferApi) {
    Register incomingRegister = registerRepository.findById(createTransferApi.getFrom())
            .orElseThrow(RegisterNotFoundException::new);
    Register outgoingRegister = registerRepository.findById(createTransferApi.getTo())
            .orElseThrow(RegisterNotFoundException::new);
    transferRepository.save(
            Transfer.betweenRegisters(
                    incomingRegister,
                    outgoingRegister,
                    createTransferApi.getAmount()
            )
    );
  }
}
