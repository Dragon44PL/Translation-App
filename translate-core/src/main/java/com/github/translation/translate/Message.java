package com.github.translation.translate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Message {

    private final String source;
    private final String target;
    private final String text;
}
