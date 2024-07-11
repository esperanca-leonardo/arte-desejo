package com.esperanca.projects.artedesejo.domain.consumer.exceptions.crud;

import com.esperanca.projects.artedesejo.core.exceptions.EntityNotFoundException;

import static java.lang.String.format;

public class ConsumerNotFoundException extends EntityNotFoundException
{
  private static final String MESSAGE = "Consumer not found with id: %d";

  public ConsumerNotFoundException(Long id)
  {
    super(format(MESSAGE, id));
  }
}
