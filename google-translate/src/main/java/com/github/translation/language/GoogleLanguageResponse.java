package com.github.translation.language;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class GoogleLanguageResponse {
    private GoogleLanguages data;
}

