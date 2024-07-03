package com.esperanca.projects.artedesejo.domain.product.entity;

import com.esperanca.projects.artedesejo.core.entity.BaseEntity;
import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity
{
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

  @ManyToOne
  private Supplier supplier;
}
