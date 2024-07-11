package com.esperanca.projects.artedesejo.domain.saleorder.controller.error.enums;

import com.esperanca.projects.artedesejo.core.error.problemdetail.contracts.ProblemDetailType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URI;

import static java.net.URI.create;

@Getter
public enum SaleOrderProblemDetailType implements ProblemDetailType
{
  NOT_FOUND("/not-found", "SaleOrder not found", HttpStatus.NOT_FOUND);

  private final URI type;
  private final String title;
  private final HttpStatus status;

  SaleOrderProblemDetailType(String type, String title, HttpStatus status)
  {
    this.type = create("https://artedesejo.com.br/problems/sale-orders" + type);
    this.title = title;
    this.status = status;
  }
}
