package com.esperanca.projects.artedesejo.core.error.controller.enums;

import com.esperanca.projects.artedesejo.core.error.problemdetail.contracts.ProblemDetailType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URI;

import static java.net.URI.create;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public enum GlobalProblemDetailType implements ProblemDetailType
{
  INVALID_DATA("/invalid-data", "Invalid data", BAD_REQUEST);

  private final URI type;
  private final String title;
  private final HttpStatus status;

  GlobalProblemDetailType(String type, String title, HttpStatus status)
  {
    this.type = create("https://artedesejo.com.br/problems" + type);
    this.title = title;
    this.status = status;
  }
}
