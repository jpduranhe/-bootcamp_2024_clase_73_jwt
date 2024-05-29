package cl.bootcamp.ejeciocio_rest.entity;


import cl.bootcamp.ejeciocio_rest.model.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="usuario")
@Data
public class UsuarioEntity  {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private int id;
	
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="email")
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name="rol")
	private Rol rol;
	
}

