package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.dto.ListarPorIdConsultaDto;
import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpringPrueba.entity.Examen;
import co.edu.unicundi.proyectoSpringPrueba.exception.InvalidFormatExcepcion;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaExamenRepo;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaRepo;
import co.edu.unicundi.proyectoSpringPrueba.repository.IExamenRepo;
import co.edu.unicundi.proyectoSpringPrueba.repository.IViewConExamRepo;
import co.edu.unicundi.proyectoSpringPrueba.service.IConsultaExamenService;
import co.edu.unicundi.proyectoSpringPrueba.view.ConsultaExamenView;

@Service
public class ConsultaExamenServiceImpl implements IConsultaExamenService {
	
	@Autowired
	private IConsultaExamenRepo repo;

	@Autowired
	private IConsultaRepo repoConsulta;
	
	@Autowired
	private IExamenRepo repoExamen;
	
	@Autowired
	private IViewConExamRepo repoViewConsExamen;	
	
	@Override
	public ConsultaExamen retornarPorId(Integer id) throws ModelNotFoundException {
		return null;
	}

	@Override
	public Page<ConsultaExamen> retornarPaginado(Integer nPage, Integer cantidad) throws ModelNotFoundException, InvalidFormatExcepcion{
		if(nPage > -1 && cantidad>0) {			
			Page<ConsultaExamen> page = this.repo.findAll(PageRequest.of(nPage, cantidad));
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
	public Page<ConsultaExamen> retornarPaginadoOrdenado(Integer nPage, Integer cantidad, String ordenar) throws ModelNotFoundException, InvalidFormatExcepcion {
		if(nPage > -1 && cantidad>0) {
			if(ordenar.equals("asc")) {
				Page<ConsultaExamen> page = this.repo.findAll(PageRequest.of(nPage, cantidad, Sort.by("id").ascending()));
				if(nPage < page.getTotalPages()) {
					return page;
				}else {
					throw new ModelNotFoundException("Error, el numero de pagina excede el limite de paginas encontradas");
				}
			}else {
				if(ordenar.equals("des")) {
					Page<ConsultaExamen> page = this.repo.findAll(PageRequest.of(nPage, cantidad, Sort.by("id").descending()));
					if(nPage < page.getTotalPages()) {
						return page;
					}else {
						throw new ModelNotFoundException("Error, el numero de pagina excede el limite de paginas encontradas");
					}
				}else {
					throw new InvalidFormatExcepcion("Error, el tipo de ordenar no es valido");
				}
			}
			
		}else {
			throw new InvalidFormatExcepcion("Error, con los datos de pagina y cantidad");
		}				
	}

	@Override
	public void guardar(ConsultaExamen entity) throws ModelNotFoundException, InvalidFormatExcepcion {
		System.out.println("id consulta " + entity.getConsulta().getId());
		if(entity.getConsulta().getId() > 0) {
			Optional<Consulta> datos = repoConsulta.findById(entity.getConsulta().getId());
			if(datos != null && datos.isPresent()) {			
				if(entity.getExamen().getId()>0) {
					Optional<Examen> dato = repoExamen.findById(entity.getExamen().getId());
					if(dato != null && dato.isPresent()) {
						ConsultaExamen guardarDato = new ConsultaExamen();
						Consulta consulta = new Consulta();
						consulta.setId(entity.getConsulta().getId());
						guardarDato.setConsulta(consulta);
						Examen examen = new Examen();
						examen.setId(entity.getExamen().getId());			
						guardarDato.setExamen(examen);
						guardarDato.setInfoAdicional(entity.getInfoAdicional());
						guardarNativo(guardarDato);
					}else {
						throw new ModelNotFoundException("Error, el examen no existe");
					}
				}else {
					throw new InvalidFormatExcepcion("Error, formato de examen no valido");
				}			
			}else {
				throw new ModelNotFoundException("Error, la consulta no existe");
			}
		}else {
			throw new InvalidFormatExcepcion("Error, formato de consulta no valido");
		}
		
	}

	@Override
	public void editar(ConsultaExamen entity) throws ModelNotFoundException, InvalidFormatExcepcion {		
		System.out.println("entro");
		if(entity.getConsulta().getId() > 0) {
			Optional<Consulta> datos = repoConsulta.findById(entity.getConsulta().getId());
			if(datos != null && datos.isPresent()) {			
				if(entity.getExamen().getId()>0) {
					Optional<Examen> dato = repoExamen.findById(entity.getExamen().getId());
					if(dato != null && dato.isPresent()) {		
						ConsultaExamen guardarDato = new ConsultaExamen();
						
						Consulta consulta = new Consulta();
						consulta.setId(entity.getConsulta().getId());
						guardarDato.setConsulta(consulta);
						
						Examen examen = new Examen();
						examen.setId(entity.getExamen().getId());			
						guardarDato.setExamen(examen);
						guardarDato.setInfoAdicional(entity.getInfoAdicional());
						System.out.println("---------- CONSULTA " + guardarDato.getConsulta().getId());
						System.out.println("---------- examen " + guardarDato.getExamen().getId());
												
						repo.editar(guardarDato.getConsulta().getId(), guardarDato.getExamen().getId(), guardarDato.getInfoAdicional());
						
					}else {
						throw new ModelNotFoundException("Error, el examen no existe");
					}
				}else {
					throw new InvalidFormatExcepcion("Error, formato de examen no valido");
				}			
			}else {
				throw new ModelNotFoundException("Error, la consulta no existe");
			}
		}else {
			throw new InvalidFormatExcepcion("Error, formato de consulta no valido");
		}
		repo.save(entity);		
	}

	@Override
	public void eliminarConsultaExamen(Integer consulta, Integer examen) throws ModelNotFoundException, InvalidFormatExcepcion {		
		if(consulta > 0) {
			Optional<Consulta> datos = repoConsulta.findById(consulta);
			if(datos != null && datos.isPresent()) {			
				if(examen>0) {
					Optional<Examen> dato = repoExamen.findById(examen);
					if(dato != null && dato.isPresent()) {
						repo.eliminar(consulta, examen);
					}else {
						throw new ModelNotFoundException("Error, el examen no existe");
					}
				}else {
					throw new InvalidFormatExcepcion("Error, formato de examen no valido");
				}			
			}else {
				throw new ModelNotFoundException("Error, la consulta no existe");
			}
		}else {
			throw new InvalidFormatExcepcion("Error, formato de consulta no valido");
		}
		
		
	}

	@Override
	public void guardarNativo(ConsultaExamen consultaExamen) {
		repo.guardar(consultaExamen.getConsulta().getId(), consultaExamen.getExamen().getId(), consultaExamen.getInfoAdicional());
	}

	@Override
	public List<ListarPorIdConsultaDto> listarPorIdCosnulta(Integer idConsulta) {
		List<ConsultaExamen> lista = repo.listarPorIdCosnulta(idConsulta);
		List<ListarPorIdConsultaDto> busqueda = new ArrayList<>();
		for(int i = 0; i<lista.size(); i++) {
			boolean validacion = true;
			for(int x = 0; x<busqueda.size(); x++) {
				if(busqueda.get(x).getConsulta() == lista.get(i).getConsulta()) {
					validacion = false;
				}
			}
			if(validacion == true) {
				ListarPorIdConsultaDto examenBusqueda = new ListarPorIdConsultaDto();
				List<Examen> examenLista = new ArrayList<>();
				for(int y = 0; y<lista.size(); y++) {				
					if(lista.get(i).getConsulta().getId() == lista.get(y).getConsulta().getId()) {
						Examen examen = new Examen();
						examen.setId(lista.get(y).getExamen().getId());					
						examen.setDescripcion(lista.get(y).getExamen().getDescripcion());
						examen.setNombre(lista.get(y).getExamen().getNombre());
						examen.setPreparacion(lista.get(y).getExamen().getPreparacion());
						examenLista.add(examen);
					}
				}
				examenBusqueda.setExamen(examenLista);
				examenBusqueda.setConsulta(lista.get(i).getConsulta());
				examenBusqueda.setInfoAdicional(lista.get(i).getInfoAdicional());
				busqueda.add(examenBusqueda);
			}
		}
		return busqueda;
	}

	@Override
	public Page<ConsultaExamen> listarPorIdCosnultaPaginado(Integer id, Integer page, Integer size) {
		return repo.findByConsulta_Id(id, PageRequest.of(page, size));
	}

	@Override
	public ConsultaExamen filtrarPorId(Integer idConsulta, Integer idExamen) throws ModelNotFoundException{
		
			Optional<Consulta> consulta = repoConsulta.findById(idConsulta);
			if(consulta != null && consulta.isPresent()) {
				Optional<Examen> dato = repoExamen.findById(idExamen);
				if(dato != null && dato.isPresent()) {
					ConsultaExamen busqueda = repo.buscarPorIdConExa(idConsulta,idExamen);
					if(busqueda != null) {
						return repo.buscarPorIdConExa(idConsulta,idExamen);
					}else {
						throw new ModelNotFoundException("No se encontro");
					}					
				}else {
					throw new ModelNotFoundException("Error, el id ingresado no existe");
				}				
			}else {
				throw new ModelNotFoundException("Error, el id ingresado no existe");
			}							
	}

	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<ConsultaExamenView> vista(Integer nPage, Integer cantidad) {	
		Page<ConsultaExamenView> busqueda = repoViewConsExamen.findAll(PageRequest.of(nPage, cantidad)); 
		for(int i = 0; i<busqueda.getTotalElements(); i++) {
			
		}
		return busqueda; 
	}

}