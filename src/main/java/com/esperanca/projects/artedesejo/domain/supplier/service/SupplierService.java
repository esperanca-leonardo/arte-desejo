package com.esperanca.projects.artedesejo.domain.supplier.service;

import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;
import com.esperanca.projects.artedesejo.domain.supplier.exceptions.SupplierNotFoundException;

public interface SupplierService
{
  Supplier findById(Long id) throws SupplierNotFoundException;
}
