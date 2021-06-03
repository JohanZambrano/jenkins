package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("cundi")
public class UniversidadCundi implements IUniversidad{

	private String nombre;
	
		
	public UniversidadCundi() {
		this.nombre= "Universiad de Cundinamarca";
	}


	@Override
	public String nombreUniversidad() { 
		return  "Universidad de Cundinamarca";
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		

}
