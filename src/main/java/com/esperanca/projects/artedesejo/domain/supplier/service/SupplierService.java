package com.esperanca.projects.artedesejo.domain.supplier.service;

import com.esperanca.projects.artedesejo.domain.supplier.exceptions.SupplierNotFoundException;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierInput;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierOutput;

import java.util.List;

public interface SupplierService
{
  List<SupplierOutput> findAll();

  SupplierOutput findById(Long id) throws SupplierNotFoundException;

  SupplierOutput save(SupplierInput supplierInput);

  SupplierOutput updateById(Long id, SupplierInput supplierInput)
      throws SupplierNotFoundException;

  void deleteById(Long id);
}
