package com.github.tomek39856.planner.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Transfer {
  @Id
  private final String id = UUID.randomUUID().toString();
  @ManyToOne(cascade = CascadeType.ALL)
  private Register from;
  @ManyToOne(cascade = CascadeType.ALL)
  private Register to;
  private BigDecimal amount;

  protected Transfer() {
  }

  private Transfer(Register from, Register to, BigDecimal amount) {
    this.from = from;
    this.to = to;
    this.amount = amount;
  }

  public static Transfer betweenRegisters(Register source, Register destination, BigDecimal amount) {
    Transfer transfer = new Transfer(source, destination, amount);
    source.applyOutgoingTransfer(transfer);
    destination.applyIncomingTransfer(transfer);
    return transfer;
  }

  public BigDecimal getAmount() {
    return amount;
  }
}
