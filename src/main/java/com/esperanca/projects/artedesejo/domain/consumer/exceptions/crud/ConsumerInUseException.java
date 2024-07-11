package com.esperanca.projects.artedesejo.domain.consumer.exceptions.crud;

import com.esperanca.projects.artedesejo.core.exceptions.EntityInUseException;

import static java.lang.String.format;

public class ConsumerInUseException extends EntityInUseException
{
  private static final String MESSAGE = "Consumer in use with id: %d";

  public ConsumerInUseException(Long id)
  {
    super(format(MESSAGE, id));
  }
}
