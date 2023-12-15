package it.betacom.dao;

import java.util.List;

import it.betacom.model.Editore;

public interface EditoreDao {

	public List<Editore> getAll();
	
	public Editore getById(int codiceE);
	
	public void insert(Editore editore);
	
	public void deleteById(int codiceE);
	
	public void update(Editore editore);
	
}
