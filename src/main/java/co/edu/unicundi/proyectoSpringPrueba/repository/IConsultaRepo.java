package co.edu.unicundi.proyectoSpringPrueba.repository;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;


@Repository
public interface IConsultaRepo extends JpaRepository<Consulta, Integer>{

	public Page<Consulta> findByDetalleConsulta(Pageable page, DetalleConsulta detalleConsulta);
	
	public Page<Consulta> findAllByOrderByIdAsc(Pageable page);
	
	public Page<Consulta> findAllByOrderByIdDesc(Pageable page);
	
	public Optional<Consulta> findById(Integer id);

	//public Page<Consulta> findByNombreDoctorAndId(Pageable page, String nombre, Integer id);

	//public Page<Consulta> findByNombreDoctorOrId(Pageable page, String nombre, Integer id);
	
}
