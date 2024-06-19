package com.esperanca.projects.artedesejo.core.problemdetail.helper;

import com.esperanca.projects.artedesejo.core.problemdetail.fielderror.Field;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Component
public class ProblemDetailHelperImpl implements ProblemDetailHelper
{
  @Override
  public Field mapToField(FieldError fieldError)
  {
    return Field.builder()
        .field(fieldError.getField())
        .message(fieldError.getDefaultMessage())
        .build();
  }

  @Override
  public List<Field> extractFieldErrors(
      MethodArgumentNotValidException exception)
  {
    return exception.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(this::mapToField)
        .toList();
  }
}
