package cl.bootcamp.ejeciocio_rest.service;

import java.util.List;

import cl.bootcamp.ejeciocio_rest.model.Usuario;


public interface UsuarioService {

	Usuario getByUsername(String username);
	int crear(Usuario usuario);
	int editar(Usuario usuario);
	List<Usuario> listado();
	Usuario getById(int id);
}
