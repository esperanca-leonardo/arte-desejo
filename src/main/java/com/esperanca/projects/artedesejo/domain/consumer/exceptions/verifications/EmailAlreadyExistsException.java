package com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications;

import static java.lang.String.format;

public class EmailAlreadyExistsException extends RuntimeException
{
  private static final String MESSAGE = "Email: %s already exists";

  public EmailAlreadyExistsException(String email)
  {
    super(format(MESSAGE, email));
  }
}
