package com.esperanca.projects.artedesejo.domain.product.exceptions;

import com.esperanca.projects.artedesejo.core.exceptions.EntityNotFoundException;

import static java.lang.String.format;

public class ProductNotFoundException extends EntityNotFoundException
{
  private static final String MESSAGE = "Product not found with id: %d";

  public ProductNotFoundException(Long id)
  {
    super(format(MESSAGE, id));
  }
}
