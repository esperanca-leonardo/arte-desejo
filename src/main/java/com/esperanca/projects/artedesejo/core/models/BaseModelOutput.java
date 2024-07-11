package com.esperanca.projects.artedesejo.core.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseModelOutput
{
  private Long id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
