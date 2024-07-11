package com.esperanca.projects.artedesejo.domain.saleorder.models;

import com.esperanca.projects.artedesejo.core.annotations.Exists;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerIdInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SaleOrderInput
{
  @Valid
  @Exists
  @NotNull
  private ConsumerIdInput consumerIdInput;

  @NotNull
  @PositiveOrZero
  private BigDecimal total;
}
