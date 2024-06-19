package com.esperanca.projects.artedesejo.domain.address.models;

import lombok.Getter;

@Getter
public class AddressInput
{
  private String cep;
  private String publicPlace;
  private String number;
  private String complement;
  private String district;
  private String city;
  private String state;
}
