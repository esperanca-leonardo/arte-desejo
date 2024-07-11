package com.esperanca.projects.artedesejo.domain.supplier.converter;

import com.esperanca.projects.artedesejo.core.contracts.helpers.converter.ConverterHelper;
import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierInput;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierOutput;

import java.util.List;

public interface SupplierConverterHelper
    extends ConverterHelper<Supplier, SupplierInput, SupplierOutput>
{
  @Override
  Supplier toEntity(SupplierInput supplierInput);

  @Override
  SupplierOutput toOutput(Supplier supplier);

  @Override
  List<SupplierOutput> toCollectionOutput(List<Supplier> suppliers);
}
