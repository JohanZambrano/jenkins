package co.edu.unicundi.proyectoSpringPrueba.exception;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;


import co.edu.unicundi.proyectoSpringPrueba.dto.ExceptionResponse;

@ControllerAdvice
@RestController
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

	// Excepciones de JAVA
	@ExceptionHandler(ModelNotFoundException.class)
	public ResponseEntity<ExceptionResponse> filtroModelNotFoundException(ModelNotFoundException ex, HttpStatus status,
			WebRequest request) {
		ex.printStackTrace();
		ExceptionResponse exp = new ExceptionResponse(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<ExceptionResponse> filtroAritmeticException(ArithmeticException ex, HttpStatus status,
			WebRequest request) {
		ex.printStackTrace();
		ExceptionResponse exp = new ExceptionResponse(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exp = new ExceptionResponse(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exp, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
	ExceptionResponse exp = new ExceptionResponse(String.valueOf(status.value()), status.name(), "JSON mal formado", request.getDescription(false));
	return new ResponseEntity<Object>(exp, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
	ExceptionResponse exp = new ExceptionResponse(String.valueOf(status.value()), status.name(), ex.getMessage(), request.getDescription(false));
	return new ResponseEntity<Object>(exp, HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
	ExceptionResponse exp = new ExceptionResponse(String.valueOf(status.value()), status.name(), ex.getMessage(), request.getDescription(false));
	return new ResponseEntity<Object>(exp, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> filtroException(Exception ex, HttpStatus status, WebRequest request){
	ex.printStackTrace();
	ExceptionResponse exp = new ExceptionResponse(String.valueOf(status.value()), status.name(), ex.getMessage(), request.getDescription(false));
	return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	
	
	
	
	
}