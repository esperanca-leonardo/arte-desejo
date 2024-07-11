package com.esperanca.projects.artedesejo.core.utils.converter;

import com.esperanca.projects.artedesejo.core.entity.BaseEntity;
import com.esperanca.projects.artedesejo.core.models.BaseOutput;

import java.util.List;

public interface Converter<
    Entity extends BaseEntity,
    Input,
    Output extends BaseOutput>
{
  Entity toEntity(Input input);

  Output toOutput(Entity entity);

  List<Output> toCollectionOutput(List<Entity> entities);
}
