package com.github.translation.translate;

public class GoogleTranslateUrlBuilder {

    private final String path;
    private final String key;

    private String source;
    private String target;
    private String text;

    public GoogleTranslateUrlBuilder(String path, String key) {
        this.path = path;
        this.key = key;
        this.source = "";
        this.target = "";
        this.text = "";
    }

    public GoogleTranslateUrlBuilder sourceLanguage(String source) {
        this.source = source;
        return this;
    }

    public GoogleTranslateUrlBuilder destinationLanguage(String target) {
        this.target = target;
        return this;
    }

    public GoogleTranslateUrlBuilder text(String text) {
        this.text = text;
        return this;
    }

    String build() {
        return String.format(path + "?key=%s&q=%s&target=%s&source=%s", key, text, target, source);
    }

    public GoogleTranslateUrlBuilder ofMessage(Message message) {
        return this.destinationLanguage(message.getTarget())
                .sourceLanguage(message.getSource())
                .text(message.getText());
    }

}
