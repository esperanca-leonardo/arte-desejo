package com.esperanca.projects.artedesejo.core.contracts.crud;

import java.util.List;

public interface ServiceCrud<Output, Input, ID>
{
  List<Output> findAll();

  Output findById(ID id);

  Output save(Input input);

  Output updateById(ID id, Input input);

  void deleteById(ID id);
}
