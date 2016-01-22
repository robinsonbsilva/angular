package br.com.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Usuario;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Usuario> usuarios;

	static {
		usuarios = populateDummyUsers();
	}

	public Usuario findById(long id) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				return usuario;
			}
		}
		return null;
	}

	public Usuario findByName(String name) {
		for (Usuario usuario : usuarios) {
			if (usuario.getNome().equalsIgnoreCase(name)) {
				return usuario;
			}
		}
		return null;
	}

	public List<Usuario> listAll() {
		return usuarios;
	}

	public void save(Usuario usuario) {
		usuario.setId(counter.incrementAndGet());
		usuarios.add(usuario);
	}

	public void update(Usuario usuario) {
		int index = usuarios.indexOf(usuario);
		usuarios.set(index, usuario);
	}

	public void delete(long id) {
		for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
			Usuario usuario = iterator.next();
			if (usuario.getId() == id) {
				iterator.remove();
			}
		}
	}

	public void deleteAll() {
		usuarios.clear();
	}

	public boolean isExists(Usuario usuario) {
		return findByName(usuario.getNome()) != null;
	}


	private static List<Usuario> populateDummyUsers() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario(counter.incrementAndGet(), "Robinson Silva", "Avenida Ibiuna, x", "robinsonbsilva@gmail.com"));
		usuarios.add(new Usuario(counter.incrementAndGet(), "Ellen Silva", "Avenida Ibiuna, y", "ellen@hotmail.com"));
		usuarios.add(new Usuario(counter.incrementAndGet(), "Jessie Silva", "Avenida Ibiuna, z", "jessie@outlook.com"));

		return usuarios;
	}

}
