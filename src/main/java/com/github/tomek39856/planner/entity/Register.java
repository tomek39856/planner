package com.github.tomek39856.planner.entity;

import com.github.tomek39856.planner.boundary.CreateRegisterApi;
import com.github.tomek39856.planner.boundary.RegisterApi;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Register {
  @Id
  private final String id = UUID.randomUUID().toString();
  private String name;
  private BigDecimal balance;

  protected Register() {}

  public Register(String name, BigDecimal balance) {
    this.name = name;
    this.balance = balance;
  }

  public static Register ofApi(CreateRegisterApi registerApi) {
    return new Register(
        registerApi.getName(),
        registerApi.getBalance()
    );
  }

  public void applyOutgoingTransfer(Transfer transfer) {
    if(balance.compareTo(transfer.getAmount()) < 0) {
      throw new NotEnoughMoneyInRegisterException();
    }
    balance = balance.add(transfer.getAmount().negate());
  }

  public void applyIncomingTransfer(Transfer transfer) {
    balance = balance.add(transfer.getAmount());
  }

  public void charge(BigDecimal amount) {
    balance = balance.add(amount);
  }

  public RegisterApi toApi() {
    return new RegisterApi(
      id,
      name,
      balance
    );
  }
}
