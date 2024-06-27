package com.esperanca.projects.artedesejo.domain.supplier.entity;

import com.esperanca.projects.artedesejo.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Supplier extends BaseEntity
{
  private String name;
}
