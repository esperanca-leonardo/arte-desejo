package com.esperanca.projects.artedesejo.domain.consumer.checker.existence.email;

import com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications.EmailAlreadyExistsException;
import com.esperanca.projects.artedesejo.domain.consumer.repository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsumerEmailExistenceCheckerImpl
    implements ConsumerEmailExistenceChecker
{
  private final ConsumerRepository repository;

  @Override
  public void checkEmailExists(String email) throws EmailAlreadyExistsException
  {
    final boolean emailExists = this.repository.existsByEmail(email);

    if (emailExists)
    {
      throw new EmailAlreadyExistsException(email);
    }
  }

  @Override
  public void checkEmailExists(String email, Long excludeConsumerId)
      throws EmailAlreadyExistsException
  {
    final boolean emailExists = this.repository.existsByEmailAndIdNot(email,
        excludeConsumerId
    );

    if (emailExists)
    {
      throw new EmailAlreadyExistsException(email);
    }
  }
}
