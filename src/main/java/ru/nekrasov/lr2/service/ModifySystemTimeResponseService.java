package ru.nekrasov.lr2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.nekrasov.lr2.model.Response;
import ru.nekrasov.lr2.util.DateTimeUtil;

import java.util.Date;
import java.util.UUID;

@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {
        UUID uuid = UUID.randomUUID();
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        return response;
    }
}
