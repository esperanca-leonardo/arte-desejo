package com.esperanca.projects.artedesejo.domain.supplier.controller.error;

import com.esperanca.projects.artedesejo.core.error.problemdetail.contracts.ProblemDetailBuilder;
import com.esperanca.projects.artedesejo.domain.supplier.controller.SupplierCrudController;
import com.esperanca.projects.artedesejo.domain.supplier.controller.error.enums.SupplierProblemDetailType;
import com.esperanca.projects.artedesejo.domain.supplier.exceptions.SupplierInUseException;
import com.esperanca.projects.artedesejo.domain.supplier.exceptions.SupplierNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.esperanca.projects.artedesejo.domain.supplier.controller.error.enums.SupplierProblemDetailType.IN_USE;
import static com.esperanca.projects.artedesejo.domain.supplier.controller.error.enums.SupplierProblemDetailType.NOT_FOUND;

@AllArgsConstructor
@RestControllerAdvice(assignableTypes = SupplierCrudController.class)
public class SupplierCrudControllerError
{
  private final ProblemDetailBuilder
      <SupplierProblemDetailType> responseErrorBuilder;

  @ExceptionHandler(SupplierNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleSupplierNotFoundException(
      SupplierNotFoundException exception)
  {
    final ProblemDetail problemDetail =
        responseErrorBuilder.buildProblemDetail(NOT_FOUND,
            exception.getMessage()
        );

    return new ResponseEntity<>(problemDetail, NOT_FOUND.getStatus());
  }

  @ExceptionHandler(SupplierInUseException.class)
  public ResponseEntity<ProblemDetail> handleSupplierInUseException(
      SupplierInUseException exception)
  {
    final ProblemDetail problemDetail =
        responseErrorBuilder.buildProblemDetail(IN_USE,
            exception.getMessage()
        );

    return new ResponseEntity<>(problemDetail, IN_USE.getStatus());
  }
}
