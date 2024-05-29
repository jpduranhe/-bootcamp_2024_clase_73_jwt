package cl.bootcamp.ejeciocio_rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.bootcamp.ejeciocio_rest.entity.UsuarioEntity;
import cl.bootcamp.ejeciocio_rest.model.Usuario;
import cl.bootcamp.ejeciocio_rest.repository.UsuarioRepository;
import cl.bootcamp.ejeciocio_rest.service.UsuarioService;
import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService  {

	private final UsuarioRepository usuarioRepository;
	
	
	
	
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	@Transactional
	public int crear(Usuario usuario) {
		try {
			
			
			UsuarioEntity usuarioEntity= new UsuarioEntity();
			String hashPass=new BCryptPasswordEncoder().encode(usuario.getPassword());
			
			usuarioEntity.setNombre(usuario.getNombre());
			usuarioEntity.setApellido(usuario.getApellido());
			usuarioEntity.setEmail(usuario.getEmail());
			usuarioEntity.setRol(usuario.getRol());
			usuarioEntity.setPassword(hashPass);
			usuarioEntity.setUsername(usuario.getUsername());
			
			UsuarioEntity usuarioGuardado=usuarioRepository.save(usuarioEntity);
			
			return usuarioGuardado.getId();
			
		}catch(Exception ex) {
			log.error(ex.getMessage());
			throw ex; 
		}
	}

	@Override
	public int editar(Usuario usuario) {
		try {
			
			Optional<UsuarioEntity> usuarioEntityGuardadoOptional = usuarioRepository.findById(usuario.getId());
		
			if(usuarioEntityGuardadoOptional.isEmpty()) return -1;
			
			UsuarioEntity usuarioEntityGuardado =usuarioEntityGuardadoOptional.get();
		
			usuarioEntityGuardado.setNombre(usuario.getNombre());
			usuarioEntityGuardado.setApellido(usuario.getApellido());
			usuarioEntityGuardado.setEmail(usuario.getEmail());
			usuarioEntityGuardado.setRol(usuario.getRol());
			
			UsuarioEntity usuarioGuardado=usuarioRepository.save(usuarioEntityGuardado);
			
			return usuarioGuardado.getId();
			
		}catch(Exception ex) {
			log.error(ex.getMessage());
			throw ex; 
		}
	}
	
	@Override
	public List<Usuario> listado() {
		try {
			
			
			List<Usuario> listado= new ArrayList<>();
			Iterable<UsuarioEntity> listadoEntities=usuarioRepository.findAll();
			
			for(UsuarioEntity usuarioEntity : listadoEntities) {
				Usuario usuario= new Usuario();
				usuario.setId(usuarioEntity.getId());
				usuario.setNombre(usuarioEntity.getNombre());
				usuario.setApellido(usuarioEntity.getApellido());
				usuario.setEmail(usuarioEntity.getEmail());
				usuario.setUsername(usuarioEntity.getUsername());
				usuario.setRol(usuarioEntity.getRol());
				listado.add(usuario);
			}
			
			return listado;
			
		}catch(Exception ex) {
			log.error(ex.getMessage());
			throw ex; 
		}
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioEntity usuario= usuarioRepository.searchByUsername(username);
		
		if(usuario != null) {
			List<GrantedAuthority> permissions= new ArrayList<>();
			
			GrantedAuthority ga= new SimpleGrantedAuthority("ROLE_"+usuario.getRol().name());
			permissions.add(ga);
			
			
			return new User(usuario.getUsername(),usuario.getPassword(),permissions);
		}
		
		return null;
	}
	


	@Override
	public Usuario getById(int id) {
		UsuarioEntity usuarioEntity=  usuarioRepository.findById(id).orElse(null);
		if(usuarioEntity== null) return null;
		
		Usuario usuario= new Usuario();
		usuario.setId(usuarioEntity.getId());
		usuario.setNombre(usuarioEntity.getNombre());
		usuario.setApellido(usuarioEntity.getApellido());
		usuario.setEmail(usuarioEntity.getEmail());
		usuario.setUsername(usuarioEntity.getUsername());
		usuario.setRol(usuarioEntity.getRol());
		return usuario;
	}
	@Override
	public Usuario getByUsername(String username) {
		UsuarioEntity usuarioEntity=  usuarioRepository.searchByUsername(username);
		if(usuarioEntity== null) return null;
		
		Usuario usuario= new Usuario();
		usuario.setId(usuarioEntity.getId());
		usuario.setNombre(usuarioEntity.getNombre());
		usuario.setApellido(usuarioEntity.getApellido());
		usuario.setEmail(usuarioEntity.getEmail());
		usuario.setUsername(usuarioEntity.getUsername());
		usuario.setRol(usuarioEntity.getRol());
		return usuario;
	}

}
