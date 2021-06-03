package co.edu.unicundi.proyectoSpringPrueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFormatExcepcion extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidFormatExcepcion(String message) {
		super(message);
	}
}
