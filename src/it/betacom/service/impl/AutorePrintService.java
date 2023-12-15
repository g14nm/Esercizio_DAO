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

import it.betacom.dao.AutoreDao;
import it.betacom.dao.impl.AutoreDaoImpl;
import it.betacom.model.Autore;
import it.betacom.service.PrintService;

public class AutorePrintService implements PrintService<Autore> {

	private static final String OUTPUT_DIRECTORY = "./Archivio_" + LocalDate.now().toString().replace('-', '_') + "/";

	private static final Logger logger = LogManager.getLogger(AutorePrintService.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";

	private String listaAutoriToString() {
		StringBuilder sb = new StringBuilder();
		AutoreDao autoreDao = new AutoreDaoImpl();
		for(Autore a : autoreDao.getAll()) {
			sb.append("------------------------------------------------\n");
			sb.append(a.toString());
			sb.append("\n");
		}
		return new String(sb);
	}

	@Override
	public void saveListAsPdf() {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(OUTPUT_DIRECTORY + "ListaAutori.pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph((listaAutoriToString())));
			document.close();
			logger.debug("generato file pdf lista autori");
			System.out.println("Generato file pdf con la lista degli autori");
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
			writer = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "ListaAutori.txt"));
			writer.write(listaAutoriToString());
			logger.debug("generato file txt lista autori");
			System.out.println("Generato file txt con la lista degli autori");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(writer != null) try {writer.close();} catch (IOException e) {logger.error(e.getMessage()); System.out.println(ERRORE);}
		}
	}

	@Override
	public void saveAsPdf(Autore autore) {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(OUTPUT_DIRECTORY + "Autore - " + autore.getNome() + "_" + autore.getCognome() + ".pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph(autore.toString()));
			document.close();
			logger.debug("generato file pdf autore con codiceA : " + autore.getCodiceA());
			System.out.println("Generato file pdf con l'autore specificato");
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsCsv(Autore autore) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAsTxt(Autore autore) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "Autore - " + autore.getNome() + "_" + autore.getCognome() + ".txt"));
			writer.write(autore.toString());
			logger.debug("generato file txt autore con codiceA : " + autore.getCodiceA());
			System.out.println("Generato file txt con l'autore specificato");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(writer != null) try {writer.close();} catch (IOException e) {logger.error(e.getMessage()); System.out.println(ERRORE);}
		}
	}
	
}
