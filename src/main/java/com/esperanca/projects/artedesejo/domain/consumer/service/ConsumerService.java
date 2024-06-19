package com.esperanca.projects.artedesejo.domain.consumer.service;

import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerInput;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerOutput;
import com.esperanca.projects.artedesejo.domain.consumer.exceptions.crud.ConsumerNotFoundException;

import java.util.List;

public interface ConsumerService
{
  List<ConsumerOutput> findAll();

  ConsumerOutput findById(Long id) throws ConsumerNotFoundException;

  ConsumerOutput save(ConsumerInput consumerInput);

  ConsumerOutput updateById(ConsumerInput consumerInput, Long id)
      throws ConsumerNotFoundException;

  void deleteById(Long id);
}
