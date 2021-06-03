package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IProfesorRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import co.edu.unicundi.proyectoSpringPrueba.service.IProfesorService;

import org.springframework.data.domain.Sort;

@Service
public class ProfesorServiceImp implements IProfesorService{

	@Autowired
	private IProfesorRepo profesorRepo;
	
	List<Profesor> datos = new ArrayList<Profesor>();
	
	@Override
	public Profesor buscarId(int id) throws ModelNotFoundException  {
		
		Profesor profesor = profesorRepo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Profesor no exontrado"));
		return profesor;	
		
		//Metodo para utilizar con lista
		/*if(datos != null) {
			for(int i = 0; i<datos.size(); i++) {
				if(datos.get(i).getId() == id) {
					return datos.get(i);
				}
			}
		}
		return null;*/
	}

	@Override
	public List<Profesor> mostrarTodos() {
		return this.profesorRepo.findAll();
	}

	@Override
	public void guardarProfesor(Profesor profesor) throws ModelNotFoundException  {
		
		this.profesorRepo.save(profesor);
		
		/*
		boolean validacion = true;
		if(datos != null) {
			for(int i = 0; i<datos.size(); i++) {
				if(datos.get(i).getId() == profesor.getId()) {
					validacion = false;
				}
			}
			if(validacion == true) {
				datos.add(profesor);	
				return "Se creo correctamente";
			}else {
				return "No se creo correctamente";
			}
		}else {
			datos.add(profesor);
			return "Se creo correctamente";
		}*/
	}

	@Override
	public void editarProfesor(Profesor profesor) throws ModelNotFoundException {
		Profesor pro = this.buscarId(profesor.getId());
		pro.setApellido(profesor.getApellido());
		pro.setNombre(profesor.getNombre());
		pro.setCorreo(profesor.getCorreo());
		pro.setEdad(profesor.getEdad());
		//pro.setCedula(profesor.getCedula());		
		this.profesorRepo.save(pro);	
		// Metodo para utilizar lista
		/*if(datos != null) {
			for(int i = 0; i<datos.size(); i++) {
				if(datos.get(i).getId() == profesor.getId()) {
					datos.set(i, profesor);
					return "Se actualizo correctamente";
				}
			}
		}
		return null;*/
	}

	@Override
	public void eliminarProfesor(int id) throws ModelNotFoundException {
		this.profesorRepo.delete(this.buscarId(id));
		// Metodo para utilizar lista
		/*if(datos != null) {
			for(int i = 0; i<datos.size(); i++) {
				if(datos.get(i).getId() == id) {
					datos.remove(i);
					return "Se elimino correctamente";
				}
			}
		}
		return null;*/
	}

	@Override
	public Profesor buscarNombramiento(String cedula) {
		return this.profesorRepo.findByCedula(cedula);
	}

	@Override
	public Profesor buscarSQL(String cedula) {
		return this.profesorRepo.buscarSQL(cedula);
	}

	@Override
	public List<Profesor> buscarJPQL(String cedula) {
		return this.buscarJPQL(cedula);
	}

	@Override
	public ArrayList<Profesor> sort1() {
		return (ArrayList<Profesor>) this.profesorRepo.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
	}

	@Override
	public Page<Profesor> listarPaginado(int page, int size) {
		return this.profesorRepo.findAll(PageRequest.of(page, size, Sort.by("nombre").ascending()));
	}
	
	

	
}
