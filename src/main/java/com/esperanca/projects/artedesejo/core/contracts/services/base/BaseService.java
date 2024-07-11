package com.esperanca.projects.artedesejo.core.contracts.services.base;

public interface BaseService<Entity>
{
  Entity findById(Long id) throws RuntimeException;
}
