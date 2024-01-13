package ru.nekrasov.lr2.service;

import org.springframework.stereotype.Service;
import ru.nekrasov.lr2.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
