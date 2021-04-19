package com.github.translation.translate;

import lombok.*;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Translations {
    private List<TranslatedText> translations;
}
