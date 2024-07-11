package com.esperanca.projects.artedesejo.domain.supplier.service;

import com.esperanca.projects.artedesejo.core.contracts.base.BaseService;
import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;
import com.esperanca.projects.artedesejo.domain.supplier.exceptions.SupplierNotFoundException;

public interface SupplierService extends BaseService<Supplier>
{
  @Override
  Supplier findById(Long id) throws SupplierNotFoundException;
}
