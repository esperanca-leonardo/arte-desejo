package com.esperanca.projects.artedesejo.core.problemdetail.helper;

import com.esperanca.projects.artedesejo.core.problemdetail.fielderror.Field;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public interface ProblemDetailHelper
{
  Field mapToField(FieldError fieldError);

  List<Field> extractFieldErrors(
      MethodArgumentNotValidException exception);
}
