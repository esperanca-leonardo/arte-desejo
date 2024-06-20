package com.esperanca.projects.artedesejo.core.crud;

import java.util.List;

public interface Crud<Output, Input>
{
  List<Output> findAll();

  Output findById(Long id);

  Output save(Input input);

  Output updateById(Long id, Input input);

  void deleteById(Long id);
}
