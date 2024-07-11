package com.esperanca.projects.artedesejo.domain.consumer.service;

import com.esperanca.projects.artedesejo.domain.consumer.entity.Consumer;
import com.esperanca.projects.artedesejo.domain.consumer.exceptions.crud.ConsumerNotFoundException;

public interface ConsumerService
{
  Consumer findById(Long id) throws ConsumerNotFoundException;
}
