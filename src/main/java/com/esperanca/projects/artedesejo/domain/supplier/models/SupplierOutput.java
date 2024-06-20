package com.esperanca.projects.artedesejo.domain.supplier.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SupplierOutput
{
  private Long id;
  private String name;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
