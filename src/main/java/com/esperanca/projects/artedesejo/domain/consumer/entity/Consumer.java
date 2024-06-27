package com.esperanca.projects.artedesejo.domain.consumer.entity;

import com.esperanca.projects.artedesejo.core.entity.BaseEntity;
import com.esperanca.projects.artedesejo.domain.address.embeddable.Address;
import com.esperanca.projects.artedesejo.domain.consumer.enums.Gender;
import com.esperanca.projects.artedesejo.domain.consumer.enums.SexualOrientation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Consumer extends BaseEntity
{
  private String name;
  private Gender gender;
  private SexualOrientation sexualOrientation;
  private LocalDate dateOfBirth;
  private String phoneNumber;
  private String email;
  private String cpf;

  @Embedded
  private Address address;
}
