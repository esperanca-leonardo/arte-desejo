package com.esperanca.projects.artedesejo.domain.consumer.controller.error;

import com.esperanca.projects.artedesejo.core.controller.error.builder.ControllerErrorBuilder;
import com.esperanca.projects.artedesejo.domain.consumer.controller.ConsumerController;
import com.esperanca.projects.artedesejo.domain.consumer.controller.error.enums.ConsumerControllerErrorType;
import com.esperanca.projects.artedesejo.domain.consumer.exceptions.crud.ConsumerNotFoundException;
import com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications.CpfAlreadyExistsException;
import com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications.EmailAlreadyExistsException;
import com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications.PhoneNumberAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.esperanca.projects.artedesejo.domain.consumer.controller.error.enums.ConsumerControllerErrorType.*;

@AllArgsConstructor
@RestControllerAdvice(assignableTypes = ConsumerController.class)
public class ConsumerControllerError
{
  private final ControllerErrorBuilder
      <ConsumerControllerErrorType> responseErrorBuilder;

  @ExceptionHandler(ConsumerNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleConsumerNotFoundException(
      ConsumerNotFoundException exception)
  {
    final ProblemDetail problemDetail =
        responseErrorBuilder.buildProblemDetail(NOT_FOUND,
        exception.getMessage()
    );

    return new ResponseEntity<>(problemDetail, NOT_FOUND.getStatus());
  }

  @ExceptionHandler(CpfAlreadyExistsException.class)
  public ResponseEntity<ProblemDetail> handleCpfAlreadyExistsException(
      CpfAlreadyExistsException exception)
  {
    final ProblemDetail problemDetail =
        responseErrorBuilder.buildProblemDetail(CPF_ALREADY_EXISTS,
            exception.getMessage()
        );

    return new ResponseEntity<>(problemDetail, CPF_ALREADY_EXISTS.getStatus());
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<ProblemDetail> handleEmailAlreadyExistsException(
      EmailAlreadyExistsException exception)
  {
    final ProblemDetail problemDetail =
        responseErrorBuilder.buildProblemDetail(EMAIL_ALREADY_EXISTS,
            exception.getMessage()
        );

    return new ResponseEntity<>(problemDetail,
        EMAIL_ALREADY_EXISTS.getStatus()
    );
  }

  @ExceptionHandler(PhoneNumberAlreadyExistsException.class)
  public ResponseEntity<ProblemDetail> handlePhoneNumberAlreadyExistsException(
      PhoneNumberAlreadyExistsException exception)
  {
    final ProblemDetail problemDetail =
        responseErrorBuilder.buildProblemDetail(PHONE_NUMBER_ALREADY_EXISTS,
            exception.getMessage()
        );

    return new ResponseEntity<>(problemDetail,
        PHONE_NUMBER_ALREADY_EXISTS.getStatus()
    );
  }
}
