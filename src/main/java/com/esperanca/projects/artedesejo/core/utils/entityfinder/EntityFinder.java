package com.esperanca.projects.artedesejo.core.utils.entityfinder;

public interface EntityFinder
{
  Object find(Class<?> entityClass, Object id);
}
