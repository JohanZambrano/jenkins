package co.edu.unicundi.proyectoSpringPrueba.dto;

import javax.validation.constraints.NotNull;

// DOCUMENTACIÓN 
public class Profesor {

	@NotNull(message = "Id requerido")
	private Integer id;
	
	@NotNull(message = "Nombre requerido")
	private String nombre;
	
	@NotNull(message = "Apellido requerido")
	private String apellido;
	
	public Profesor() {
		
	}
	
	/**
	 * Constructor con variables
	 * @param id
	 * @param nombre
	 * @param apellido
	 */
	public Profesor(Integer id, String nombre, String apellido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
}
