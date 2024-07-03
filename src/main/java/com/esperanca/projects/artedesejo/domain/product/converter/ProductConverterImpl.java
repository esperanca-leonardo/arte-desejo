package com.esperanca.projects.artedesejo.domain.product.converter;

import com.esperanca.projects.artedesejo.domain.product.entity.Product;
import com.esperanca.projects.artedesejo.domain.product.models.ProductInput;
import com.esperanca.projects.artedesejo.domain.product.models.ProductOutput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductConverterImpl implements ProductConverter
{
  private final ModelMapper modelMapper;

  @Override
  public Product toEntity(ProductInput productInput)
  {
    return this.modelMapper.map(productInput, Product.class);
  }

  @Override
  public ProductOutput toOutput(Product product)
  {
    return this.modelMapper.map(product, ProductOutput.class);
  }

  @Override
  public List<ProductOutput> toCollectionOutput(List<Product> products)
  {
    return products.stream()
        .map(this::toOutput)
        .toList();
  }
}
