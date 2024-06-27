package com.esperanca.projects.artedesejo.domain.supplier.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class SupplierIdInput
{
  @NotNull
  @Positive
  private Long id;
}
