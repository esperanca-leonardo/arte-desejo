package com.esperanca.projects.artedesejo.domain.saleorder.entity;

import com.esperanca.projects.artedesejo.core.entity.BaseEntity;
import com.esperanca.projects.artedesejo.domain.consumer.entity.Consumer;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class SaleOrder extends BaseEntity
{
  @ManyToOne
  private Consumer consumer;

  private BigDecimal total;
}
