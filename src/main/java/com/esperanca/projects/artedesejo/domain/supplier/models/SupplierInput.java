package com.esperanca.projects.artedesejo.domain.supplier.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SupplierInput
{
  @NotBlank
  private String name;
}
