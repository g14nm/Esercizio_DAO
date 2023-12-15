package it.betacom.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import it.betacom.dao.EditoreDao;
import it.betacom.dao.impl.EditoreDaoImpl;
import it.betacom.model.Editore;
import it.betacom.service.PrintService;

public class EditorePrintService implements PrintService<Editore> {
	
	private static final String OUTPUT_DIRECTORY = "./Archivio_" + LocalDate.now().toString().replace('-', '_') + "/";
	
	private static final Logger logger = LogManager.getLogger(EditorePrintService.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";

	private String listaEditoriToString() {
		StringBuilder sb = new StringBuilder();
		EditoreDao editoreDao = new EditoreDaoImpl();
		for(Editore e : editoreDao.getAll()) {
			sb.append(e.getCodiceE() + " | " + e.getNome());
			sb.append("\n");
		}
		return new String(sb);
	}
	
	@Override
	public void saveListAsPdf() {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(OUTPUT_DIRECTORY + "ListaEditori.pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph((listaEditoriToString())));
			document.close();
			logger.debug("generato file pdf lista editori");
			System.out.println("Generato file pdf con la lista degli editori");
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveListAsCsv() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveListAsTxt() {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "ListaEditori.txt"));
			writer.write(listaEditoriToString());
			logger.debug("generato file txt lista editori");
			System.out.println("Generato file txt con la lista degli editori");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(writer != null) try {writer.close();} catch (IOException e) {logger.error(e.getMessage()); System.out.println(ERRORE);}
		}
	}

	@Override
	public void saveAsPdf(Editore editore) {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(OUTPUT_DIRECTORY + "Editore - " + editore.getNome() + ".pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph(editore.getCodiceE() + " | " + editore.getNome()));
			document.close();
			logger.debug("generato file pdf editore con codiceE : " + editore.getCodiceE());
			System.out.println("Generato file pdf con l'editore specificato");
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsCsv(Editore editore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAsTxt(Editore editore) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "Editore - " + editore.getNome() + ".txt"));
			writer.write(editore.getCodiceE() + " | " + editore.getNome());
			logger.debug("generato file txt editore con codiceE : " + editore.getCodiceE());
			System.out.println("Generato file txt con l'editore specificato");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(writer != null) try {writer.close();} catch (IOException e) {logger.error(e.getMessage()); System.out.println(ERRORE);}
		}
	}

}
