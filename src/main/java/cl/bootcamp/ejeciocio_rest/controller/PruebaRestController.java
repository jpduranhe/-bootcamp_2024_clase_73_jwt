package cl.bootcamp.ejeciocio_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class PruebaRestController {
	
	@GetMapping
	public ResponseEntity<String>  login() {
		
		return ResponseEntity.ok("Prueba");
	}
}
