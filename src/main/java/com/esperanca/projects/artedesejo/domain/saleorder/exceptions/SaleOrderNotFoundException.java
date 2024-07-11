package com.esperanca.projects.artedesejo.domain.saleorder.exceptions;

import com.esperanca.projects.artedesejo.core.exceptions.EntityNotFoundException;

import static java.lang.String.format;

public class SaleOrderNotFoundException extends EntityNotFoundException
{
  private static final String MESSAGE = "SaleOrder not found with id: %d";

  public SaleOrderNotFoundException(Long id)
  {
    super(format(MESSAGE, id));
  }
}
