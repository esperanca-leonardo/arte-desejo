package com.esperanca.projects.artedesejo.domain.consumer.checker.existence.cpf;

import com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications.CpfAlreadyExistsException;

public interface ConsumerCpfExistenceChecker
{
  void checkCpfExists(String cpf) throws CpfAlreadyExistsException;

  void checkCpfExists(String cpf, Long excludeConsumerId)
      throws CpfAlreadyExistsException;
}
