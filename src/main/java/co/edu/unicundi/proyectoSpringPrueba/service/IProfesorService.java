package co.edu.unicundi.proyectoSpringPrueba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;


public interface IProfesorService {

	public Profesor buscarId(int id) throws ModelNotFoundException;
	
	public List<Profesor> mostrarTodos();
	
	public void guardarProfesor(Profesor profesor) throws ModelNotFoundException;
	
	public void editarProfesor(Profesor profesor) throws ModelNotFoundException;
	
	public void eliminarProfesor(int id) throws ModelNotFoundException;
	
	public Profesor buscarNombramiento(String cedula);
	
	public Profesor buscarSQL(String cedula);
	
	public List<Profesor> buscarJPQL(String cedula);
	
	public ArrayList<Profesor> sort1();
	
	public Page<Profesor> listarPaginado(int page, int size);
	
}
