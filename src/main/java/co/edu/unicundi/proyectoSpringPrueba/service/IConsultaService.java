package co.edu.unicundi.proyectoSpringPrueba.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.InvalidFormatExcepcion;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;

public interface IConsultaService extends ICrudService<Consulta, Integer> {
	
	public Consulta retornarPorId(int id) throws ModelNotFoundException;
	
	public List<Consulta> retornar();
	
	public void guardar(Consulta consulta);
	
	public void editar(Consulta consulta) throws ModelNotFoundException;
	
	public void eliminar(Integer id) throws ModelNotFoundException;
	
	public Page<Consulta> listarPaginado(int page, int size);
	
	//public Page<Consulta> findByNombreDoctorAndId(Integer page, Integer cantidad, String nombre, Integer id) throws ModelNotFoundException, InvalidFormatExcepcion ;
	
	//public Page<Consulta> findByNombreDoctorOrId(Integer page, Integer cantidad, String nombre, Integer id) throws ModelNotFoundException, InvalidFormatExcepcion ;
	
	public Page<Consulta> findAllByOrdenByIdAsc(Integer nPage, Integer cantidad) throws InvalidFormatExcepcion, ModelNotFoundException;
	
	public Page<Consulta> findAllByOrdenByIdDesc(Integer nPage, Integer cantidad) throws InvalidFormatExcepcion, ModelNotFoundException;
	
}
