package com.github.tomek39856.planner.boundary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CreateSupplyApi {
    @Positive
    @NotNull
    private final Double amount;

    @JsonCreator
    public CreateSupplyApi(@JsonProperty("amount") BigDecimal amount) {
        this.amount = amount.doubleValue();
    }

    public Double getAmount() {
        return amount;
    }
}
