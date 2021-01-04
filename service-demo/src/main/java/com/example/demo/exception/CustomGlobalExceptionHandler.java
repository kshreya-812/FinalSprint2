package com.example.demo.exception;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.payload.*;


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private static Logger logger=LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);
	private final String BAD_REQUEST = "BAD_REQUEST";
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> customHandleNotFound(Exception ex, WebRequest request) {
		
		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.NOT_FOUND.value());
		logger.error("Exception :: "+ex.getMessage());
		return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);

	}	

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ValidationErrorResponse> handleConstraintViolation(ConstraintViolationException ex,
			WebRequest request) {
		List<String> details = ex.getConstraintViolations().parallelStream().map(e -> e.getMessage())
				.collect(Collectors.toList());

		ValidationErrorResponse validationErrResp = new ValidationErrorResponse();		
		validationErrResp.setTimestamp(LocalDateTime.now());
		validationErrResp.setMessage(BAD_REQUEST);
		validationErrResp.setStatusCode(HttpStatus.BAD_REQUEST.value());
		validationErrResp.setDetails(details);
		logger.error("Exception :: "+ex.getMessage());
		return new ResponseEntity<>(validationErrResp, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OperationFailedException.class)
	public final ResponseEntity<?> handleOperationFailedViolation(OperationFailedException ex,
			WebRequest request) {
		
		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		logger.error("Exception :: "+ex.getMessage());
		return new ResponseEntity<>("Operation failed.", HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public final ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
//		Map<String, String> errors = new HashMap<>();
//	    ex.getBindingResult().getAllErrors().forEach((error) -> {
//	        String fieldName = ((FieldError) error).getField();
//	        String errorMessage = error.getDefaultMessage();
//	        errors.put(fieldName, errorMessage);
//	    });
//
//		//ErrorResponse error = new ErrorResponse(BAD_REQUEST, errors);
//		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> customHandleForServerError(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		logger.error("Exception :: "+ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	@ExceptionHandler(OpenServiceFoundException.class)
	public final ResponseEntity<?> handleNoOpenServiceFoundViolation(OpenServiceFoundException ex,
			WebRequest request) {
		
		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		logger.error("Exception :: "+ex.getMessage());
		return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);	
	}
	
}
