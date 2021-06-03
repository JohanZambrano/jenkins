package co.edu.unicundi.proyectoSpringPrueba.controller;

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

import co.edu.unicundi.proyectoSpringPrueba.entity.Medico;
import co.edu.unicundi.proyectoSpringPrueba.exception.InvalidFormatExcepcion;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.service.IMedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private IMedicoService service;
	
	@GetMapping("/retornaPorId/{id}")
	public ResponseEntity<?> retornarPorId(@PathVariable int id) throws ModelNotFoundException  {
			Medico medico = service.retornarPorId(id);
			return new ResponseEntity<Medico>(medico, HttpStatus.OK);			
	}	
	
	@GetMapping("/retornarPaginado/{page}/{size}")
	public ResponseEntity<?> retornarPaginado(@PathVariable int page, @PathVariable int size) throws ModelNotFoundException, InvalidFormatExcepcion  {
			Page<Medico> consultaPaginada = service.retornarPaginado(page, size);
			return new ResponseEntity<Page<Medico>>(consultaPaginada, HttpStatus.OK);			
	}	
	
	@GetMapping("/retornarPaginadoOrdenado/{page}/{size}/{ordenado}")
	public ResponseEntity<?> retornarPaginadoOrdenado(@PathVariable int page, @PathVariable int size, @PathVariable String ordenado) throws ModelNotFoundException, InvalidFormatExcepcion  {
			Page<Medico> consultaPaginada = service.retornarPaginadoOrdenado(page, size, ordenado);
			return new ResponseEntity<Page<Medico>>(consultaPaginada, HttpStatus.OK);			
	}	
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Medico medico) throws ModelNotFoundException, InvalidFormatExcepcion {	
			service.guardar(medico);
			return new ResponseEntity<Object>("", HttpStatus.CREATED);				
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> editar(@Valid @RequestBody Medico medico) throws ModelNotFoundException, InvalidFormatExcepcion {	
			service.editar(medico);
			return new ResponseEntity<Object>("", HttpStatus.OK);				
	}	
	
	@DeleteMapping("eliminar/{id}") 
	public ResponseEntity<Object> elimianr(@PathVariable Integer id) throws ModelNotFoundException {
		service.eliminar(id);
		return new ResponseEntity<Object>("", HttpStatus.NO_CONTENT);
	}	
	

}
