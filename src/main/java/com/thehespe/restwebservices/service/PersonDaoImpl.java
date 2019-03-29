package com.thehespe.restwebservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.thehespe.restwebservices.exceptions.PersonNotFoundException;
import com.thehespe.restwebservices.model.Person;

@Component
public class PersonDaoImpl implements PersonDao {
	private static List<Person> persons = new ArrayList<>();
	private static Integer personsCount = 4;
	
	static {
		persons.add(new Person(1, "Athena Ross", new Date()));
		persons.add(new Person(2, "Adam Clarke", new Date()));
		persons.add(new Person(3, "Ellen Solis", new Date()));
		persons.add(new Person(4, "Andy Mack", new Date()));
	}

	@Override	
	public List<Person> getAll() {
		return persons;
	}

	@Override
	public Person save(Person person) {
		person.setId(++personsCount);
		persons.add(person);
		
		return person;
	}

	@Override
	public Person getPerson(Integer id) {
		for (Person person : persons) {
			if (person.getId().equals(id)) {
				return person;
			}
		}
		
		throw new PersonNotFoundException("User with ID: " + id + " doesn't exist");
	}

	@Override
	public Person delete(Integer id) {
		Iterator<Person> it = persons.iterator();
		
		while (it.hasNext()) {
			Person person = it.next();
			if (person.getId().equals(id)) {
				it.remove();
				return person;
			}
		}
		
		throw new PersonNotFoundException("User with ID: " + id + " doesn't exist");
		
	}
	
}
