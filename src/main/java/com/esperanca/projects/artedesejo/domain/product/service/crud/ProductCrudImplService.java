package com.esperanca.projects.artedesejo.domain.product.service.crud;

import com.esperanca.projects.artedesejo.core.contracts.helpers.copier.CopierHelper;
import com.esperanca.projects.artedesejo.domain.product.converter.ProductConverterHelper;
import com.esperanca.projects.artedesejo.domain.product.entity.Product;
import com.esperanca.projects.artedesejo.domain.product.exceptions.ProductNotFoundException;
import com.esperanca.projects.artedesejo.domain.product.models.ProductInput;
import com.esperanca.projects.artedesejo.domain.product.models.ProductOutput;
import com.esperanca.projects.artedesejo.domain.product.repository.ProductRepository;
import com.esperanca.projects.artedesejo.domain.product.service.helper.foreignkey.ProductForeignKeyHelper;
import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCrudImplService implements ProductCrudService
{
  private final ProductConverterHelper converter;
  private final ProductRepository repository;
  private final CopierHelper copierHelper;
  private final ProductForeignKeyHelper foreignKeyHelper;

  @Override
  public List<ProductOutput> findAll()
  {
    return this.repository.findAll()
        .stream()
        .map(this.converter::toOutput)
        .toList();
  }

  @Override
  public ProductOutput findById(Long id) throws ProductNotFoundException
  {
    return this.repository.findById(id)
        .map(converter::toOutput)
        .orElseThrow(() -> new ProductNotFoundException(id));
  }

  @Override
  public ProductOutput save(ProductInput productInput)
  {
    final Supplier supplier = this.foreignKeyHelper.getEntity(productInput);
    Product product = this.converter.toEntity(productInput);

    product.setSupplier(supplier);
    product = this.repository.save(product);

    return this.converter.toOutput(product);
  }

  @Override
  public ProductOutput updateById(Long id, ProductInput productInput)
      throws ProductNotFoundException
  {
    Product product = this.repository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));

    final Supplier supplier = this.foreignKeyHelper.getEntity(productInput);

    product.setSupplier(supplier);
    this.copierHelper.copyProperties(productInput, product);
    product = this.repository.save(product);

    return this.converter.toOutput(product);
  }

  @Override
  public void deleteById(Long id)
  {
    this.findById(id);
    this.repository.deleteById(id);
  }
}
