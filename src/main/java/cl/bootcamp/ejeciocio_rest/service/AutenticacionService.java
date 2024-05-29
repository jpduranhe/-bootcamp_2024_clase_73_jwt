package cl.bootcamp.ejeciocio_rest.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import cl.bootcamp.ejeciocio_rest.model.LoginRequest;
import cl.bootcamp.ejeciocio_rest.model.TokenResponse;

@Service
public class AutenticacionService {

	private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailService;
	
	
	public AutenticacionService(JwtService jwtService, AuthenticationManager authenticationManager,
			UserDetailsService userDetailService) {
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.userDetailService=userDetailService;
	}


	public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        
        
        UserDetails user=userDetailService.loadUserByUsername(request.getUsername());
        String token=jwtService.getToken(user);
        return new TokenResponse(token);

    }
}
