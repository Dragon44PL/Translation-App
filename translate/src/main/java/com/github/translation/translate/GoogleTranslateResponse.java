package com.github.translation.translate;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class GoogleTranslateResponse {
    private Translations data;
}

