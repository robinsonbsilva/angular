package br.com.service;

import java.util.List;

import br.com.model.Usuario;

public interface UserService {

	Usuario findById(long id);

	Usuario findByName(String name);

	List<Usuario> listAll();

	void save(Usuario usuario);

	void update(Usuario usuario);

	void delete(long id);

	void deleteAll();

	boolean isExists(Usuario usuario);

}
