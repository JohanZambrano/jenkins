package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("andes")
public class UniversidadAndes implements IUniversidad{

	private String nombre;
	
	public  UniversidadAndes() {
		this.nombre = "Universidad de los Andes";
	}
	
	@Override
	public String nombreUniversidad() {
		return this.nombre;
	}
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	

}
