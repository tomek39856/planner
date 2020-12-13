package com.github.tomek39856.planner.boundary;

import com.github.tomek39856.planner.control.RegisterNotFoundException;
import com.github.tomek39856.planner.entity.NotEnoughMoneyInRegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorControllerAdvice {
    @ExceptionHandler(RegisterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void notFound() {}

    @ExceptionHandler(NotEnoughMoneyInRegisterException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    void notEnoughMoney() {
    }
}
