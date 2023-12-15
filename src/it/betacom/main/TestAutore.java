package it.betacom.main;

import java.util.List;

import it.betacom.dao.AutoreDao;
import it.betacom.dao.impl.AutoreDaoImpl;
import it.betacom.model.Autore;
import it.betacom.service.PrintService;
import it.betacom.service.impl.AutorePrintService;

public class TestAutore {

	public static void main(String[] args) {
		AutoreDao autoreDao = new AutoreDaoImpl();
		PrintService<Autore> autorePrintService = new AutorePrintService();
		
		Autore mdc = new Autore("Miguel", "de Cervantes", 1547, 1616, 'm', "Spagnaa");
		
		//stampiamo la lista degli autori presenti su Db
		System.out.println("---------------------------");
		stampaAutori(autoreDao.getAll());
		
		//stampiamo i dati dell' autore richiesto
		System.out.println("---------------------------");
		int codiceA = 1;
		Autore autore = autoreDao.getById(codiceA);
		if(autore != null)
			System.out.println("Autore richiesto -> " + autore.toString());
		else
			System.out.println("Non è presente nessun autore con il codiceA indicato!");
		
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
		
		//salva lista autori su file txt
		System.out.println("---------------------------");
		autorePrintService.saveListAsTxt();

		//salva singolo autore su file txt
		System.out.println("---------------------------");
		Autore autoreDaSalvareSuFileTxt = autoreDao.getById(1);
		if(autoreDaSalvareSuFileTxt != null) autorePrintService.saveAsTxt(autoreDaSalvareSuFileTxt);
		else System.out.println("Non è presente nessun genere con l'id specificato");

		//salva lista autori su file pdf
		System.out.println("---------------------------");
		autorePrintService.saveListAsPdf();

		//salva singolo autore su file pdf
		System.out.println("---------------------------");
		Autore autoreDaSalvareSuFilePdf = autoreDao.getById(1);
		if(autoreDaSalvareSuFilePdf != null) autorePrintService.saveAsPdf(autoreDaSalvareSuFilePdf);
		else System.out.println("Non è presente nessun genere con l'id specificato");
	}
	
	private static void stampaAutori(List<Autore> autori) {
		if(autori == null || autori.isEmpty())
			System.out.println("Lista autori vuota!");
		else {
			System.out.println("Lista autori");
			autori.forEach(autore -> System.out.println(autore.toString()));
		}
	}
	
}
