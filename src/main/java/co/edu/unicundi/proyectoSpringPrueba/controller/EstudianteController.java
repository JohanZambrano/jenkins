package co.edu.unicundi.proyectoSpringPrueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import co.edu.unicundi.proyectoSpringPrueba.dto.Estudiante;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.IEstudianteService;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
	
	@Autowired
	private IEstudianteService estudianteService;

	@GetMapping(path = "/retornar", produces = "application/xml")
	public Estudiante retornarEstudiante() {
		Estudiante est = new Estudiante(1070, "Johans", "González Montero");
		return est;
	}
	
	@GetMapping(path = "/retornarIny")
	public Estudiante retornarEstudianteInyeccion() {
		Estudiante est = estudianteService.retornaInfo();
		return est;
	}
	
	
}
