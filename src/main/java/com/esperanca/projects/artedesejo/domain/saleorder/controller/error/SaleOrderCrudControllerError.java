package com.esperanca.projects.artedesejo.domain.saleorder.controller.error;

import com.esperanca.projects.artedesejo.core.error.problemdetail.contracts.ProblemDetailBuilder;
import com.esperanca.projects.artedesejo.domain.saleorder.controller.SaleOrderCrudController;
import com.esperanca.projects.artedesejo.domain.saleorder.controller.error.enums.SaleOrderProblemDetailType;
import com.esperanca.projects.artedesejo.domain.saleorder.exceptions.SaleOrderNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.esperanca.projects.artedesejo.domain.saleorder.controller.error.enums.SaleOrderProblemDetailType.NOT_FOUND;

@AllArgsConstructor
@RestControllerAdvice(assignableTypes = SaleOrderCrudController.class)
public class SaleOrderCrudControllerError
{
  private final ProblemDetailBuilder
      <SaleOrderProblemDetailType> problemDetailBuilder;

  @ExceptionHandler(SaleOrderNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleSaleOrderNotFoundException(
      SaleOrderNotFoundException exception)
  {
    final ProblemDetail problemDetail =
        problemDetailBuilder.buildProblemDetail(NOT_FOUND,
            exception.getMessage()
        );

    return new ResponseEntity<>(problemDetail, NOT_FOUND.getStatus());
  }

}
