package co.edu.unicundi.proyectoSpringPrueba.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.exception.ExceptionResponseHandler;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.service.IProfesorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.*;

@RestController
@Api(tags = "Controller de Profesores", produces = "application/json")
@RequestMapping("/profesores")
public class ProfesorController {
	
	
	@Autowired
	private IProfesorService profesorService;
	
	
	//No utilizar
	@RequestMapping(value = "/retonarNombre", method = RequestMethod.GET)
	public String retornarNombre() {
		return "Johans ";
		
	}
	
	//@GetMapping(path = "/retornarApellido", produces = "application/xml")
	@GetMapping("/retornarApellido")
	public String retornarApellido() {
		return "González";
	}
	
	@GetMapping("/retornarInfo/{nombre}/{apellido}")
	public String retornarInfo(	@PathVariable String nombre, 
								@PathVariable String apellido) {
		return nombre + " " + apellido;
	}
	
	@PostMapping("/guardar")
	/*@ApiOperation(
			value = "Guarda un profesor",
			notes = "Guarda un profesor"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 201, message = "CREATED", response = Profesor.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })*/
	public ResponseEntity<?> guardar(@Valid @RequestBody Profesor profesor){
		System.out.println("Entro " + profesor.getNombre());
		try {
			this.profesorService.guardarProfesor(profesor);
		} catch (ModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("", HttpStatus.CREATED);	
		/*if(this.profesorService.guardarProfesor(profesor) == "Se creo correctamente") {
			return new ResponseEntity<String>("Se creo correctamente", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("No se creo correctamente", HttpStatus.BAD_REQUEST);
		}*/
	}
	
	@PutMapping("editar")
	@ApiOperation(
			value = "Editar al profesor correspondiente al id",
			notes = "Editar al profesor correspondiente al id"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Profesor.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<?> editar(@RequestBody Profesor profesor) throws ModelNotFoundException{
		//System.out.println(var + " " + profesor.getId() + " " + profesor.getNombre() + " " + profesor.getApellido());
		this.profesorService.editarProfesor(profesor);
		return new ResponseEntity<Object>("", HttpStatus.OK);
		/*if(this.profesorService.editarProfesor(profesor) == "Se actualizo correctamente") {
			return new ResponseEntity<String>("Se actualizo correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("No se puedo actualizar correctamente", HttpStatus.NOT_FOUND);
		}*/
	}
	
	@DeleteMapping("eliminar/{id}")
	@ApiOperation(
			value = "Elimina el profesor correspondiente al id",
			notes = "Elimina el profesor correspondiente al id"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Profesor.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = Profesor.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException{
		this.profesorService.eliminarProfesor(id);
		return new ResponseEntity<Object>("", HttpStatus.NO_CONTENT);
		/*if(this.profesorService.eliminarProfesor(id) == "Se elimino correctamente") {
			return new ResponseEntity<String>("Se elimino correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("No se elimino correctamente", HttpStatus.NOT_FOUND);
		}*/
		
	}
	
	
	@GetMapping("/retornarTodos")
	@ApiOperation(
			value = "Retorna los profesores",
			notes = "Retorna los profesores"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Profesor.class ),
			@ApiResponse(code = 204, message = "NOT_CONTENT", response = Profesor.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<List<Profesor>> retornarTodos() throws ModelNotFoundException, Exception {
		List<Profesor> profe = this.profesorService.mostrarTodos();
		if(profe.size() != 0) {
			return new ResponseEntity<List<Profesor>>(profe, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<Profesor>>(profe, HttpStatus.NOT_FOUND);
		}
		
	}

	@GetMapping("/retornarProfesorId/{id}")
	@ApiOperation(
			value = "Retorna el profesor correspondiente al id",
			notes = "Retorna el profesor correspondiente al id"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Profesor.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = Profesor.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<Profesor> retornarProfesorId(@PathVariable int id) throws ModelNotFoundException{
		Profesor profe = this.profesorService.buscarId(id);
		if(profe != null) {
			return new ResponseEntity<Profesor>(profe, HttpStatus.OK);
		}else {
			return new ResponseEntity<Profesor>(profe, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/buscarCedulaNombramiento/{id}")
	@ApiOperation(
			value = "Retorna el profesor correspondiente a la cedula",
			notes = "Retorna el profesor correspondiente al id"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Profesor.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = Profesor.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<Profesor> buscarCedulaNombramiento(@PathVariable String id) throws ModelNotFoundException{
		Profesor profe = this.profesorService.buscarNombramiento(id);
		if(profe != null) {
			return new ResponseEntity<Profesor>(profe, HttpStatus.OK);
		}else {
			return new ResponseEntity<Profesor>(profe, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/buscarCedulaSQL/{id}")
	@ApiOperation(
			value = "Retorna el profesor correspondiente a la cedula",
			notes = "Retorna el profesor correspondiente al id"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Profesor.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = Profesor.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<Profesor> buscarCedulaSQL(@PathVariable String id) throws ModelNotFoundException{
		Profesor profe = this.profesorService.buscarSQL(id);
		if(profe != null) {
			return new ResponseEntity<Profesor>(profe, HttpStatus.OK);
		}else {
			return new ResponseEntity<Profesor>(profe, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/buscarCedulaJPQL/{id}")
	@ApiOperation(
			value = "Retorna el profesor correspondiente a la cedula",
			notes = "Retorna el profesor correspondiente al id"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Profesor.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = Profesor.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<?> buscarCedulaJPQL(@PathVariable String id) throws ModelNotFoundException{
		List<Profesor> profe = this.profesorService.buscarJPQL(id);
		if(profe != null) {
			return new ResponseEntity<Object>(profe, HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(profe, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/consultaSort")
	@ApiOperation(
			value = "Retorna el profesor correspondiente a la cedula",
			notes = "Retorna el profesor correspondiente al id"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Profesor.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = Profesor.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<?> consultaSort() throws ModelNotFoundException{
		List<Profesor> profe = this.profesorService.sort1();
		if(profe != null) {
			return new ResponseEntity<Object>(profe, HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(profe, HttpStatus.NOT_FOUND);
		}
	}
	

	@GetMapping("/consultaPaginado/{page}/{size}")
	@ApiOperation(
			value = "Retorna el profesor correspondiente a la cedula",
			notes = "Retorna el profesor correspondiente al id"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Profesor.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = Profesor.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<?> consultaPaginado(@PathVariable int page, @PathVariable int size) throws ModelNotFoundException{
		return new ResponseEntity<Object>(this.profesorService.listarPaginado(page, size), HttpStatus.OK);
		
	}
}
