package com.esperanca.projects.artedesejo.core.error.problemdetail.fielderror;

import lombok.Builder;

@Builder
public record ProblemDetailFieldError(
    String field,
    String message
) { }
