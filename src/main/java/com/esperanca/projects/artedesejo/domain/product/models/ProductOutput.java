package com.esperanca.projects.artedesejo.domain.product.models;

import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierOutput;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductOutput
{
  private Long id;
  private String name;
  private String description;
  private String color;
  private String brand;
  private String flavor;
  private String sensation;
  private String category;
  private String subCategory;
  private String targetAudience;
  private String size;
  private String fabric;
  private String additionalInformation;
  private Long quantity;
  private Long stockQuantity;
  private BigDecimal costPrice;
  private BigDecimal salePrice;
  private SupplierOutput supplier;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
