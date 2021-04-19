package com.github.translation.translate;

import org.springframework.web.client.RestTemplate;
import java.util.Optional;

public class RestTemplateTranslate implements Translate {

    private final GoogleTranslateUrlBuilder googleTranslateUrlBuilder;
    private final RestTemplate restTemplate;

    public RestTemplateTranslate(GoogleTranslateUrlBuilder googleTranslateUrlBuilder, RestTemplate restTemplate) {
        this.googleTranslateUrlBuilder = googleTranslateUrlBuilder;
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<TranslatedMessage> translateMessage(Message message) {
        final String url = googleTranslateUrlBuilder.ofMessage(message).build();
        final GoogleTranslateResponse googleTranslateResponse = restTemplate.postForObject(url, null, GoogleTranslateResponse.class);
        return createTranslateMessage(message, googleTranslateResponse);
    }

    private Optional<TranslatedMessage> createTranslateMessage(Message message, GoogleTranslateResponse googleTranslateResponse) {
        if(googleTranslateResponse != null && googleTranslateResponse.getData() != null && googleTranslateResponse.getData().getTranslations() != null ) {
            final Optional<TranslatedText> translatedText = googleTranslateResponse.getData().getTranslations().stream().findFirst();
            return translatedText.map(text -> processCreatingMessage(text, message));
        }

        return Optional.empty();
    }

    private TranslatedMessage processCreatingMessage(TranslatedText translatedText, Message message) {
        final String decoded = translatedText.getTranslatedText();
        return TranslatedMessage.builder().sourceLanguage(message.getTarget()).translatedText(decoded).build();
    }
}
