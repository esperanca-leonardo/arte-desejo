package com.esperanca.projects.artedesejo.domain.consumer.entity;

import com.esperanca.projects.artedesejo.domain.address.embeddable.Address;
import com.esperanca.projects.artedesejo.domain.consumer.enums.Gender;
import com.esperanca.projects.artedesejo.domain.consumer.enums.SexualOrientation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consumer
{
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
