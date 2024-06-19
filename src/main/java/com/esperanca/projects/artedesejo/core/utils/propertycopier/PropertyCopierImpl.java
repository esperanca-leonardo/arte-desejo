package com.esperanca.projects.artedesejo.core.utils.propertycopier;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PropertyCopierImpl implements PropertyCopier
{
  private final ModelMapper modelMapper;

  @Override
  public void copyProperties(Object source, Object target)
  {
    this.modelMapper.map(source, target);
  }
}
