package com.esperanca.projects.artedesejo.domain.address.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address
{
  @Column(name = "address_cep")
  private String cep;

  @Column(name = "address_public_place")
  private String publicPlace;

  @Column(name = "address_number")
  private String number;

  @Column(name = "address_complement")
  private String complement;

  @Column(name = "address_district")
  private String district;

  @Column(name = "address_city")
  private String city;

  @Column(name = "address_state")
  private String state;
}
