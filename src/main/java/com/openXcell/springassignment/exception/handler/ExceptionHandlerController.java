package com.openXcell.springassignment.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.openXcell.springassignment.exception.CustomException;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class) // DTO Validation
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e) {

		Map<String, String> errorMap = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CustomException.class)
	public Map<String, String> handleCustomException(CustomException e) {

		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", e.getMessage());

		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	public Map<String, String> handleException(Throwable e) {

		Map<String, String> errorMap = new HashMap<>();

		errorMap.put("errorMessage", "Something Went Wrong, Please contact administrator.");
		e.printStackTrace();

		return errorMap;
	}

}
