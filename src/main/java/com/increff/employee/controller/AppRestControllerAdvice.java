package com.increff.employee.controller;

import com.increff.employee.Exception.ApiException;
import com.increff.employee.model.Data.MessageData;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppRestControllerAdvice {

	@ExceptionHandler(ApiException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public MessageData handle(ApiException e) {
		MessageData data = new MessageData();
		data.setMessage(e.getMessage());
		return data;
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public MessageData handle(Throwable e) {
		e.printStackTrace();
		MessageData data = new MessageData();
		data.setMessage("An unknown error has occurred - " + e.getMessage());
		return data;
	}

	@ExceptionHandler(value = {ConstraintViolationException.class})
	protected ArrayList<String>  handleConflict(ConstraintViolationException ex, WebRequest request) {
		ArrayList<String> errors = ex.getConstraintViolations().stream().map(error -> "Field '" + error.getPropertyPath().toString() + "' " + error.getMessage()).collect(Collectors.toCollection(ArrayList::new));
		return errors;
//		return ResponseHandler.generateResponse(new ApiException(-1, "Bad Input"), HttpStatus.BAD_REQUEST, errors);
	}
}