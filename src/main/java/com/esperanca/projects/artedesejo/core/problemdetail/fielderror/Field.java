package com.esperanca.projects.artedesejo.core.problemdetail.fielderror;

import lombok.Builder;

@Builder
public record Field(
    String field,
    String message
) { }
