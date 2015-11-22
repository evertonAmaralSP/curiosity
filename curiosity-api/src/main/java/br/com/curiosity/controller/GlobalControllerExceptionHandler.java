package br.com.curiosity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.curiosity.exception.base.CuriosityRuntimeException;
import br.com.curiosity.model.StatusError;

@ControllerAdvice
class GlobalControllerExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(CuriosityRuntimeException.class)
    @ResponseBody
    public StatusError handleCuriosityRuntimeException (final Exception ex) {
        return new StatusError(HttpStatus.BAD_REQUEST.toString(),ex.getMessage());
    }
}
