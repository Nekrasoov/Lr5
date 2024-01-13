package ru.nekrasov.lr2.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nekrasov.lr2.exeption.UnsupportedCodeException;
import ru.nekrasov.lr2.exeption.ValidationFailedException;
import ru.nekrasov.lr2.model.*;
import ru.nekrasov.lr2.service.*;
import ru.nekrasov.lr2.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final  ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;
    private final AnnualBonusService annualBonusService;


    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        ModifyRequestService modifyRequestService,
                        AnnualBonusService annualBonusService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.annualBonusService = annualBonusService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response>feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {



        log.info("request: {}", request);

        Response response = buildResponse(request);
        try {
            validationService.isValid(bindingResult);
            handleUnsupportedCode(request);
        } catch (ValidationFailedException e) {
            return handleValidationException(e, response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            return handleUnsupportedCodeException(e, response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return handleUnknownException(e, response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponseService.modify(response);
        modifyRequestService.modify(request);
        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }

    private Response buildResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .annualBonus(calculateAnnualBonus(request))
                .build();
    }

    private Double calculateAnnualBonus(Request request) {
        int currentYear = 2023;
        return annualBonusService.calculate(request.getPosition(), request.getSalary(), request.getBonus(), request.getWorkDays(), currentYear);
    }

    private void handleUnsupportedCode(Request request) throws UnsupportedCodeException {
        if ("123".equals(request.getUid())) {
            log.error("Received request with unsupported code: 123");
            throw new UnsupportedCodeException("Unsupported code: 123");
        }
    }

    private ResponseEntity<Response> handleValidationException(ValidationFailedException e, Response response, HttpStatus httpStatus) {
        log.error("Validation failed: {}", e.getMessage());
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
        response.setErrorMessage(ErrorMessages.VALIDATION);
        return new ResponseEntity<>(response, httpStatus);
    }

    private ResponseEntity<Response> handleUnsupportedCodeException(UnsupportedCodeException e, Response response, HttpStatus httpStatus) {
        log.error("Unsupported code: {}", e.getMessage());
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
        response.setErrorMessage(ErrorMessages.UNSUPPORTED);
        return new ResponseEntity<>(response, httpStatus);
    }

    private ResponseEntity<Response> handleUnknownException(Exception e, Response response, HttpStatus httpStatus) {
        log.error("Unsupported code: {}", e.getMessage());
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
        response.setErrorMessage(ErrorMessages.VALIDATION);
        return new ResponseEntity<>(response, httpStatus);
    }
}
