package com.esperanca.projects.artedesejo.domain.saleorder.models;

import com.esperanca.projects.artedesejo.core.models.BaseOutput;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerOutput;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaleOrderOutput extends BaseOutput
{
  private ConsumerOutput consumer;
  private BigDecimal total;
}
