package com.esperanca.projects.artedesejo.domain.consumer.service.crud;

import com.esperanca.projects.artedesejo.core.contracts.crud.CrudService;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerInput;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerOutput;
import com.esperanca.projects.artedesejo.domain.consumer.exceptions.crud.ConsumerNotFoundException;

import java.util.List;

public interface ConsumerCrudService extends CrudService<ConsumerOutput, ConsumerInput, Long>
{
  @Override
  List<ConsumerOutput> findAll();

  @Override
  ConsumerOutput findById(Long id) throws ConsumerNotFoundException;

  @Override
  ConsumerOutput save(ConsumerInput consumerInput);

  @Override
  ConsumerOutput updateById(Long id, ConsumerInput consumerInput)
      throws ConsumerNotFoundException;

  @Override
  void deleteById(Long id);
}
