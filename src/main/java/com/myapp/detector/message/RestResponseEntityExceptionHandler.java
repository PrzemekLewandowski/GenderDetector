package com.myapp.detector.message;

import com.myapp.detector.service.exception.FileServiceException;
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
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected String handleUnsupportedOperationException(UnsupportedOperationException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(FileServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected String handleFileCrawlerException(FileServiceException exception) {
        return exception.getMessage();
    }
}
