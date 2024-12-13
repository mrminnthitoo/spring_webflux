package com.minnthitoo.spring_webflux.controller.advice;

import com.minnthitoo.spring_webflux.model.dto.RestResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<RestResponse> handleException(WebExchangeBindException exception){
        final BindingResult bindingResult = exception.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        final Map<String, String> errors = new HashMap<>();
        fieldErrors.forEach(error-> errors.put(error.getField(), error.getDefaultMessage()));
        RestResponse restResponse = new RestResponse();
        restResponse.setMessage("Validation Error");
        restResponse.setError(errors);
        return ResponseEntity.badRequest().body(restResponse);
    }

}
