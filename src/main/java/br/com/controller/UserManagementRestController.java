package br.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.model.Usuario;
import br.com.service.UserService;

@RestController
public class UserManagementRestController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/usuario/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {

		if (userService.isExists(usuario)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.save(usuario);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> update(@PathVariable("id") long id, @RequestBody Usuario usuario) {

		Usuario currentUser = userService.findById(id);

		if (currentUser == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		currentUser.setNome(usuario.getNome());
		currentUser.setEndereco(usuario.getEndereco());
		currentUser.setEmail(usuario.getEmail());

		userService.update(currentUser);

		return new ResponseEntity<Usuario>(currentUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> delete(@PathVariable("id") long id) {

		Usuario usuario = userService.findById(id);
		if (usuario == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		userService.delete(id);

		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/usuario/", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listAll() {
		List<Usuario> users = userService.listAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> findById(@PathVariable("id") long id) {
		Usuario usuario = userService.findById(id);
		if (usuario == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> deleteAll() {
		System.out.println("Deleting All Users");

		userService.deleteAll();

		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

}