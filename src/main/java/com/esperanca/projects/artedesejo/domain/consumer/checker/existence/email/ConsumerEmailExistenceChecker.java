package com.esperanca.projects.artedesejo.domain.consumer.checker.existence.email;

import com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications.EmailAlreadyExistsException;

public interface ConsumerEmailExistenceChecker
{
  void checkEmailExists(String email) throws EmailAlreadyExistsException;

  void checkEmailExists(String email, Long excludeConsumerId)
      throws EmailAlreadyExistsException;
}
