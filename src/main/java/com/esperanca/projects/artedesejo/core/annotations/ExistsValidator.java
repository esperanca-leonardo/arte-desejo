package com.esperanca.projects.artedesejo.core.annotations;

import com.esperanca.projects.artedesejo.core.utils.entityfinder.EntityFinder;
import com.esperanca.projects.artedesejo.core.utils.helpers.pathhelper.PathHelper;
import com.esperanca.projects.artedesejo.core.utils.helpers.stringhelper.StringHelper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;

import static java.lang.Class.forName;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@AllArgsConstructor
public class ExistsValidator implements ConstraintValidator<Exists, Object>
{
  private final PathHelper pathHelper;
  private final StringHelper stringHelper;
  private final EntityFinder entityFinder;

  @Override
  public void initialize(Exists annotation)
  {
    ConstraintValidator.super.initialize(annotation);
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context)
  {
    try
    {
      requireNonNull(value);

      final Class<?> aClass = value.getClass();

      String className = aClass.getSimpleName();
      className = stringHelper.removeSuffix(className, "IdInput");

      final String entityPath = pathHelper.getEntityPath(className);
      final Class<?> entityClass = forName(entityPath);
      final Field id = aClass.getDeclaredField("id");

      id.setAccessible(true);

      final var idValue = (Long) id.get(value);
      final Object entity = entityFinder.find(entityClass, idValue);

      return !isNull(entity);
    }
    catch (Exception exception)
    {
      throw new RuntimeException(exception);
    }
  }
}
