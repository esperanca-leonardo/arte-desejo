package com.esperanca.projects.artedesejo.core.utils.copier;

import com.esperanca.projects.artedesejo.core.contracts.helpers.copier.CopierHelper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CopierHelperImpl implements CopierHelper
{
  private final ModelMapper modelMapper;

  @Override
  public void copyProperties(Object source, Object target)
  {
    this.modelMapper.map(source, target);
  }
}
