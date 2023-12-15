package it.betacom.dao;

import java.util.List;

import it.betacom.model.Genere;

public interface GenereDao {
	
	public List<Genere> getAll();

	public Genere getById(int codiceG);
	
	public void insert(Genere genere);
	
	public void deleteById(int codiceG);
	
	public void update(Genere genere);
	
}
