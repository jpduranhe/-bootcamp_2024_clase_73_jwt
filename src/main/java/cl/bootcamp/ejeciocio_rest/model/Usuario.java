package cl.bootcamp.ejeciocio_rest.model;

import lombok.Data;

@Data
public class Usuario {
	
	private int id;
	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private String email;
	private Rol rol;
}
