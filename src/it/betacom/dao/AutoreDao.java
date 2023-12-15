package it.betacom.dao;

import java.util.List;

import it.betacom.model.Autore;

public interface AutoreDao {
	
	public List<Autore> getAll();
	
	public Autore getById(int codiceA);
	
	public void insert(Autore autore);
	
	public void update(Autore autore);
	
	public void deleteById(int codiceA);
	
}
