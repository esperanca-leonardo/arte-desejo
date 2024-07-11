package com.esperanca.projects.artedesejo.core.utils.helpers.stringhelper;

import com.esperanca.projects.artedesejo.core.contracts.helpers.stringhelper.StringHelper;
import org.springframework.stereotype.Component;

@Component
public class StringHelperImpl implements StringHelper
{
  @Override
  public String removeSuffix(String className, String suffix)
  {
    if (!className.endsWith(suffix))
    {
      throw new IllegalArgumentException("The suffix is not present");
    }

    final int lengthWithoutSuffix = className.length() - suffix.length();
    return className.substring(0, lengthWithoutSuffix);
  }
}
