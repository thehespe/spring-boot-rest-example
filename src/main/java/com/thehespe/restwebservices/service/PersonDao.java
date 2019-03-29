package com.thehespe.restwebservices.service;

import java.util.List;

import com.thehespe.restwebservices.model.Person;

public interface PersonDao {

	public List<Person> getAll();
	
	public Person save(Person person);
	
	public Person getPerson(Integer id);
	
	public Person delete(Integer id);
}
