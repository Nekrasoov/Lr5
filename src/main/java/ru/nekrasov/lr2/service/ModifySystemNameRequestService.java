package ru.nekrasov.lr2.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.nekrasov.lr2.model.Request;
import ru.nekrasov.lr2.model.SystemName;


@Service
public class ModifySystemNameRequestService implements ModifyRequestService {
    @Override
    public void modify(Request request){
        request.setSystemName(SystemName.SERVICE1);
        request.setSource("Service 1 send JSON messages to Service 2");

        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });
    }
}