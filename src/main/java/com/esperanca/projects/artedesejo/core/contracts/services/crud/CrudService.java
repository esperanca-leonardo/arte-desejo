package com.esperanca.projects.artedesejo.core.contracts.services.crud;

import com.esperanca.projects.artedesejo.core.models.BaseOutput;

import java.util.List;

public interface CrudService<Output extends BaseOutput, Input, ID>
{
  List<Output> findAll();

  Output findById(ID id);

  Output save(Input input);

  Output updateById(ID id, Input input);

  void deleteById(ID id);
}
