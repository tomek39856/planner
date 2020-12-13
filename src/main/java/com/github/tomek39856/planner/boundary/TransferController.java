package com.github.tomek39856.planner.boundary;

import com.github.tomek39856.planner.control.ExecuteTransferUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {
  private final ExecuteTransferUseCase executeTransferUseCase;

  public TransferController(ExecuteTransferUseCase executeTransferUseCase) {
    this.executeTransferUseCase = executeTransferUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody CreateTransferApi createTransferApi) {
    executeTransferUseCase.execute(createTransferApi);
  }
}
