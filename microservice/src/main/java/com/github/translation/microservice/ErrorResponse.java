package com.github.translation.microservice;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class ErrorResponse {
    private final String error;
}
