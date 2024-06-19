package com.esperanca.projects.artedesejo.domain.consumer.exceptions.crud;

import static java.lang.String.format;

public class ConsumerNotFoundException extends RuntimeException
{
  private static final String MESSAGE = "Consumer not found with id: %d";

  public ConsumerNotFoundException(Long id)
  {
    super(format(MESSAGE, id));
  }
}
