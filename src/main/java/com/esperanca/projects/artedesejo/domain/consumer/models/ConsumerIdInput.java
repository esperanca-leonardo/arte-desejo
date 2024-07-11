package com.esperanca.projects.artedesejo.domain.consumer.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ConsumerIdInput
{
  @NotNull
  @Positive
  private Long id;
}
