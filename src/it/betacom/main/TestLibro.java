package it.betacom.main;

import java.util.List;

import it.betacom.dao.LibroDao;
import it.betacom.dao.impl.LibroDaoImpl;
import it.betacom.model.Libro;

public class TestLibro {

	public static void main(String[] args) {
		LibroDao libroDao = new LibroDaoImpl();
		
		//libro di prova
		Libro carrie = new Libro("Carrie", 224, 1974, 4, 2, 1);
		
		//stampiamo la lista dei libri presenti su Db
		System.out.println("---------------------------");
		stampaLibri(libroDao.getAll());
		
		//stampiamo i dati del libro richiesto
		System.out.println("---------------------------");
		Libro libro = libroDao.getByIdWithDetails(1);
		if(libro != null) System.out.println("Libro richiesto -> " + libro.toString());
		else System.out.println("Non è presente nessun libro con il codiceL indicato!");
		
		//stampiamo l'esito dell'inserimento e stampiamo la lista libri
		System.out.println("---------------------------");
		libroDao.insert(carrie);
		stampaLibri(libroDao.getAll());
		
		//stampiamo l'esito dell'aggiornamento (es: cambiare editore) stampiamo la lista libri
		System.out.println("---------------------------");
		carrie.setCodiceE(2);
		libroDao.update(carrie);
		stampaLibri(libroDao.getAll());
		
		//stampiamo l'esito del delete libro e stampare la lista libri
		System.out.println("---------------------------");
		libroDao.deleteById(carrie.getCodiceL());
		stampaLibri(libroDao.getAll());
	}
	
	private static void stampaLibri(List<Libro> libri) {
		if(libri == null || libri.isEmpty())
			System.out.println("Lista libri vuota!");
		else {
			System.out.println("Lista libri");
			libri.forEach(System.out::println);
		}
	}
	
}
