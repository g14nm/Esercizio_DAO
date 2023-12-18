package it.betacom.main;

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
import it.betacom.service.impl.AutorePrintService;
import it.betacom.service.impl.EditorePrintService;
import it.betacom.service.impl.GenerePrintService;
import it.betacom.service.impl.LibroPrintService;

public class TestStampa {

	public static void main(String[] args) {
		testLibri();
		testAutori();
		testGeneri();
		testEditori();
	}
	
	private static void testLibri() {
		LibroDao libroDao = new LibroDaoImpl();
		PrintService<Libro> libroPrintService = new LibroPrintService();
		
		//salva lista libri su file txt
		System.out.println("---------------------------");
		libroPrintService.saveListAsTxt();

		//salva singolo libro su file txt
		System.out.println("---------------------------");
		Libro libroDaSalvareSuFileTxt = libroDao.getByIdWithDetails(1);
		if(libroDaSalvareSuFileTxt != null) libroPrintService.saveAsTxt(libroDaSalvareSuFileTxt);
		else System.out.println("Non è presente nessun libro con l'id specificato");

		//salva lista libri su file pdf
		System.out.println("---------------------------");
		libroPrintService.saveListAsPdf();

		//salva singolo libro su file pdf
		System.out.println("---------------------------");
		Libro libroDaSalvareSuFilePdf = libroDao.getByIdWithDetails(1);
		if(libroDaSalvareSuFilePdf != null) libroPrintService.saveAsPdf(libroDaSalvareSuFilePdf);
		else System.out.println("Non è presente nessun genere con l'id specificato");

		//salva lista libri su file csv
		System.out.println("---------------------------");
		libroPrintService.saveListAsCsv();

		//salva singolo libro su file csv
		System.out.println("---------------------------");
		Libro libroDaSalvareSuFileCsv = libroDao.getByIdWithDetails(1);
		if(libroDaSalvareSuFileCsv != null) libroPrintService.saveAsCsv(libroDaSalvareSuFileCsv);
		else System.out.println("Non è presente nessun genere con l'id specificato");
	}
	
	private static void testAutori() {
		AutoreDao autoreDao = new AutoreDaoImpl();
		PrintService<Autore> autorePrintService = new AutorePrintService();
		
		//salva lista autori su file txt
		System.out.println("---------------------------");
		autorePrintService.saveListAsTxt();

		//salva singolo autore su file txt
		System.out.println("---------------------------");
		Autore autoreDaSalvareSuFileTxt = autoreDao.getById(1);
		if(autoreDaSalvareSuFileTxt != null) autorePrintService.saveAsTxt(autoreDaSalvareSuFileTxt);
		else System.out.println("Non è presente nessun autore con l'id specificato");

		//salva lista autori su file pdf
		System.out.println("---------------------------");
		autorePrintService.saveListAsPdf();

		//salva singolo autore su file pdf
		System.out.println("---------------------------");
		Autore autoreDaSalvareSuFilePdf = autoreDao.getById(1);
		if(autoreDaSalvareSuFilePdf != null) autorePrintService.saveAsPdf(autoreDaSalvareSuFilePdf);
		else System.out.println("Non è presente nessun autore con l'id specificato");
		
		//salva lista autori su file csv
		System.out.println("---------------------------");
		autorePrintService.saveListAsCsv();

		//salva singolo autore su file csv
		System.out.println("---------------------------");
		Autore autoreDaSalvareSuFileCsv = autoreDao.getById(1);
		if(autoreDaSalvareSuFileCsv != null) autorePrintService.saveAsCsv(autoreDaSalvareSuFileCsv);
		else System.out.println("Non è presente nessun autore con l'id specificato");
	}
	
	private static void testGeneri() {
		GenereDao genereDao = new GenereDaoImpl();
		PrintService<Genere> generePrintService = new GenerePrintService();

		//salva lista generi su file txt
		System.out.println("---------------------------");
		generePrintService.saveListAsTxt();

		//salva singolo genere su file txt
		System.out.println("---------------------------");
		Genere genereDaSalvareSuFileTxt = genereDao.getById(1);
		if(genereDaSalvareSuFileTxt != null) generePrintService.saveAsTxt(genereDaSalvareSuFileTxt);
		else System.out.println("Non è presente nessun genere con l'id specificato");

		//salva lista generi su file pdf
		System.out.println("---------------------------");
		generePrintService.saveListAsPdf();

		//salva singolo genere su file pdf
		System.out.println("---------------------------");
		Genere genereDaSalvareSuFilePdf = genereDao.getById(1);
		if(genereDaSalvareSuFilePdf != null) generePrintService.saveAsPdf(genereDaSalvareSuFilePdf);
		else System.out.println("Non è presente nessun genere con l'id specificato");
		
		//salva lista generi su file csv
		System.out.println("---------------------------");
		generePrintService.saveListAsCsv();

		//salva singolo genere su file csv
		System.out.println("---------------------------");
		Genere genereDaSalvareSuFileCsv = genereDao.getById(1);
		if(genereDaSalvareSuFileCsv != null) generePrintService.saveAsCsv(genereDaSalvareSuFileCsv);
		else System.out.println("Non è presente nessun genere con l'id specificato");
	}
	
	private static void testEditori() {
		EditoreDao editoreDao = new EditoreDaoImpl();
		PrintService<Editore> editorePrintService = new EditorePrintService();

		//salva lista editori su file txt
		System.out.println("---------------------------");
		editorePrintService.saveListAsTxt();

		//salva singolo editore su file txt
		System.out.println("---------------------------");
		Editore editoreDaSalvareSuFileTxt = editoreDao.getById(1);
		if(editoreDaSalvareSuFileTxt != null) editorePrintService.saveAsTxt(editoreDaSalvareSuFileTxt);
		else System.out.println("Non è presente nessun editore con l'id specificato");

		//salva lista editori su file pdf
		System.out.println("---------------------------");
		editorePrintService.saveListAsPdf();

		//salva singolo editore su file pdf
		System.out.println("---------------------------");
		Editore editoreDaSalvareSuFilePdf = editoreDao.getById(1);
		if(editoreDaSalvareSuFilePdf != null) editorePrintService.saveAsPdf(editoreDaSalvareSuFilePdf);
		else System.out.println("Non è presente nessun editore con l'id specificato");
		
		//salva lista editori su file csv
		System.out.println("---------------------------");
		editorePrintService.saveListAsCsv();

		//salva singolo editore su file csv
		System.out.println("---------------------------");
		Editore editoreDaSalvareSuFileCsv = editoreDao.getById(1);
		if(editoreDaSalvareSuFileCsv != null) editorePrintService.saveAsCsv(editoreDaSalvareSuFileCsv);
		else System.out.println("Non è presente nessun editore con l'id specificato");
	}
	
}
