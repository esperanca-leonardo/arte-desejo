package com.esperanca.projects.artedesejo.domain.consumer.checker;

import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerInput;
import com.esperanca.projects.artedesejo.domain.consumer.checker.existence.cpf.ConsumerCpfExistenceChecker;
import com.esperanca.projects.artedesejo.domain.consumer.checker.existence.email.ConsumerEmailExistenceChecker;
import com.esperanca.projects.artedesejo.domain.consumer.checker.existence.phonenumber.ConsumerPhoneNumberExistenceChecker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsumerCheckerImpl implements ConsumerChecker
{
  private final ConsumerCpfExistenceChecker cpfExistenceChecker;
  private final ConsumerEmailExistenceChecker emailExistenceChecker;
  private final ConsumerPhoneNumberExistenceChecker phoneNumberExistenceChecker;

  @Override
  public void validateNewConsumerData(ConsumerInput consumerInput)
  {
    final String cpf = consumerInput.getCpf();
    final String email = consumerInput.getEmail();
    final String phoneNumber = consumerInput.getPhoneNumber();

    this.cpfExistenceChecker.checkCpfExists(cpf);
    this.emailExistenceChecker.checkEmailExists(email);
    this.phoneNumberExistenceChecker.checkPhoneNumberExists(phoneNumber);
  }


  @Override
  public void validateExistingConsumerData(ConsumerInput consumerInput,
                                           Long excludeConsumerId)
  {
    final String cpf = consumerInput.getCpf();
    final String email = consumerInput.getEmail();
    final String phoneNumber = consumerInput.getPhoneNumber();

    this.cpfExistenceChecker.checkCpfExists(cpf, excludeConsumerId);
    this.emailExistenceChecker.checkEmailExists(email, excludeConsumerId);
    this.phoneNumberExistenceChecker.checkPhoneNumberExists(phoneNumber,
        excludeConsumerId
    );
  }
}
