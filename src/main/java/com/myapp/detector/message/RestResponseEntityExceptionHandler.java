package com.myapp.detector.message;

import com.myapp.detector.service.exception.FileCrawlerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleUnsupportedOperationException(UnsupportedOperationException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(FileCrawlerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected String handleFileCrawlerException(FileCrawlerException exception) {
        return exception.getMessage();
    }
}
