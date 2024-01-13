package ru.nekrasov.lr2.service;


import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.nekrasov.lr2.exeption.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}
