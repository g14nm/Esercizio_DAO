package it.betacom.main;

import java.util.List;

import it.betacom.dao.EditoreDao;
import it.betacom.dao.impl.EditoreDaoImpl;
import it.betacom.model.Editore;
import it.betacom.service.PrintService;
import it.betacom.service.impl.EditorePrintService;

public class TestEditore {

	public static void main(String[] args) {
		EditoreDao editoreDao = new EditoreDaoImpl();
		PrintService<Editore> editorePrintService = new EditorePrintService();
		
		Editore laterza = new Editore("Laterza");
		Editore deagostini = new Editore("De Agostini");
		Editore bompiani = new Editore("Bompiano");
		Editore adelphi = new Editore("Adelphi");
		
		//stampiamo la lista degli editori presenti su Db
		System.out.println("---------------------------");
		System.out.println("Lista degli editori");
		List<Editore> editori = editoreDao.getAll();
		editori.forEach(e -> System.out.println("codiceE : " + e.getCodiceE() + " | nome : " + e.getNome()));
		
		//stampiamo i dati dell' editore richiesto
		System.out.println("---------------------------");
		int codiceE = 1;
		Editore editore = editoreDao.getById(codiceE);
		if(editore != null)
			System.out.println("Editore richiesto -> codiceE : " + editore.getCodiceE() + " | nome: " + editore.getNome());
		else
			System.out.println("Non è presente nessun editore con il codiceE indicato!");
		
		//stampiamo l'esito dell'inserimento e stampiamo la lista Editori
		//editori da inserire(Laterza,De Agostini,Bompiani,Adelphi)
		System.out.println("---------------------------");
		editoreDao.insert(laterza);
		editoreDao.insert(deagostini);
		editoreDao.insert(bompiani);
		editoreDao.insert(adelphi);
		System.out.println("Lista degli editori");
		editori = editoreDao.getAll();
		editori.forEach(e -> System.out.println("codiceE : " + e.getCodiceE() + " | nome : " + e.getNome()));
		
		//stampiamo l'esito dell'aggiornamento (es: cambiare nome ) stampiamo la lista Editori
		System.out.println("---------------------------");
		bompiani.setNome("Bompiani");
		editoreDao.update(bompiani);
		System.out.println("Lista degli editori");
		editori = editoreDao.getAll();
		editori.forEach(e -> System.out.println("codiceE : " + e.getCodiceE() + " | nome : " + e.getNome()));
		
		//stampiamo l'esito del delete Editore e stampare la lista Editori
		System.out.println("---------------------------");
		editoreDao.deleteById(adelphi.getCodiceE());
		System.out.println("Lista degli editori");
		editori = editoreDao.getAll();
		editori.forEach(e -> System.out.println("codiceE : " + e.getCodiceE() + " | nome : " + e.getNome()));
		
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
	}

}
