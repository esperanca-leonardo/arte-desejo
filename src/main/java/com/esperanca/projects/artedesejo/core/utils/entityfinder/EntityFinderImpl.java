package com.esperanca.projects.artedesejo.core.utils.entityfinder;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EntityFinderImpl implements EntityFinder
{
  private final EntityManager entityManager;

  @Override
  public Object find(Class<?> entityClass, Object id)
  {
    return this.entityManager.find(entityClass, id);
  }
}
