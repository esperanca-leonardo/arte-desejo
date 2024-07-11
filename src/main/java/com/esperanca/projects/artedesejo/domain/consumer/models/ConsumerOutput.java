package com.esperanca.projects.artedesejo.domain.consumer.models;

import com.esperanca.projects.artedesejo.core.models.BaseModelOutput;
import com.esperanca.projects.artedesejo.domain.address.models.AddressOutput;
import com.esperanca.projects.artedesejo.domain.consumer.enums.Gender;
import com.esperanca.projects.artedesejo.domain.consumer.enums.SexualOrientation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConsumerOutput extends BaseModelOutput
{
  private String name;
  private Gender gender;
  private SexualOrientation sexualOrientation;
  private LocalDate dateOfBirth;
  private String phoneNumber;
  private String email;
  private String cpf;
  private AddressOutput address;
}
