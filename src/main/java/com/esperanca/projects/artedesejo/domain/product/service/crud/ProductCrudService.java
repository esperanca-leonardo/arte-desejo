package com.esperanca.projects.artedesejo.domain.product.service.crud;

import com.esperanca.projects.artedesejo.core.contracts.crud.ServiceCrud;
import com.esperanca.projects.artedesejo.domain.product.exceptions.ProductNotFoundException;
import com.esperanca.projects.artedesejo.domain.product.models.ProductInput;
import com.esperanca.projects.artedesejo.domain.product.models.ProductOutput;

import java.util.List;

public interface ProductCrudService extends ServiceCrud<ProductOutput, ProductInput, Long>
{
  @Override
  List<ProductOutput> findAll();

  @Override
  ProductOutput findById(Long id) throws ProductNotFoundException;

  @Override
  ProductOutput save(ProductInput input);

  @Override
  ProductOutput updateById(Long id, ProductInput input)
      throws ProductNotFoundException;

  @Override
  void deleteById(Long id);
}
