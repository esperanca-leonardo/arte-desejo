package com.esperanca.projects.artedesejo.domain.consumer.models;

import com.esperanca.projects.artedesejo.domain.address.models.AddressInput;
import com.esperanca.projects.artedesejo.domain.consumer.enums.Gender;
import com.esperanca.projects.artedesejo.domain.consumer.enums.SexualOrientation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
public class ConsumerInput
{
  @NotBlank
  private String name;
  private Gender gender;
  private SexualOrientation sexualOrientation;
  private LocalDate dateOfBirth;
  private String phoneNumber;

  @Email
  private String email;

  @CPF
  private String cpf;
  private AddressInput address;
}
