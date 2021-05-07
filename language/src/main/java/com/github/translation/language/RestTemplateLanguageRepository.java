package com.github.translation.language;

import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestTemplateLanguageRepository implements LanguageCacheRepository {

    private final RestTemplate restTemplate;
    private final String url;
    private final String key;
    private GoogleLanguageResponse googleLanguages;

    public RestTemplateLanguageRepository(String url, String key, RestTemplate restTemplate) {
        this.url = url;
        this.key = key;
        this.restTemplate = restTemplate;
        this.googleLanguages = null;
    }

    @Override
    public List<Language> findLanguages() {
        if(!exists(googleLanguages)) {
            updateLanguages();
        }

        return processFindingLanguages();
    }

    @Override
    public void updateLanguages() {
        final String url = buildUrl();
        final GoogleLanguageResponse googleLanguageResponses = restTemplate.postForObject(url, null, GoogleLanguageResponse.class);
        this.googleLanguages = exists(googleLanguageResponses) ? googleLanguageResponses : googleLanguages;
    }

    private List<Language> processFindingLanguages() {
        return (exists(googleLanguages))
            ? googleLanguages.getData().getLanguages().stream().map(google -> new Language(google.getLanguage())).collect(Collectors.toList())
            : new ArrayList<>();
    }

    private boolean exists(GoogleLanguageResponse googleLanguageResponse) {
        return googleLanguageResponse != null && googleLanguageResponse.getData() != null && googleLanguageResponse.getData().getLanguages() != null
            && !googleLanguageResponse.getData().getLanguages().isEmpty();
    }

    private String buildUrl() {
        return url + "?key=" + key;
    }
}
