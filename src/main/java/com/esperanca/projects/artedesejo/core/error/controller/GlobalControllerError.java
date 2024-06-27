package com.esperanca.projects.artedesejo.core.error.controller;

import com.esperanca.projects.artedesejo.core.error.problemdetail.contracts.ProblemDetailBuilder;
import com.esperanca.projects.artedesejo.core.error.controller.enums.GlobalProblemDetailType;
import com.esperanca.projects.artedesejo.core.error.problemdetail.fielderror.ProblemDetailFieldError;
import com.esperanca.projects.artedesejo.core.error.problemdetail.helper.ProblemDetailHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static com.esperanca.projects.artedesejo.core.error.controller.enums.GlobalProblemDetailType.INVALID_DATA;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalControllerError extends ResponseEntityExceptionHandler
{
  private final ProblemDetailBuilder
      <GlobalProblemDetailType> problemDetailBuilder;

  private final ProblemDetailHelper problemDetailHelper;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request)
  {
    final var detail = "One or more fields are invalid. Please fill in " +
        "the correct information and try again.";

    final ProblemDetail problemDetail =
        this.problemDetailBuilder.buildProblemDetail(INVALID_DATA, detail);

    final List<ProblemDetailFieldError> fieldErrors =
        this.problemDetailHelper.extractFieldErrors(ex);

    problemDetail.setProperty("fieldErrors", fieldErrors);

    return super.handleExceptionInternal(ex, problemDetail, headers, status,
        request
    );
  }
}
