package com.esperanca.projects.artedesejo.core.error.problemdetail.helper;

import com.esperanca.projects.artedesejo.core.error.problemdetail.fielderror.ProblemDetailFieldError;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public interface ProblemDetailHelper
{
  ProblemDetailFieldError mapToField(FieldError fieldError);

  List<ProblemDetailFieldError> extractFieldErrors(
      MethodArgumentNotValidException exception);
}
