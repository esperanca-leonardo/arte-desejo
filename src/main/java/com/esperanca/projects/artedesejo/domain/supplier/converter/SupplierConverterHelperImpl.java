package com.esperanca.projects.artedesejo.domain.supplier.converter;

import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierInput;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierOutput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SupplierConverterHelperImpl implements SupplierConverterHelper
{
  private final ModelMapper modelMapper;

  @Override
  public Supplier toEntity(SupplierInput supplierInput)
  {
    return this.modelMapper.map(supplierInput, Supplier.class);
  }

  @Override
  public SupplierOutput toOutput(Supplier supplier)
  {
    return this.modelMapper.map(supplier, SupplierOutput.class);
  }

  @Override
  public List<SupplierOutput> toCollectionOutput(List<Supplier> suppliers)
  {
    return suppliers.stream()
        .map(this::toOutput)
        .toList();
  }
}
