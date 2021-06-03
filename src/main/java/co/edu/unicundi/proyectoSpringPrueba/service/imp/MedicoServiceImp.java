package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.entity.Medico;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IMedicoRepo;
import co.edu.unicundi.proyectoSpringPrueba.service.IMedicoService;

@Service
public class MedicoServiceImp implements IMedicoService {

	@Autowired
	private IMedicoRepo repo;
	
	@Override
	public Medico retornarPorId(Integer id) throws ModelNotFoundException {
		Medico medico = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Medico no encontrado"));
		return medico;
	}

	@Override
	public Page<Medico> retornarPaginado(Integer page, Integer size) {
		return repo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Medico> retornarPaginadoOrdenado(Integer page, Integer size, String ordenar) {
		return repo.findAll(PageRequest.of(page, size, Sort.by(ordenar).ascending()));
	}

	@Override
	public void guardar(Medico medico) {
		medico.getDireccion().setMedico(medico);
		repo.save(medico);
	}

	@Override
	public void editar(Medico medico) throws ModelNotFoundException {
		Medico med = retornarPorId(medico.getId());
		med.setNombre(medico.getNombre());
		med.setApellido(medico.getApellido());
		med.setCorreo(medico.getCorreo());
		med.getDireccion().setBarrio(medico.getDireccion().getBarrio());
		med.getDireccion().setCiudad(medico.getDireccion().getCiudad());
		med.getDireccion().setPais(medico.getDireccion().getPais());
		med.getDireccion().setDetalle(medico.getDireccion().getDetalle());
		
		repo.save(med);
	}

	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		if(cantidadObtenida(id) > 0)
			repo.deleteById(id);
		else
			 throw new ModelNotFoundException("Medico no encontrado");	
	}
	
	private Integer cantidadObtenida(Integer id) {
		BigInteger cantidad = (BigInteger) this.repo.cantidadObtenida(id);
		return cantidad.intValue();
	}	

}
