package it.betacom.main;

import java.util.List;

import it.betacom.dao.GenereDao;
import it.betacom.dao.impl.GenereDaoImpl;
import it.betacom.model.Genere;

public class TestGenere {

	public static void main(String[] args) {
		GenereDao genereDao = new GenereDaoImpl();
		
		//genere di prova
		Genere biografia = new Genere("biografie");
		
		//stampiamo la lista dei generi presenti su Db
		System.out.println("---------------------------");
		stampaGeneri(genereDao.getAll());
		
		//stampiamo i dati del genere  richiesto
		System.out.println("---------------------------");
		Genere genere = genereDao.getById(1);
		if(genere != null) System.out.println("Genere richiesto -> codiceG : " + genere.getCodiceG() + " | descrizione : " + genere.getDescrizione());
		else System.out.println("Non è presente nessun genere con il codiceG indicato!");
		
		//stampiamo l'esito dell'inserimento e stampiamo la lista
		System.out.println("---------------------------");
		genereDao.insert(biografia);
		stampaGeneri(genereDao.getAll());
		
		//stampiamo l'esito dell'aggiornamento (es: cambiare descrizone romanzi in romanzo) stampiamo la lista
		System.out.println("---------------------------");
		biografia.setDescrizione("biografia");
		genereDao.update(biografia);
		stampaGeneri(genereDao.getAll());
		
		//stampiamo l'esito del delete e stampare la lista
		System.out.println("---------------------------");
		genereDao.deleteById(biografia.getCodiceG());
		stampaGeneri(genereDao.getAll());
	}
	
	private static void stampaGeneri(List<Genere> generi) {
		if(generi == null || generi.isEmpty())
			System.out.println("Lista generi vuota!");
		else {
			System.out.println("Lista generi");
			generi.forEach(System.out::println);
		}
	}

}
