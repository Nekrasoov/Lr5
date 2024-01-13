package ru.nekrasov.lr2.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {

    EMPTY(""),
    VALIDATION("Ошибка валидации"),
    UNSUPPORTED("Проищошла непредвиденная ошибка"),
    UNKNOWN("Не поддерживаемая ошибка");
    private final String description;

    ErrorMessages(String description){
        this.description = description;
    }

    @JsonValue
    public String getName(){
        return description;
    }
}