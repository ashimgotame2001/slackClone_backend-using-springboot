package com.project.workmanagemantSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BadAlertException extends AbstractThrowableProblem {



    private final String message;
    private final String errorKey;
    private static final String PROBLEM_BASE_URL = "https://localhost:8080/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");

    public BadAlertException(String defaultMessage, String message, String errorKey) {
        this(DEFAULT_TYPE, defaultMessage, message, errorKey);
    }

    public BadAlertException(URI type, String defaultMessage, String message , String errorKey) {
        super(type, defaultMessage, Status.BAD_REQUEST, defaultMessage, type, null, getAlertParameters(message, errorKey));
        this.message = message;
        this.errorKey = errorKey;
    }


    @Override
    public String getMessage() {
        return message;
    }


    public String getErrorKey() {
        return errorKey;
    }

    private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        parameters.put("params", entityName);
        return parameters;
    }

}
