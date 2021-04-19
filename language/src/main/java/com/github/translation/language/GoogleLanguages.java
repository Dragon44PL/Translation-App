package com.github.translation.language;

import lombok.*;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class GoogleLanguages {
    private List<GoogleLanguage> languages;
}
