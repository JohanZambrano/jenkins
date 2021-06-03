package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.InvalidFormatExcepcion;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaRepo;
import co.edu.unicundi.proyectoSpringPrueba.service.IConsultaService;
import co.edu.unicundi.proyectoSpringPrueba.service.ICrudService;

@Service
public class ConsultaServiceImp implements IConsultaService {

	@Autowired
	private IConsultaRepo repo;
	
	@Override
	public Consulta retornarPorId(int id) throws ModelNotFoundException {
		Consulta consulta = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Consulta no encontrada"));
		//consulta.setDetalleConsulta(null);
		//consulta.getDetalleConsulta().clear();
		return consulta;
	}

	@Override
	public List<Consulta> retornar() {
		List<Consulta> listaConsulta = repo.findAll();
		for (Consulta consulta : listaConsulta) {
			consulta.setDetalleConsulta(null);
		}
		return listaConsulta;
	}

	@Override
	public void guardar(Consulta consulta) {		
		consulta.setId(null);
		if(consulta.getDetalleConsulta() != null) {
			consulta.getDetalleConsulta().forEach(det -> {
				det.setConsulta(consulta);
			});
		}
		repo.save(consulta);
	}

	@Override
	public void editar(Consulta consulta) throws ModelNotFoundException {
		Consulta con = this.retornarPorId(consulta.getId());
		con.setMedico(consulta.getMedico());
		con.setFecha(consulta.getFecha());
		repo.save(con);
	}

	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		//Cambiar por consulta mas optima
		this.retornarPorId(id);
		repo.deleteById(id);
	}

	@Override
	public Page<Consulta> listarPaginado(int page, int size) {
		return this.repo.findAll(PageRequest.of(page, size, Sort.by("nombreDoctor").ascending()));
	}

	/*@Override
	public Page<Consulta> findByNombreDoctorAndId(Integer nPage, Integer cantidad, String nombre, Integer id) throws ModelNotFoundException, InvalidFormatExcepcion {
		if(nPage > -1 && cantidad>0) {
			PageRequest pageR = PageRequest.of(nPage, cantidad);
			Page<Consulta> page = repo.findByNombreDoctorAndId(pageR, nombre, id);
			if(page.getContent().size() > 0) {				
				if(nPage < page.getTotalPages()) {
					return page;
				}else {
					throw new ModelNotFoundException("Error, el numero de pagina excede el limite de paginas encontradas");
				}
			}else {
				throw new ModelNotFoundException("No se encontraron datos");
			}
		}else {
			throw new InvalidFormatExcepcion("Error, con los datos de pagina y cantidad");
		}
		
	}

	@Override
	public Page<Consulta> findByNombreDoctorOrId(Integer nPage, Integer cantidad, String nombre, Integer id) throws ModelNotFoundException, InvalidFormatExcepcion{
		if(nPage > -1 && cantidad>0) {
			PageRequest pageR = PageRequest.of(nPage, cantidad);
			Page<Consulta> page = repo.findByNombreDoctorOrId(pageR, nombre, id);
			if(page.getContent().size() > 0) {
				if(nPage < page.getTotalPages()) {
					return page;
				}else {
					throw new ModelNotFoundException("Error, el numero de pagina excede el limite de paginas encontradas");
				}
			}else {
				throw new ModelNotFoundException("No se encontraron datos");
			}
		}else {
			throw new InvalidFormatExcepcion("Error, con los datos de pagina y cantidad");
		}
	}*/

	@Override
	public Page<Consulta> findAllByOrdenByIdAsc(Integer nPage, Integer cantidad) throws InvalidFormatExcepcion, ModelNotFoundException {
		if(nPage > -1 && cantidad>0) {
			Page<Consulta> page = this.repo.findAll(PageRequest.of(nPage, cantidad, Sort.by("id").ascending()));
			if(nPage < page.getTotalPages()) {
				return page;
			}else {
				throw new ModelNotFoundException("Error, el numero de pagina excede el limite de paginas encontradas");
			}
		}else {
			throw new InvalidFormatExcepcion("Error, con los datos de pagina y cantidad");
		}
	}

	@Override
	public Page<Consulta> findAllByOrdenByIdDesc(Integer nPage, Integer cantidad) throws InvalidFormatExcepcion, ModelNotFoundException {
		if(nPage > -1 && cantidad>0) {
			Page<Consulta> page = this.repo.findAll(PageRequest.of(nPage, cantidad, Sort.by("id").descending()));
			if(nPage < page.getTotalPages()) {
				return page;
			}else {
				throw new ModelNotFoundException("Error, el numero de pagina excede el limite de paginas encontradas");
			}			
		}else {
			throw new InvalidFormatExcepcion("Error, con los datos de pagina y cantidad");
		}
	}

	@Override
	public Consulta retornarPorId(Integer id) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Consulta> retornarPaginado(Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Consulta> retornarPaginadoOrdenado(Integer page, Integer size, String ordenar) {
		// TODO Auto-generated method stub
		return null;
	}
	

}