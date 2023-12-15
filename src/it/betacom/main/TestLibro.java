package it.betacom.main;

import java.util.List;

import it.betacom.dao.AutoreDao;
import it.betacom.dao.EditoreDao;
import it.betacom.dao.GenereDao;
import it.betacom.dao.LibroDao;
import it.betacom.dao.impl.AutoreDaoImpl;
import it.betacom.dao.impl.EditoreDaoImpl;
import it.betacom.dao.impl.GenereDaoImpl;
import it.betacom.dao.impl.LibroDaoImpl;
import it.betacom.model.Autore;
import it.betacom.model.Editore;
import it.betacom.model.Genere;
import it.betacom.model.Libro;
import it.betacom.service.PrintService;
import it.betacom.service.impl.LibroPrintService;

public class TestLibro {

	public static void main(String[] args) {
		LibroDao libroDao = new LibroDaoImpl();
		PrintService<Libro> libroPrintService = new LibroPrintService();
		
//		Libro carrie = new Libro("Carrie", 224, 1974, 4, 2, 1);
//		
//		//stampiamo la lista dei libri presenti su Db
//		System.out.println("---------------------------");
//		stampaLibri(libroDao.getAll());
//		
//		//stampiamo i dati del libro richiesto
//		System.out.println("---------------------------");
//		int codiceL = 1;
//		Libro libro = libroDao.getById(codiceL);
//		if(libro != null) {
//			System.out.println("Libro richiesto -> " + libro.toString());
//		}
//		else
//			System.out.println("Non è presente nessun libro con il codiceL indicato!");
//		
//		//stampiamo l'esito dell'inserimento e stampiamo la lista libri
//		System.out.println("---------------------------");
//		libroDao.insert(carrie);
//		stampaLibri(libroDao.getAll());
//		
//		//stampiamo l'esito dell'aggiornamento (es: cambiare editore) stampiamo la lista libri
//		System.out.println("---------------------------");
//		Editore editore2 = editoreDao.getById(2);
//		carrie.setEditore(editore2);
//		libroDao.update(carrie);
//		stampaLibri(libroDao.getAll());
//		
//		//stampiamo l'esito del delete libro e stampare la lista libri
//		System.out.println("---------------------------");
//		libroDao.deleteById(carrie.getCodiceL());
//		stampaLibri(libroDao.getAll());
		
		//salva lista libri su file txt
		System.out.println("---------------------------");
		libroPrintService.saveListAsTxt();

		//salva singolo libro su file txt
		System.out.println("---------------------------");
		Libro libroDaSalvareSuFileTxt = libroDao.getById(1);
		if(libroDaSalvareSuFileTxt != null) libroPrintService.saveAsTxt(libroDaSalvareSuFileTxt);
		else System.out.println("Non è presente nessun libro con l'id specificato");

		//salva lista libri su file pdf
		System.out.println("---------------------------");
		libroPrintService.saveListAsPdf();

		//salva singolo libro su file pdf
		System.out.println("---------------------------");
		Libro libroDaSalvareSuFilePdf = libroDao.getById(1);
		if(libroDaSalvareSuFilePdf != null) libroPrintService.saveAsPdf(libroDaSalvareSuFilePdf);
		else System.out.println("Non è presente nessun genere con l'id specificato");
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
