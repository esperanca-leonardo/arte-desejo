package com.esperanca.projects.artedesejo.core.controller.error.type;

import org.springframework.http.HttpStatus;

import java.net.URI;

public interface ControllerErrorType
{
  URI getType();
  String getTitle();
  HttpStatus getStatus();
}
