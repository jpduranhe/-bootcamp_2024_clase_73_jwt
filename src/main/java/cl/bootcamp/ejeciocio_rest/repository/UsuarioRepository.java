package cl.bootcamp.ejeciocio_rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.bootcamp.ejeciocio_rest.entity.UsuarioEntity;



@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity,Integer>{

	UsuarioEntity searchByUsername(String email);
}
