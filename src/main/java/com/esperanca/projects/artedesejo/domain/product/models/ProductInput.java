package com.esperanca.projects.artedesejo.domain.product.models;

import com.esperanca.projects.artedesejo.core.annotations.Exists;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierIdInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductInput
{
  @NotBlank
  private String name;
  private String description;
  private String color;

  @NotBlank
  private String brand;
  private String flavor;
  private String sensation;
  private String category;
  private String subCategory;
  private String targetAudience;
  private String size;
  private String fabric;
  private String additionalInformation;

  @NotNull
  @PositiveOrZero
  private Long quantity;

  @NotNull
  @PositiveOrZero
  private Long stockQuantity;

  @NotNull
  @PositiveOrZero
  private BigDecimal costPrice;

  @NotNull
  @PositiveOrZero
  private BigDecimal salePrice;

  @Valid
  @Exists
  @NotNull
  private SupplierIdInput supplierIdInput;
}
