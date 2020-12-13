package com.github.tomek39856.planner.boundary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CreateRegisterApi {
  @NotEmpty
  private final String name;
  @Positive
  @NotNull
  private final BigDecimal balance;

  @JsonCreator
  public CreateRegisterApi(@JsonProperty("name") String name,
                           @JsonProperty("balance") BigDecimal balance) {
    this.name = name;
    this.balance = balance;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getBalance() {
    return balance;
  }
}
