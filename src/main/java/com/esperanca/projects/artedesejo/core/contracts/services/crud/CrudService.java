package com.esperanca.projects.artedesejo.core.contracts.services.crud;

import com.esperanca.projects.artedesejo.core.exceptions.EntityNotFoundException;
import com.esperanca.projects.artedesejo.core.models.BaseOutput;

import java.util.List;

public interface CrudService<Output extends BaseOutput, Input, ID>
{
  List<Output> findAll();

  Output findById(ID id) throws EntityNotFoundException;

  Output save(Input input);

  Output updateById(ID id, Input input) throws EntityNotFoundException;

  void deleteById(ID id);
}
