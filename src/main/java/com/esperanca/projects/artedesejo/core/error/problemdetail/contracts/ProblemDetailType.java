package com.esperanca.projects.artedesejo.core.error.problemdetail.contracts;

import org.springframework.http.HttpStatus;

import java.net.URI;

public interface ProblemDetailType
{
  URI getType();
  String getTitle();
  HttpStatus getStatus();
}
