package it.betacom.main;

import java.util.List;

import it.betacom.dao.EditoreDao;
import it.betacom.dao.impl.EditoreDaoImpl;
import it.betacom.model.Editore;

public class TestEditore {

	public static void main(String[] args) {
		EditoreDao editoreDao = new EditoreDaoImpl();
		
		//editori di prova
		Editore laterza = new Editore("Laterza");
		Editore deagostini = new Editore("De Agostini");
		Editore bompiani = new Editore("Bompiano");
		Editore adelphi = new Editore("Adelphi");
		
		//stampiamo la lista degli editori presenti su Db
		System.out.println("---------------------------");
		stampaEditori(editoreDao.getAll());
		
		//stampiamo i dati dell' editore richiesto
		System.out.println("---------------------------");
		Editore editore = editoreDao.getById(1);
		if(editore != null) System.out.println("Editore richiesto -> codiceE : " + editore.getCodiceE() + " | nome: " + editore.getNome());
		else System.out.println("Non è presente nessun editore con il codiceE indicato!");
		
		//stampiamo l'esito dell'inserimento e stampiamo la lista Editori
		//editori da inserire(Laterza,De Agostini,Bompiani,Adelphi)
		System.out.println("---------------------------");
		editoreDao.insert(laterza);
		editoreDao.insert(deagostini);
		editoreDao.insert(bompiani);
		editoreDao.insert(adelphi);
		stampaEditori(editoreDao.getAll());
		
		//stampiamo l'esito dell'aggiornamento (es: cambiare nome ) stampiamo la lista Editori
		System.out.println("---------------------------");
		bompiani.setNome("Bompiani");
		editoreDao.update(bompiani);
		stampaEditori(editoreDao.getAll());
		
		//stampiamo l'esito del delete Editore e stampare la lista Editori
		System.out.println("---------------------------");
		editoreDao.deleteById(adelphi.getCodiceE());
		stampaEditori(editoreDao.getAll());
	}

	private static void stampaEditori(List<Editore> editori) {
		if(editori == null || editori.isEmpty())
			System.out.println("Lista editori vuota!");
		else {
			System.out.println("Lista editori");
			editori.forEach(System.out::println);
		}
	}

}
