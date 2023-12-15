package it.betacom.main;

import java.util.List;

import it.betacom.dao.GenereDao;
import it.betacom.dao.impl.GenereDaoImpl;
import it.betacom.model.Genere;
import it.betacom.service.PrintService;
import it.betacom.service.impl.GenerePrintService;

public class TestGenere {

	public static void main(String[] args) {
		GenereDao genereDao = new GenereDaoImpl();
		PrintService<Genere> generePrintService = new GenerePrintService();
		
		Genere biografia = new Genere("biografie");
		
		//stampiamo la lista dei generi presenti su Db
		System.out.println("---------------------------");
		System.out.println("Lista dei generi");
		List<Genere> generi = genereDao.getAll();
		generi.forEach(g -> System.out.println("codiceG : " + g.getCodiceG() + " | descrizione : " + g.getDescrizione()));
		
		//stampiamo i dati del genere  richiesto
		System.out.println("---------------------------");
		int codiceG = 1;
		Genere genere = genereDao.getById(codiceG);
		if(genere != null)
			System.out.println("Genere richiesto -> codiceG : " + genere.getCodiceG() + " | descrizione : " + genere.getDescrizione());
		else
			System.out.println("Non è presente nessun genere con il codiceG indicato!");
		
		//stampiamo l'esito dell'inserimento e stampiamo la lista
		System.out.println("---------------------------");
		genereDao.insert(biografia);
		System.out.println("Lista dei generi");
		generi = genereDao.getAll();
		generi.forEach(g -> System.out.println("codiceG : " + g.getCodiceG() + " | descrizione : " + g.getDescrizione()));
		
		//stampiamo l'esito dell'aggiornamento (es: cambiare descrizone romanzi in romanzo) stampiamo la lista
		System.out.println("---------------------------");
		biografia.setDescrizione("biografia");
		genereDao.update(biografia);
		System.out.println("Lista dei generi");
		generi = genereDao.getAll();
		generi.forEach(g -> System.out.println("codiceG : " + g.getCodiceG() + " | descrizione : " + g.getDescrizione()));
		
		//stampiamo l'esito del delete e stampare la lista
		System.out.println("---------------------------");
		genereDao.deleteById(biografia.getCodiceG());
		System.out.println("Lista dei generi");
		generi = genereDao.getAll();
		generi.forEach(g -> System.out.println("codiceG : " + g.getCodiceG() + " | descrizione : " + g.getDescrizione()));
		
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
	}

}
