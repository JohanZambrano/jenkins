package co.edu.unicundi.proyectoSpringPrueba.view;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "vista_consulta") // <--- nombre de la vista en la base de datos
@Immutable
public class ConsultaExamenView {
	
	@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", updatable = false, nullable = false)
	private int id;
	
   @Column(name = "fecha", nullable = false)
   private LocalDateTime fecha;

   @Column(name = "id_medico", nullable = false)
   private int id_medico;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public LocalDateTime getFecha() {
	return fecha;
}

public void setFecha(LocalDateTime fecha) {
	this.fecha = fecha;
}

public int getId_medico() {
	return id_medico;
}

public void setId_medico(int id_medico) {
	this.id_medico = id_medico;
}



   
   
}
