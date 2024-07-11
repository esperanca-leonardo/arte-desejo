package com.esperanca.projects.artedesejo.domain.consumer.service;

import com.esperanca.projects.artedesejo.domain.consumer.entity.Consumer;
import com.esperanca.projects.artedesejo.domain.consumer.exceptions.crud.ConsumerNotFoundException;
import com.esperanca.projects.artedesejo.domain.consumer.repository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsumerServiceImpl implements ConsumerService
{
  private final ConsumerRepository repository;

  @Override
  public Consumer findById(Long id) throws ConsumerNotFoundException
  {
    return this.repository.findById(id)
        .orElseThrow(() -> new ConsumerNotFoundException(id));
  }
}
