package com.github.translation.translate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class TranslatedMessage {
    private String sourceLanguage;
    private String translatedText;
}
