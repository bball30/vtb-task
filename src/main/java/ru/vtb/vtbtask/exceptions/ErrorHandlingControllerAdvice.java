package ru.vtb.vtbtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.vtb.vtbtask.messages.ErrorResponse;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {
    @ExceptionHandler(InvalidParamException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onInvalidParamException(InvalidParamException e) {
        return new ErrorResponse(e.getMessage());
    }
}
