package com.tenet.web.rest.common.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseExceptionResponse;
import com.tenet.web.rest.common.exception.BadRequestException;
import com.tenet.web.rest.common.exception.CustomException;
import com.tenet.web.rest.common.exception.ResourceAlreadyExistsException;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.exception.UnauthorizedException;

@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<BaseExceptionResponse> resourceNotFound(ResourceNotFoundException exception,
			WebRequest request) {
		return new ResponseEntity<BaseExceptionResponse>(buildResponse(HttpStatus.NOT_FOUND, exception, request),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<BaseExceptionResponse> resourceAlreadyExists(ResourceAlreadyExistsException exception,
			WebRequest request) {
		return new ResponseEntity<BaseExceptionResponse>(buildResponse(HttpStatus.CONFLICT, exception, request),
				HttpStatus.CONFLICT);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BaseExceptionResponse> customException(CustomException exception, WebRequest request) {
		return new ResponseEntity<BaseExceptionResponse>(buildResponse(HttpStatus.BAD_REQUEST, exception, request),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<BaseExceptionResponse> unauthorizedException(UnauthorizedException exception,
			WebRequest request) {
		return new ResponseEntity<BaseExceptionResponse>(buildResponse(HttpStatus.UNAUTHORIZED, exception, request),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<BaseExceptionResponse> handleAllExceptions(RuntimeException exception,
			WebRequest request) {
		BaseExceptionResponse response = buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception, request);
		response.setErrorMessage(ApplicationConstants.ERROR_MSG_INTERNAL_SERVER_ERROR);
		return new ResponseEntity<BaseExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BaseExceptionResponse response = buildResponse(HttpStatus.BAD_REQUEST, exception, request);
		ObjectError error = exception.getBindingResult().getAllErrors().get(0);
		response.setErrorMessage(error.getDefaultMessage());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BaseExceptionResponse response = buildResponse(HttpStatus.BAD_REQUEST, exception, request);
		response.setErrorMessage(ApplicationConstants.ERROR_MSG_MALFORMED_JSON_REQUEST);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	private BaseExceptionResponse buildResponse(HttpStatus status, Exception exception, WebRequest webRequest) {
		BaseExceptionResponse response = new BaseExceptionResponse();
		response.setStatus(status.value());
		response.setErrorCode(status.name());
		response.setErrorMessage(exception.getMessage());
		return response;
	}
}
