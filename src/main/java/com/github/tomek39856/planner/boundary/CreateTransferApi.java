package com.github.tomek39856.planner.boundary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CreateTransferApi {
  @NotEmpty
  private final String from;
  @NotEmpty
  private final String to;
  @Positive
  @NotNull
  private final BigDecimal amount;

  @JsonCreator
  public CreateTransferApi(@JsonProperty("from") String from,
                           @JsonProperty("to") String to,
                           @JsonProperty("amount") BigDecimal amount) {
    this.from = from;
    this.to = to;
    this.amount = amount;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public BigDecimal getAmount() {
    return amount;
  }
}
