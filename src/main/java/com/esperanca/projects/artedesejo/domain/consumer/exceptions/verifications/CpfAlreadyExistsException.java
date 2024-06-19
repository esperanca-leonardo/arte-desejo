package com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications;

import static java.lang.String.format;

public class CpfAlreadyExistsException extends RuntimeException
{
  private static final String MESSAGE = "CPF: %s already exists";

  public CpfAlreadyExistsException(String cpf)
  {
    super(format(MESSAGE, cpf));
  }
}
