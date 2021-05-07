package com.github.translation.microservice;

import com.github.translation.language.Language;
import com.github.translation.language.LanguageCacheRepository;
import com.github.translation.language.LanguageRepository;
import com.github.translation.translate.Message;
import com.github.translation.translate.Translate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
class TranslateController {

    private final Translate translate;
    private final LanguageCacheRepository languageRepository;

    TranslateController(Translate translate, LanguageCacheRepository languageRepository) {
        this.translate = translate;
        this.languageRepository = languageRepository;
    }

    @PostMapping("/translate")
    ResponseEntity<?> translate( @Validated @RequestBody Message message) {
        if(!languageExists(message.getSource())) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder().error("Source language does not exists").build());
        }

        if(!languageExists(message.getTarget())) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder().error("Target language does not exists").build());
        }

        final Optional<Message> translatedMessage = translate.translateMessage(message);
        return (translatedMessage.isPresent()) ? ResponseEntity.ok(createTranslationResponse(translatedMessage.get())) : ResponseEntity.ok().build();
    }

    @GetMapping("/languages")
    List<Language> languages() {
        return languageRepository.findLanguages();
    }

    private boolean languageExists(String candidate) {
        final List<Language> languages = languageRepository.findLanguages();
        return languages.stream().anyMatch((language) -> language.getLanguage().toLowerCase().equals(candidate.toLowerCase()));
    }

    private TranslationResponse createTranslationResponse(Message translatedMessage) {
        return TranslationResponse.builder().source(translatedMessage.getSource())
                                            .text(translatedMessage.getText())
                                            .build();
    }

}
