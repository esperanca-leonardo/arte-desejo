package com.esperanca.projects.artedesejo.core.error.problemdetail.helper;

import com.esperanca.projects.artedesejo.core.error.problemdetail.fielderror.ProblemDetailFieldError;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Component
public class ProblemDetailHelperImpl implements ProblemDetailHelper
{
  @Override
  public ProblemDetailFieldError mapToField(FieldError fieldError)
  {
    return ProblemDetailFieldError.builder()
        .field(fieldError.getField())
        .message(fieldError.getDefaultMessage())
        .build();
  }

  @Override
  public List<ProblemDetailFieldError> extractFieldErrors(
      MethodArgumentNotValidException exception)
  {
    return exception.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(this::mapToField)
        .toList();
  }
}
