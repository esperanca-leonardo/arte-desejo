package com.esperanca.projects.artedesejo.domain.supplier.controller.error;

import com.esperanca.projects.artedesejo.core.error.problemdetail.contracts.ProblemDetailBuilder;
import com.esperanca.projects.artedesejo.domain.supplier.controller.SupplierController;
import com.esperanca.projects.artedesejo.domain.supplier.controller.error.enums.SupplierProblemDetailType;
import com.esperanca.projects.artedesejo.domain.supplier.exceptions.SupplierNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.esperanca.projects.artedesejo.domain.supplier.controller.error.enums.SupplierProblemDetailType.NOT_FOUND;

@AllArgsConstructor
@RestControllerAdvice(assignableTypes = SupplierController.class)
public class SupplierControllerError
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
}
