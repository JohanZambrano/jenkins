package co.edu.unicundi.proyectoSpringPrueba.service;

import java.util.List;

import org.springframework.data.domain.Page;

import co.edu.unicundi.proyectoSpringPrueba.dto.ListarPorIdConsultaDto;
import co.edu.unicundi.proyectoSpringPrueba.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpringPrueba.exception.InvalidFormatExcepcion;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.view.ConsultaExamenView;

public interface IConsultaExamenService extends ICrudService<ConsultaExamen, Integer> {

	public void guardarNativo(ConsultaExamen consultaExamen);
	
	List<ListarPorIdConsultaDto> listarPorIdCosnulta(Integer idConsulta);
	
	Page<ConsultaExamen> listarPorIdCosnultaPaginado(Integer id, Integer page, Integer size);
	
	public ConsultaExamen filtrarPorId(Integer consulta, Integer examen) throws ModelNotFoundException;

	public void eliminarConsultaExamen(Integer consulta, Integer examen) throws ModelNotFoundException, InvalidFormatExcepcion;
	
	public Page<ConsultaExamenView> vista(Integer nPage, Integer cantidad);
	
}