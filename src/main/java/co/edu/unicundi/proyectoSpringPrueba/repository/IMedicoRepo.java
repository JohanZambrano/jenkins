	package co.edu.unicundi.proyectoSpringPrueba.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.Direccion;
import co.edu.unicundi.proyectoSpringPrueba.entity.Medico;

@Repository
	public interface IMedicoRepo extends JpaRepository<Medico, Integer>{
		
		@Query(value = "select count(*) from medico where id = :id", nativeQuery  = true)
		Object cantidadObtenida(@Param("id") Integer id);

		public Page<Medico> findByNombre(Pageable page, String nombre);
		
		public Page<Medico> findByApellido(Pageable page, String apellido);
		
		public Page<Medico> findByCorreo(Pageable page, String correo);
		
		public Page<Medico> findByDireccion(Pageable page, Direccion direccion);
				
	}
