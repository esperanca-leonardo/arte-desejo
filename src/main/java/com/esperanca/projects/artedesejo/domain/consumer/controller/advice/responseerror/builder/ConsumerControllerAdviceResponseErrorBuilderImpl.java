package com.esperanca.projects.artedesejo.domain.consumer.controller.advice.responseerror.builder;

import com.esperanca.projects.artedesejo.core.problemdetail.helper.ProblemDetailHelper;
import com.esperanca.projects.artedesejo.domain.consumer.controller.advice.responseerror.enums.ConsumerControllerAdviceResponseErrorType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@Component
@AllArgsConstructor
public class ConsumerControllerAdviceResponseErrorBuilderImpl
    implements ConsumerControllerAdviceResponseErrorBuilder
{

  private final ProblemDetailHelper problemDetailHelper;

  @Override
  public ProblemDetail buildProblemDetail(
      ConsumerControllerAdviceResponseErrorType responseError, String detail)
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
