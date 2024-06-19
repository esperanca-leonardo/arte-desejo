package com.esperanca.projects.artedesejo.domain.address.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressOutput
{
  private String cep;
  private String publicPlace;
  private String number;
  private String complement;
  private String district;
  private String city;
  private String state;
}
