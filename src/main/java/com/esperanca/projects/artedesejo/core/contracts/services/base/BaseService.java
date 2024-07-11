package com.esperanca.projects.artedesejo.core.contracts.services.base;

import com.esperanca.projects.artedesejo.core.entity.BaseEntity;
import com.esperanca.projects.artedesejo.core.exceptions.EntityNotFoundException;

public interface BaseService<Entity extends BaseEntity>
{
  Entity findById(Long id) throws EntityNotFoundException;
}
