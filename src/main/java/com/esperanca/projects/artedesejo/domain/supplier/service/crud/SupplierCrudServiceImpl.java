package com.esperanca.projects.artedesejo.domain.supplier.service.crud;

import com.esperanca.projects.artedesejo.core.utils.propertycopier.PropertyCopier;
import com.esperanca.projects.artedesejo.domain.supplier.converter.SupplierConverter;
import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;
import com.esperanca.projects.artedesejo.domain.supplier.exceptions.SupplierInUseException;
import com.esperanca.projects.artedesejo.domain.supplier.exceptions.SupplierNotFoundException;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierInput;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierOutput;
import com.esperanca.projects.artedesejo.domain.supplier.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierCrudServiceImpl implements SupplierCrudService
{
  private final PropertyCopier copier;
  private final SupplierConverter converter;
  private final SupplierRepository repository;

  @Override
  public List<SupplierOutput> findAll()
  {
    return this.repository.findAll()
        .stream()
        .map(this.converter::toOutput)
        .toList();
  }

  @Override
  public SupplierOutput findById(Long id) throws SupplierNotFoundException
  {
    return this.repository.findById(id)
        .map(this.converter::toOutput)
        .orElseThrow(() -> new SupplierNotFoundException(id));
  }

  @Override
  public SupplierOutput save(SupplierInput supplierInput)
  {
    Supplier supplier = this.converter.toEntity(supplierInput);
    supplier = this.repository.save(supplier);
    return this.converter.toOutput(supplier);
  }

  @Override
  public SupplierOutput updateById(Long id, SupplierInput supplierInput)
      throws SupplierNotFoundException
  {
    Supplier savedSupplier = this.repository.findById(id)
        .orElseThrow(() -> new SupplierNotFoundException(id));

    this.copier.copyProperties(supplierInput, savedSupplier);

    savedSupplier = this.repository.save(savedSupplier);
    return this.converter.toOutput(savedSupplier);
  }

  @Override
  public void deleteById(Long id) throws SupplierInUseException
  {
    this.findById(id);

    try
    {
      this.repository.deleteById(id);
      this.repository.flush();
    }
    catch (DataIntegrityViolationException exception)
    {
      throw new SupplierInUseException(id);
    }
  }
}
