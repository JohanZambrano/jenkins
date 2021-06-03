package co.edu.unicundi.proyectoSpringPrueba.repository;

import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;

@Repository
public interface IProfesorRepo extends JpaRepository<Profesor, Integer>{

	// SQL
	@Query(value="Select p FROM Profesor p WHERE p.cedula = :cedula")
	public Profesor buscarSQL(String cedula);
	
	//Nombramiento
	public Profesor findByCedula(String cedula);
	
	// JPQL
	@Query(value = "SELECT l FROM Profesor l WHere l.cedula = :cedula")
	Collection<Profesor> buscarJPQL(String cedula);

}
