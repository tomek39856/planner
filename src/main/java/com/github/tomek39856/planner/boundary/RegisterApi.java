package com.github.tomek39856.planner.boundary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class RegisterApi {
  private final String id;
  private final String name;
  private final BigDecimal balance;

  @JsonCreator
  public RegisterApi(String id, String name, BigDecimal balance) {
    this.id = id;
    this.name = name;
    this.balance = balance;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getBalance() {
    return balance;
  }
}
