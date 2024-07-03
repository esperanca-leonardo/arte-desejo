package com.esperanca.projects.artedesejo.domain.supplier.exceptions;

import static java.lang.String.format;

public class SupplierInUseException extends RuntimeException
{
  private static final String MESSAGE = "Supplier in use with id: %d";

  public SupplierInUseException(Long id)
  {
    super(format(MESSAGE, id));
  }
}
