package com.esperanca.projects.artedesejo.domain.supplier.service.crud;

import com.esperanca.projects.artedesejo.core.contracts.crud.CrudService;
import com.esperanca.projects.artedesejo.domain.supplier.exceptions.SupplierInUseException;
import com.esperanca.projects.artedesejo.domain.supplier.exceptions.SupplierNotFoundException;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierInput;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierOutput;

import java.util.List;

public interface SupplierCrudService extends CrudService<SupplierOutput, SupplierInput, Long>
{
  @Override
  List<SupplierOutput> findAll();

  @Override
  SupplierOutput findById(Long id) throws SupplierNotFoundException;

  @Override
  SupplierOutput save(SupplierInput supplierInput);

  @Override
  SupplierOutput updateById(Long id, SupplierInput supplierInput)
      throws SupplierNotFoundException;

  @Override
  void deleteById(Long id) throws SupplierInUseException;
}
