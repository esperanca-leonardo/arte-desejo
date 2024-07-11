package com.esperanca.projects.artedesejo.core.utils.helpers.pathhelper;

import com.esperanca.projects.artedesejo.core.contracts.helpers.path.PathHelper;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class PathHelperImpl implements PathHelper
{
  public static final String DOMAIN_PACKAGE = "com.esperanca.projects" +
      ".artedesejo.domain";

  @Override
  public String getEntityPath(String className)
  {
    return format("%s.%s.entity.%s",
        DOMAIN_PACKAGE, className.toLowerCase(), className
    );
  }
}
