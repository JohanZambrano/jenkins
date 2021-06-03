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
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.proyectoSpringPrueba.dto.DetalleConsultaDto;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.service.IDetalleConsultaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/detalleConsulta")
public class DetalleConsultaController {

	@Autowired
	private IDetalleConsultaService service;
	
	@PostMapping("/guardar")
	/*@ApiOperation(
			value = "Guarda un profesor",
			notes = "Guarda un profesor"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 201, message = "CREATED", response = Profesor.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })*/
	public ResponseEntity<?> guardar(@RequestBody DetalleConsultaDto profesor){
		try {
			this.service.guardar(profesor);
			return new ResponseEntity<Object>("", HttpStatus.CREATED);	
		} catch (ModelNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("editar")
	@ApiOperation(
			value = "Editar el detalle consulta correspondiente al id",
			notes = "Editar el detalle consulta correspondiente al id"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = DetalleConsulta.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<?> editar(@RequestBody DetalleConsultaDto datos) throws ModelNotFoundException{
		try{
			this.service.editar(datos);
			return new ResponseEntity<Object>("", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("eliminar/{id}")
	@ApiOperation(
			value = "Elimina el detalle consulta correspondiente al id",
			notes = "Elimina el detalle consulta correspondiente al id"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = DetalleConsulta.class ),
			@ApiResponse(code = 400, message = "BAD_REQUEST", response = DetalleConsulta.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = DetalleConsulta.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException{
		this.service.eliminar(id);
		return new ResponseEntity<Object>("", HttpStatus.NO_CONTENT);
		/*if(this.profesorService.eliminarProfesor(id) == "Se elimino correctamente") {
			return new ResponseEntity<String>("Se elimino correctamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("No se elimino correctamente", HttpStatus.NOT_FOUND);
		}*/
		
	}
	
	
	@GetMapping("/retornarTodos")
	@ApiOperation(
			value = "Retorna",
			notes = "Retorna"
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = DetalleConsulta.class ),
			@ApiResponse(code = 204, message = "NOT_CONTENT", response = DetalleConsulta.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<List<DetalleConsulta>> retornarTodos() throws ModelNotFoundException, Exception {
		List<DetalleConsulta> profe = this.service.mostrarTodos();
		if(profe.size() != 0) {
			return new ResponseEntity<List<DetalleConsulta>>(profe, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<DetalleConsulta>>(profe, HttpStatus.NOT_FOUND);
		}
		
		
	}
	
}
