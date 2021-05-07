package com.github.translation.microservice;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class TranslationResponse {
    private final String source;
    private final String text;
}
