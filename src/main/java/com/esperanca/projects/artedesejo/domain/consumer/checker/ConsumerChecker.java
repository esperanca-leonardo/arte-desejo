package com.esperanca.projects.artedesejo.domain.consumer.checker;

import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerInput;

public interface ConsumerChecker
{
  void validateNewConsumerData(ConsumerInput consumerInput);
  void validateExistingConsumerData(ConsumerInput consumerInput, Long id);
}
