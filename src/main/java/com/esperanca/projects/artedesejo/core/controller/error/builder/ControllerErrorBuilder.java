package com.esperanca.projects.artedesejo.core.controller.error.builder;

import com.esperanca.projects.artedesejo.core.controller.error.type.ControllerErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

public interface ControllerErrorBuilder<Type extends ControllerErrorType>
{
  default ProblemDetail buildProblemDetail(Type responseError, String detail)
  {
    final URI type = responseError.getType();
    final String title = responseError.getTitle();
    final HttpStatus status = responseError.getStatus();
    final LocalDateTime timestamp = now().withNano(0);

    final ProblemDetail problemDetail = forStatusAndDetail(status, detail);

    problemDetail.setType(type);
    problemDetail.setTitle(title);
    problemDetail.setProperty("timestamp", timestamp);

    return problemDetail;
  }
}
