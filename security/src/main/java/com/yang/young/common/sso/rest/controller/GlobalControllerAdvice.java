package com.yang.young.common.sso.rest.controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yang.young.common.sso.rest.controller.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	
	public VndErrors nullPointerExceptionHandler(NullPointerException e) {
		return new VndErrors("error", "this a internal server error. We will fix ASAP");
	}
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors userNotFoundExceptionHandler(UserNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}
	

}
