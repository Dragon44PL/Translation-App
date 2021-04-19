package com.github.translation.translate;

import java.util.Optional;

public interface Translate {
    Optional<TranslatedMessage> translateMessage(Message message);
}
