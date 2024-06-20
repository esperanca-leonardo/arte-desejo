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
  @Column(nullable = false)
  private String name;

  private Gender gender;

  private SexualOrientation sexualOrientation;

  private LocalDate dateOfBirth;

  @Column(unique = true, nullable = false)
  private String phoneNumber;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(unique = true, nullable = false)
  private String cpf;

  @Embedded
  private Address address;
}
