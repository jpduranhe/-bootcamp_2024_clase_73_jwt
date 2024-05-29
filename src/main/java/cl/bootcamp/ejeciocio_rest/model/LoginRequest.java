package cl.bootcamp.ejeciocio_rest.model;

import lombok.Data;

@Data
public class LoginRequest {
	private String username;
	private String password;

}
