package cl.bootcamp.ejeciocio_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bootcamp.ejeciocio_rest.model.LoginRequest;
import cl.bootcamp.ejeciocio_rest.model.TokenResponse;
import cl.bootcamp.ejeciocio_rest.service.AutenticacionService;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {
	
	private AutenticacionService auth;
	
	

	
	public UsuarioRestController(AutenticacionService auth) {
		this.auth = auth;
	}




	@PostMapping("/login")
	public ResponseEntity<TokenResponse>  login(@RequestBody LoginRequest login) {
		TokenResponse token= auth.login(login);
		
		return ResponseEntity.ok(token);
	}

	
	
}
