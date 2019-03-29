package com.thehespe.restwebservices.service;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thehespe.restwebservices.model.Person;

@RestController
public class PersonRest {

	@Autowired
	private PersonDao personDao;

	@GetMapping(path = "/users")
	public List<Person> getAll() {
		return personDao.getAll();
	}

	@GetMapping(path = "/users/{id}")
	public Person getPerson(@PathVariable Integer id) {
		Person person = personDao.getPerson(id);
		return person;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> save(@Valid @RequestBody Person person) {
		Person savedPerson = personDao.save(person);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedPerson.getId())
		.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public Person deleteUser(@PathVariable Integer id) {
		return personDao.delete(id);
	}
}
