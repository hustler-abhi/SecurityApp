package com.masai.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(UserException.class)
	 public ResponseEntity<MyErrordetails> myUserException(UserException e, WebRequest rq) {
		MyErrordetails err = new MyErrordetails(LocalDate.now(), e.getMessage(), rq.getDescription(false));
		return new ResponseEntity<MyErrordetails>(err,HttpStatus.BAD_GATEWAY);

	}
}
