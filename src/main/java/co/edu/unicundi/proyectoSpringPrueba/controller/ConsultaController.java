package co.edu.unicundi.proyectoSpringPrueba.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import co.edu.unicundi.proyectoSpringPrueba.dto.ListarPorIdConsultaDto;
import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.exception.InvalidFormatExcepcion;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.service.IConsultaExamenService;
import co.edu.unicundi.proyectoSpringPrueba.service.IConsultaService;
import co.edu.unicundi.proyectoSpringPrueba.view.ConsultaExamenView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ConsultaController {
	
	@Autowired
	private IConsultaService service;
	
	@Autowired
	private IConsultaExamenService serviceConsultaExamen;
	
	@GetMapping("/retornar")
	public ResponseEntity<?> retornar() throws ModelNotFoundException  {
			List<Consulta> listaConsulta = service.retornar();
			return new ResponseEntity<List<Consulta>>(listaConsulta, HttpStatus.OK);			
	}	
	
	@GetMapping("/retornaPorId/{id}")
	public ResponseEntity<?> retornarPorId(@PathVariable int id) throws ModelNotFoundException  {
			Consulta consulta = service.retornarPorId(id);
			return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);			
	}	
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Consulta consulta) {	
			service.guardar(consulta);
			return new ResponseEntity<Object>("", HttpStatus.CREATED);				
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> editar(@Valid @RequestBody Consulta consulta) throws ModelNotFoundException {	
			service.editar(consulta);
			return new ResponseEntity<Object>("", HttpStatus.OK);				
	}	
	
	@DeleteMapping("eliminar/{id}") 
	public ResponseEntity<Object> elimianr(@PathVariable int id) throws ModelNotFoundException {
		service.eliminar(id);
		return new ResponseEntity<Object>("", HttpStatus.NO_CONTENT);
	}	
	
	@GetMapping("/consultaPaginado/{page}/{size}")	
	public ResponseEntity<?> consultaPaginado(@PathVariable int page, @PathVariable int size) throws ModelNotFoundException{
		return new ResponseEntity<Object>(this.service.listarPaginado(page, size), HttpStatus.OK);
	}
	
	@GetMapping("/consultaOr/{page}/{size}/{nombre}/{id}")
	@ApiOperation(
			value = "Retorna el nombre de la consulta con el id, Servicio paginado.",
			notes = "Retorna el nombre de la consulta con el id, Servicio paginado."
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Consulta.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = Consulta.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<?> consultaPaginadoOr(@PathVariable int page, @PathVariable int size, @PathVariable String nombre , @PathVariable int id) throws ModelNotFoundException, InvalidFormatExcepcion{
		return null;
		//return new ResponseEntity<Page<Consulta>>(this.service.findByNombreDoctorOrId(page, size, nombre, id), HttpStatus.OK);
	}
	
	@GetMapping("/consultaAnd/{page}/{size}/{nombre}/{id}")
	@ApiOperation(
			value = "Retorna el nombre de la consulta con el id, Servicio paginado.",
			notes = "Retorna el nombre de la consulta con el id, Servicio paginado."
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Consulta.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = Consulta.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class)})
	public ResponseEntity<?> consultaPaginadoAnd(@PathVariable int page, @PathVariable int size, @PathVariable String nombre , @PathVariable int id) throws ModelNotFoundException, InvalidFormatExcepcion{
		return null;
		//return new ResponseEntity<Page<Consulta>>(this.service.findByNombreDoctorAndId(page, size, nombre, id), HttpStatus.OK);
	}
	
	@GetMapping("/consultaAscendente/{page}/{size}")	
	@ApiOperation(
			value = "Servicio paginado con orden ascendente.",
			notes = "Servicio paginado con orden ascendente."
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Consulta.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = Consulta.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<?> consultaPaginadoAscendente(@PathVariable int page, @PathVariable int size) throws ModelNotFoundException, InvalidFormatExcepcion{
		return new ResponseEntity<Page<Consulta>>(this.service.findAllByOrdenByIdAsc(page, size), HttpStatus.OK);
	}
	
	@GetMapping("/consultaDescendente/{page}/{size}")	
	@ApiOperation(
			value = "Servicio paginado con orden descendente.",
			notes = "Servicio paginado con orden descendente."
			)
			@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Consulta.class ),
			@ApiResponse(code = 404, message = "NOT_FOUND", response = Consulta.class ),
			@ApiResponse(code = 503, message = "Servicio no Disponible", response = String.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	public ResponseEntity<?> consultaPaginadoDescendente(@PathVariable int page, @PathVariable int size) throws ModelNotFoundException, InvalidFormatExcepcion{
		return new ResponseEntity<Page<Consulta>>(this.service.findAllByOrdenByIdDesc(page, size), HttpStatus.OK);
	}
	
	@GetMapping("/listarCEPorIdCosnulta/{id}")
	public ResponseEntity<?> listarCEPorIdCosnulta(@PathVariable int id) throws ModelNotFoundException  {
			List<ListarPorIdConsultaDto> lista = serviceConsultaExamen.listarPorIdCosnulta(id);
			return new ResponseEntity<List<ListarPorIdConsultaDto>>(lista, HttpStatus.OK);			
	}	
	
	@GetMapping("/listarCEPorIdCosnultaPaginado/{id}/{page}/{size}")
	public ResponseEntity<?> listarCEPorIdCosnultaPaginado(@PathVariable int id,
			@PathVariable int page,
			@PathVariable int size ) throws ModelNotFoundException  {
			Page<ConsultaExamen> lista = serviceConsultaExamen.listarPorIdCosnultaPaginado(id, page, size);
			return new ResponseEntity<Page<ConsultaExamen>>(lista, HttpStatus.OK);			
	}		
	
	@PostMapping("/guardarConsultaExamen")
	public ResponseEntity<?> guardarConsultaExamen(@Valid @RequestBody ConsultaExamen consulta) throws ModelNotFoundException, InvalidFormatExcepcion {	
			serviceConsultaExamen.guardar(consulta);
			return new ResponseEntity<Object>("", HttpStatus.CREATED);				
	}
	
	@PutMapping("/editarConsultaExamen")
	public ResponseEntity<?> editarConsultaExamen(@Valid @RequestBody ConsultaExamen consulta) throws ModelNotFoundException, InvalidFormatExcepcion {	
			serviceConsultaExamen.editar(consulta);
			return new ResponseEntity<Object>("", HttpStatus.OK);				
	}	
	
	@DeleteMapping("eliminarConsultaExamen/{consulta}/{examen}") 
	public ResponseEntity<Object> eliminarConsultaExamen(@PathVariable int consulta, @PathVariable int examen) throws ModelNotFoundException, InvalidFormatExcepcion {
		serviceConsultaExamen.eliminarConsultaExamen(consulta, examen);
		return new ResponseEntity<Object>("", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/retornarIdConExa/{consulta}/{examen}")
	public ResponseEntity<?> retornarPorIdConsultaExamen(@PathVariable int consulta, @PathVariable int examen) throws ModelNotFoundException  {			
		ConsultaExamen dato = serviceConsultaExamen.filtrarPorId(consulta, examen);
		return new ResponseEntity<ConsultaExamen>(dato, HttpStatus.OK);			
	}
	
	@GetMapping("/retornarPaginadoConExa/{page}/{size}")
	public ResponseEntity<?> retornarPaginadoConsultaExamen(
			@PathVariable int page,
			@PathVariable int size ) throws ModelNotFoundException, InvalidFormatExcepcion  {
			Page<ConsultaExamen> lista = serviceConsultaExamen.retornarPaginado(page, size);
			return new ResponseEntity<Page<ConsultaExamen>>(lista, HttpStatus.OK);			
	}

	@GetMapping("/retornarVistaConsultaExamen/{page}/{size}")
	public ResponseEntity<?> retornarVistaConsultaExamen(
			@PathVariable int page,
			@PathVariable int size ) throws ModelNotFoundException, InvalidFormatExcepcion  {
			Page<ConsultaExamenView> lista = serviceConsultaExamen.vista(page, size);
			return new ResponseEntity<Page<ConsultaExamenView>>(lista, HttpStatus.OK);			
	}
	
}