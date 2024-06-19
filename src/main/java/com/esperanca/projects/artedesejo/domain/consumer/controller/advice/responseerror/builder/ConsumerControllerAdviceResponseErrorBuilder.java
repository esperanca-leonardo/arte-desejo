package com.esperanca.projects.artedesejo.domain.consumer.controller.advice.responseerror.builder;

import com.esperanca.projects.artedesejo.domain.consumer.controller.advice.responseerror.enums.ConsumerControllerAdviceResponseErrorType;
import org.springframework.http.ProblemDetail;

public interface ConsumerControllerAdviceResponseErrorBuilder
{
  ProblemDetail buildProblemDetail(
      ConsumerControllerAdviceResponseErrorType responseError,
      String detail
  );
}
