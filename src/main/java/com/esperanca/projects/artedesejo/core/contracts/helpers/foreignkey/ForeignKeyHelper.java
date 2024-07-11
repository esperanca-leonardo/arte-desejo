package com.esperanca.projects.artedesejo.core.contracts.helpers.foreignkey;

import com.esperanca.projects.artedesejo.core.entity.BaseEntity;

public interface ForeignKeyHelper<Entity extends BaseEntity, Input>
{
  Entity getEntity(Input input);
}
