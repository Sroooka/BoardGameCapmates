package com.capgemini.jstk.BoardGameCapmates.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.capgemini.jstk.BoardGameCapmates.exceptions.ErrorMessage;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;

@ControllerAdvice
public class GlobalErrorController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorController.class);

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage playerExceptionHandler(Exception ex) {
		LOGGER.error("Error in player service: ", ex);
		return new ErrorMessage(ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(NonExistingPlayerException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage playerNotFoundException(NonExistingPlayerException ex) {
		LOGGER.error("Error in player service: ", ex);
		return new ErrorMessage(ex.getMessage());
	}
}
