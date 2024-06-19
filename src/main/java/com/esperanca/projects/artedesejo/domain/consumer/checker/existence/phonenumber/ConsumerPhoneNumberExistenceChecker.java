package com.esperanca.projects.artedesejo.domain.consumer.checker.existence.phonenumber;

import com.esperanca.projects.artedesejo.domain.consumer.exceptions.verifications.PhoneNumberAlreadyExistsException;

public interface ConsumerPhoneNumberExistenceChecker
{
  void checkPhoneNumberExists(String phoneNumber)
      throws PhoneNumberAlreadyExistsException;

  void checkPhoneNumberExists(String phoneNumber, Long excludeConsumerId)
      throws PhoneNumberAlreadyExistsException;
}
