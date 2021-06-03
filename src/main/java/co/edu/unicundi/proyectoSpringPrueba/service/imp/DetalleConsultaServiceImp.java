package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.dto.DetalleConsultaDto;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaRepo;
import co.edu.unicundi.proyectoSpringPrueba.repository.IDetalleConsultaRepo;
import co.edu.unicundi.proyectoSpringPrueba.service.IConsultaService;
import co.edu.unicundi.proyectoSpringPrueba.service.IDetalleConsultaService;

@Service
public class DetalleConsultaServiceImp implements IDetalleConsultaService {

	@Autowired
	private IDetalleConsultaRepo repo;
	
	@Autowired
	private IConsultaService repoConsulta;
	
	@Override
	public List<DetalleConsulta> mostrarTodos() {
		return repo.findAll();
	}

	@Override
	public void guardar(DetalleConsultaDto datos) throws ModelNotFoundException {
		
		DetalleConsulta guardar = new DetalleConsulta();
		guardar.setConsulta(repoConsulta.retornarPorId(datos.getConsulta()));
		guardar.setDiagnostico(datos.getDiagnostico());
		guardar.setTratamiento(datos.getTratamiento());
		repo.save(guardar);
		
	}

	@Override
	public void editar(DetalleConsultaDto datos) throws ModelNotFoundException {
		;
		if(buscarId(datos.getId())!= null) {
			DetalleConsulta editar = new DetalleConsulta();
			editar.setDiagnostico(datos.getDiagnostico());
			editar.setTratamiento(datos.getTratamiento());
			editar.setId(datos.getId());
			editar.setConsulta(repoConsulta.retornarPorId(datos.getConsulta()));
			repo.save(editar);
		}
	}

	@Override
	public void eliminar(int id) throws ModelNotFoundException {		
		repo.delete(buscarId(id));
		
	}

	@Override
	public DetalleConsulta buscarId(int id) throws ModelNotFoundException {
		DetalleConsulta datos = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Detalle consulta no encontrado"));
		return datos;	
	}

	@Override
	public DetalleConsulta retornarPorId(Integer id) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DetalleConsulta> retornarPaginado(Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DetalleConsulta> retornarPaginadoOrdenado(Integer page, Integer size, String ordenar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(DetalleConsulta entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(DetalleConsulta entity) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
