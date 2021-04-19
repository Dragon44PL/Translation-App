package com.github.translation.translate;

import java.util.Optional;

public interface Translate {
    Optional<Message> translateMessage(Message message);
}
