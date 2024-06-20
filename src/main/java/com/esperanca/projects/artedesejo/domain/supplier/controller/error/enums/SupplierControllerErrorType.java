package com.esperanca.projects.artedesejo.domain.supplier.controller.error.enums;

import com.esperanca.projects.artedesejo.core.controller.error.type.ControllerErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URI;

import static java.net.URI.create;

@Getter
public enum SupplierControllerErrorType implements ControllerErrorType
{
  NOT_FOUND("/not-found", "Supplier not found",
      HttpStatus.NOT_FOUND
  );

  private final URI type;
  private final String title;
  private final HttpStatus status;

  SupplierControllerErrorType(String type, String title,
                              HttpStatus status)
  {
    this.type = create("https://artedesejo.com.br/problems/supplier" + type);
    this.title = title;
    this.status = status;
  }
}
