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

import it.betacom.dao.LibroDao;
import it.betacom.dao.impl.LibroDaoImpl;
import it.betacom.model.Libro;
import it.betacom.service.PrintService;

public class LibroPrintService implements PrintService<Libro> {

	private static final String OUTPUT_DIRECTORY = "./Archivio_" + LocalDate.now().toString().replace('-', '_') + "/";

	private static final Logger logger = LogManager.getLogger(LibroPrintService.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";

	private String listaLibriToString() {
		StringBuilder sb = new StringBuilder();
		LibroDao libroDao = new LibroDaoImpl();
		for(Libro l : libroDao.getAll()) {
			sb.append("------------------------------------------------\n");
			sb.append(l.toString());
			sb.append("\n");
		}
		return new String(sb);
	}

	@Override
	public void saveListAsPdf() {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(OUTPUT_DIRECTORY + "ListaLibri.pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph((listaLibriToString())));
			document.close();
			logger.debug("generato file pdf lista libri");
			System.out.println("Generato file pdf con la lista dei libri");
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
			writer = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "ListaLibri.txt"));
			writer.write(listaLibriToString());
			logger.debug("generato file txt lista libri");
			System.out.println("Generato file txt con la lista dei libri");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(writer != null) try {writer.close();} catch (IOException e) {logger.error(e.getMessage()); System.out.println(ERRORE);}
		}
	}

	@Override
	public void saveAsPdf(Libro libro) {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(OUTPUT_DIRECTORY + "Libro - " + libro.getTitolo() + ".pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph(libro.toString()));
			document.close();
			logger.debug("generato file pdf libro con codiceL : " + libro.getCodiceL());
			System.out.println("Generato file pdf con il libro specificato");
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsCsv(Libro libro) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAsTxt(Libro libro) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "Libro - " + libro.getTitolo() + ".txt"));
			writer.write(libro.toString());
			logger.debug("generato file txt libro con codiceA : " + libro.getCodiceA());
			System.out.println("Generato file txt con il libro specificato");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(writer != null) try {writer.close();} catch (IOException e) {logger.error(e.getMessage()); System.out.println(ERRORE);}
		}
	}

}
