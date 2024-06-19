package com.esperanca.projects.artedesejo.domain.consumer.checker.existence.cpf;

import com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications.CpfAlreadyExistsException;
import com.esperanca.projects.artedesejo.domain.consumer.repository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsumerCpfExistenceCheckerImpl
    implements ConsumerCpfExistenceChecker
{
  private final ConsumerRepository repository;

  @Override
  public void checkCpfExists(String cpf) throws CpfAlreadyExistsException
  {
    final boolean cpfExists = this.repository.existsByCpf(cpf);

    if (cpfExists)
    {
      throw new CpfAlreadyExistsException(cpf);
    }
  }

  @Override
  public void checkCpfExists(String cpf, Long excludeConsumerId)
      throws CpfAlreadyExistsException
  {
    final boolean cpfExists = this.repository.existsByCpfAndIdNot(cpf,
        excludeConsumerId
    );

    if (cpfExists)
    {
      throw new CpfAlreadyExistsException(cpf);
    }
  }
}
