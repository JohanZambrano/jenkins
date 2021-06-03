package co.edu.unicundi.proyectoSpringPrueba.service;

import java.util.List;

import co.edu.unicundi.proyectoSpringPrueba.dto.DetalleConsultaDto;
import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;

public interface IDetalleConsultaService extends ICrudService<DetalleConsulta, Integer>{

	public DetalleConsulta buscarId(int id) throws ModelNotFoundException;
	
	public List<DetalleConsulta> mostrarTodos();
	
	public void guardar(DetalleConsultaDto datos) throws ModelNotFoundException;
	
	public void editar(DetalleConsultaDto datos) throws ModelNotFoundException;
	
	public void eliminar(int id) throws ModelNotFoundException;
	
}
