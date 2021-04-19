package com.github.translation.microservice;

import com.github.translation.language.RestTemplateLanguageRepository;
import com.github.translation.translate.GoogleTranslateUrlBuilder;
import com.github.translation.translate.RestTemplateTranslate;
import com.github.translation.translate.Translate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class TranslateConfig {

    @Value("${translate.api.path}")
    private String url;

    @Value("${translate.api.language.path}")
    private String languageUrl;

    @Value("${translate.api.key}")
    private String key;

    @Bean
    GoogleTranslateUrlBuilder googleTranslateUrlBuilder() {
        return new GoogleTranslateUrlBuilder(url, key);
    }

    @Bean
    Translate translate(GoogleTranslateUrlBuilder googleTranslateUrlBuilder, RestTemplate restTemplate) {
        return new RestTemplateTranslate(googleTranslateUrlBuilder, restTemplate);
    }

    @Bean
    RestTemplateLanguageRepository restTemplateLanguageRepository(RestTemplate restTemplate) {
        return new RestTemplateLanguageRepository(languageUrl, key, restTemplate);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
