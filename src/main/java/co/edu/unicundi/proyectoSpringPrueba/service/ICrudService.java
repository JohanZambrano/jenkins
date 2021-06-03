package co.edu.unicundi.proyectoSpringPrueba.service;

import java.util.List;

import org.springframework.data.domain.Page;

import co.edu.unicundi.proyectoSpringPrueba.exception.InvalidFormatExcepcion;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;

public abstract interface ICrudService <T, V> {

	public T retornarPorId(V id) throws ModelNotFoundException;
	
	public Page<T> retornarPaginado(Integer page, Integer size) throws ModelNotFoundException, InvalidFormatExcepcion;
	
	public Page<T> retornarPaginadoOrdenado(Integer page, Integer size, String ordenar) throws ModelNotFoundException, InvalidFormatExcepcion;
	
	public void guardar(T entity) throws ModelNotFoundException, InvalidFormatExcepcion;
	
	public void editar(T entity) throws ModelNotFoundException, InvalidFormatExcepcion;
	
	public void eliminar(V id) throws ModelNotFoundException;	

}
