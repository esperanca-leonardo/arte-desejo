package com.esperanca.projects.artedesejo.domain.consumer.controller.error.enums;

import com.esperanca.projects.artedesejo.core.controller.error.type.ControllerErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URI;

import static java.net.URI.create;
import static org.springframework.http.HttpStatus.CONFLICT;

@Getter
public enum ConsumerControllerErrorType implements ControllerErrorType
{
  NOT_FOUND("/not-found", "Consumer not found",
      HttpStatus.NOT_FOUND
  ),
  CPF_ALREADY_EXISTS("/cpf-already-exists", "CPF already exists",
      CONFLICT
  ),
  EMAIL_ALREADY_EXISTS("/email-already-exists",
      "Email already exists", CONFLICT
  ),
  PHONE_NUMBER_ALREADY_EXISTS("/phone-number-already-exists",
      "Phone number already exists", CONFLICT
  );

  private final URI type;
  private final String title;
  private final HttpStatus status;

  ConsumerControllerErrorType(String type, String title, HttpStatus status)
  {
    this.type = create("https://artedesejo.com.br/problems/consumer" + type);
    this.title = title;
    this.status = status;
  }
}
