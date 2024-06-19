package com.esperanca.projects.artedesejo.domain.consumer.checker.existence.phonenumber;

import com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications.PhoneNumberAlreadyExistsException;
import com.esperanca.projects.artedesejo.domain.consumer.repository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsumerPhoneNumberExistenceCheckerImpl
    implements ConsumerPhoneNumberExistenceChecker
{
  private final ConsumerRepository repository;

  @Override
  public void checkPhoneNumberExists(String phoneNumber)
      throws PhoneNumberAlreadyExistsException
  {
    final boolean phoneNumberExists = this.repository.existsByPhoneNumber(
        phoneNumber
    );

    if (phoneNumberExists)
    {
      throw new PhoneNumberAlreadyExistsException(phoneNumber);
    }
  }

  @Override
  public void checkPhoneNumberExists(String phoneNumber,
      Long excludeConsumerId) throws PhoneNumberAlreadyExistsException
  {
    final boolean phoneNumberExists = this.repository
        .existsByPhoneNumberAndIdNot(phoneNumber, excludeConsumerId);

    if (phoneNumberExists)
    {
      throw new PhoneNumberAlreadyExistsException(phoneNumber);
    }
  }
}
