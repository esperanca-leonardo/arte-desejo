package com.esperanca.projects.artedesejo.core.exceptions;

public class EntityInUseException extends RuntimeException
{
  public EntityInUseException(String message)
  {
    super(message);
  }
}
