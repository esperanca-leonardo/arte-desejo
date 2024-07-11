package com.esperanca.projects.artedesejo.domain.saleorder.converter;

import com.esperanca.projects.artedesejo.domain.saleorder.entity.SaleOrder;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderInput;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderOutput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SaleOrderConverterImpl implements SaleOrderConverter
{
  private final ModelMapper modelMapper;

  @Override
  public SaleOrder toEntity(SaleOrderInput saleOrderInput)
  {
    return this.modelMapper.map(saleOrderInput, SaleOrder.class);
  }

  @Override
  public SaleOrderOutput toOutput(SaleOrder entity)
  {
    return this.modelMapper.map(entity, SaleOrderOutput.class);
  }

  @Override
  public List<SaleOrderOutput> toCollectionOutput(List<SaleOrder> saleOrders)
  {
    return saleOrders.stream()
        .map(this::toOutput)
        .toList();
  }
}
