package com.esperanca.projects.artedesejo.domain.supplier.exceptions;

import com.esperanca.projects.artedesejo.core.exceptions.EntityNotFoundException;

import static java.lang.String.format;

public class SupplierNotFoundException extends EntityNotFoundException
{
  private static final String MESSAGE = "Supplier not found with id: %d";

  public SupplierNotFoundException(Long id)
  {
    super(format(MESSAGE, id));
  }
}
