package com.esperanca.projects.artedesejo.domain.saleorder.converter;

import com.esperanca.projects.artedesejo.core.contracts.helpers.converter.ConverterHelper;
import com.esperanca.projects.artedesejo.domain.saleorder.entity.SaleOrder;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderInput;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderOutput;

import java.util.List;

public interface SaleOrderConverter extends ConverterHelper<SaleOrder,
    SaleOrderInput, SaleOrderOutput>
{

  @Override
  SaleOrder toEntity(SaleOrderInput saleOrderInput);

  @Override
  SaleOrderOutput toOutput(SaleOrder entity);

  @Override
  List<SaleOrderOutput> toCollectionOutput(List<SaleOrder> saleOrders);
}
