package com.esperanca.projects.artedesejo.domain.supplier.exceptions;

import static java.lang.String.format;

public class SupplierNotFoundException extends RuntimeException
{
  private static final String MESSAGE = "Supplier not found with id: %d";

  public SupplierNotFoundException(Long id)
  {
    super(format(MESSAGE, id));
  }
}
