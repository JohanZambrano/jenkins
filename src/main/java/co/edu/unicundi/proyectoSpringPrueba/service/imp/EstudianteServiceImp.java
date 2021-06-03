package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.dto.Estudiante;

@Service
public class EstudianteServiceImp implements IEstudianteService{

	@Autowired
	@Qualifier("andes")
	private IUniversidad universidadService;
	
	
	@Override
	public Estudiante retornaInfo() {
		Estudiante estudiante = new Estudiante(1070, "Johans", "González Montero");
		estudiante.setUniversidad(universidadService);
		String nombU = universidadService.nombreUniversidad();
		System.out.println(nombU);
		return estudiante;
	}

}
