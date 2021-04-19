package com.github.translation.translate;

import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class Message {

    @NotBlank
    private final String source;

    @NotBlank
    private final String target;

    @NotBlank
    private final String text;
}
