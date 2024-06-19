package com.esperanca.projects.artedesejo.core.utils.converter;

import java.util.List;

public interface Converter<Entity, Input, Output>
{
  Entity toEntity(Input input);

  Output toOutput(Entity entity);

  List<Output> toCollectionOutput(List<Entity> entities);
}
