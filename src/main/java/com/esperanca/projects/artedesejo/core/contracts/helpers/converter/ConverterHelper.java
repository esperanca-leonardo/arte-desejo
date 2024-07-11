package com.esperanca.projects.artedesejo.core.contracts.helpers.converter;

import com.esperanca.projects.artedesejo.core.entity.BaseEntity;
import com.esperanca.projects.artedesejo.core.models.BaseOutput;

import java.util.List;

public interface ConverterHelper<
    Entity extends BaseEntity,
    Input,
    Output extends BaseOutput>
{
  Entity toEntity(Input input);

  Output toOutput(Entity entity);

  List<Output> toCollectionOutput(List<Entity> entities);
}
