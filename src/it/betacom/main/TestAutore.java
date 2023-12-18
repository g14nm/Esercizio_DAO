package it.betacom.main;

import java.util.List;

import it.betacom.dao.AutoreDao;
import it.betacom.dao.impl.AutoreDaoImpl;
import it.betacom.model.Autore;

public class TestAutore {

	public static void main(String[] args) {
		AutoreDao autoreDao = new AutoreDaoImpl();
		
		//autore di prova
		Autore mdc = new Autore("Miguel", "de Cervantes", 1547, 1616, 'M', "Spagnaa");
		
		//stampiamo la lista degli autori presenti su Db
		System.out.println("---------------------------");
		stampaAutori(autoreDao.getAll());
		
		//stampiamo i dati dell' autore richiesto
		System.out.println("---------------------------");
		Autore autore = autoreDao.getById(1);
		if(autore != null) System.out.println("Autore richiesto -> " + autore.toString());
		else System.out.println("Non è presente nessun autore con il codiceA indicato!");
		
		//stampiamo l'esito dell'inserimento e stampiamo la lista
		System.out.println("---------------------------");
		autoreDao.insert(mdc);
		stampaAutori(autoreDao.getAll());
		
		//stampiamo l'esito dell'aggiornamento (es: cambiare nazione) stampiamo la lista
		System.out.println("---------------------------");
		mdc.setNazione("Spagna");
		autoreDao.update(mdc);
		stampaAutori(autoreDao.getAll());
		
		//stampiamo l'esito del delete e stampare la lista
		System.out.println("---------------------------");
		autoreDao.deleteById(mdc.getCodiceA());
		stampaAutori(autoreDao.getAll());
	}
	
	private static void stampaAutori(List<Autore> autori) {
		if(autori == null || autori.isEmpty())
			System.out.println("Lista autori vuota!");
		else {
			System.out.println("Lista autori");
			autori.forEach(System.out::println);
		}
	}
	
}
