package com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications;

import static java.lang.String.format;

public class PhoneNumberAlreadyExistsException extends RuntimeException
{
  private static final String MESSAGE = "Phone number: %s already exists";

  public PhoneNumberAlreadyExistsException(String cellPhoneNumber)
  {
    super(format(MESSAGE, cellPhoneNumber));
  }
}
