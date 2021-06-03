package co.edu.unicundi.proyectoSpringPrueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.view.ConsultaExamenView;


@Repository
public interface IViewConExamRepo extends JpaRepository<ConsultaExamenView, Integer>{

}
