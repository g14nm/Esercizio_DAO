package it.betacom.dao;

import java.util.List;

import it.betacom.model.Libro;

public interface LibroDao {

	public List<Libro> getAll();

	public Libro getById(int codiceL);
	
	public void insert(Libro libro);
	
	public void deleteById(int codiceL);
	
	public void update(Libro libro);
	
}
