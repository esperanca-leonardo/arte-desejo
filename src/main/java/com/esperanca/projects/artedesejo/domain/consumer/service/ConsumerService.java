package com.esperanca.projects.artedesejo.domain.consumer.service;

import com.esperanca.projects.artedesejo.core.contracts.services.base.BaseService;
import com.esperanca.projects.artedesejo.domain.consumer.entity.Consumer;
import com.esperanca.projects.artedesejo.domain.consumer.exceptions.crud.ConsumerNotFoundException;

public interface ConsumerService extends BaseService<Consumer>
{
  @Override
  Consumer findById(Long id) throws ConsumerNotFoundException;
}
