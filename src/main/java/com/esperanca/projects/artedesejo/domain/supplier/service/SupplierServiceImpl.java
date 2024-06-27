package com.esperanca.projects.artedesejo.domain.supplier.service;

import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;
import com.esperanca.projects.artedesejo.domain.supplier.exceptions.SupplierNotFoundException;
import com.esperanca.projects.artedesejo.domain.supplier.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierService
{
  private final SupplierRepository repository;

  @Override
  public Supplier findById(Long id) throws SupplierNotFoundException
  {
    return this.repository.findById(id)
        .orElseThrow(() -> new SupplierNotFoundException(id));
  }
}
