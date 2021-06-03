package co.edu.unicundi.proyectoSpringPrueba.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;

public class DetalleConsultaDto {
	
	private Integer id;
	
	private String diagnostico;
	
	private String tratamiento;
		
	private int consulta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public int getConsulta() {
		return consulta;
	}

	public void setConsulta(int consulta) {
		this.consulta = consulta;
	}
	
}
