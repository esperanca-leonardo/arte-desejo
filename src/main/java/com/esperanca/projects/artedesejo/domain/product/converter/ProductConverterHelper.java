package com.esperanca.projects.artedesejo.domain.product.converter;

import com.esperanca.projects.artedesejo.core.contracts.helpers.converter.ConverterHelper;
import com.esperanca.projects.artedesejo.domain.product.entity.Product;
import com.esperanca.projects.artedesejo.domain.product.models.ProductInput;
import com.esperanca.projects.artedesejo.domain.product.models.ProductOutput;

import java.util.List;

public interface ProductConverterHelper extends
                                        ConverterHelper<Product, ProductInput, ProductOutput>
{
  @Override
  Product toEntity(ProductInput productInput);

  @Override
  ProductOutput toOutput(Product product);

  @Override
  List<ProductOutput> toCollectionOutput(List<Product> products);
}
