package com.esperanca.projects.artedesejo.domain.product.controller.error;

import com.esperanca.projects.artedesejo.core.error.problemdetail.contracts.ProblemDetailBuilder;
import com.esperanca.projects.artedesejo.domain.product.controller.ProductController;
import com.esperanca.projects.artedesejo.domain.product.controller.error.enums.ProductProblemDetailType;
import com.esperanca.projects.artedesejo.domain.product.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.esperanca.projects.artedesejo.domain.product.controller.error.enums.ProductProblemDetailType.NOT_FOUND;

@AllArgsConstructor
@RestControllerAdvice(assignableTypes = ProductController.class)
public class ProductControllerError
{
  private final ProblemDetailBuilder
      <ProductProblemDetailType> problemDetailBuilder;

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleProductNotFoundException(
      ProductNotFoundException exception)
  {
    final ProblemDetail problemDetail =
        problemDetailBuilder.buildProblemDetail(NOT_FOUND,
            exception.getMessage()
        );

    return new ResponseEntity<>(problemDetail, NOT_FOUND.getStatus());
  }
}
