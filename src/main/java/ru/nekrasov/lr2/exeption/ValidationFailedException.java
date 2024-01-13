package ru.nekrasov.lr2.exeption;

public class ValidationFailedException extends Exception {
    public  ValidationFailedException(String message) {
        super(message);
    }
}
