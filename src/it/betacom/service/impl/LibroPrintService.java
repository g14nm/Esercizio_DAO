package it.betacom.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.opencsv.CSVWriter;

import it.betacom.dao.impl.LibroDaoImpl;
import it.betacom.model.Libro;
import it.betacom.service.PrintService;
import it.betacom.util.PropertiesLoader;

public class LibroPrintService implements PrintService<Libro> {

	private static final Logger logger = LogManager.getLogger(LibroPrintService.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";

	@Override
	public void saveListAsPdf() {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(this.getDirectory() + "Lista Libri.pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph(listaLibriToString()));
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
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(this.getDirectory() + "Lista Libri.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			writer.writeNext(new String[] {"codiceL", "titolo", "numero pagine", "anno", "autore", "genere", "editore"});
			new LibroDaoImpl().getAll().forEach(l -> writer.writeNext(new String[] {
					String.valueOf(l.getCodiceL()),
					l.getTitolo(),
					String.valueOf(l.getNumeroPagine()),
					String.valueOf(l.getAnno()),
					l.getNomeAutore() + " " + l.getCognomeAutore(),
					l.getGenere(),
					l.getEditore()})
					);
			writer.close();
			logger.debug("generato file csv lista libri");
			System.out.println("Generato file csv con la lista dei libri");
		}
		catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveListAsTxt() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.getDirectory() + "Lista Libri.txt"));
			writer.write(listaLibriToString());
			writer.flush();
			writer.close();
			logger.debug("generato file txt lista libri");
			System.out.println("Generato file txt con la lista dei libri");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsPdf(Libro libro) {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(this.getDirectory() + "Libro - " + libro.getTitolo() + " - " + libro.getNomeAutore() + " " + libro.getCognomeAutore() + ".pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph(libro.details()));
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
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(this.getDirectory() + "Libro - " + libro.getTitolo() + " - " + libro.getNomeAutore() + " " + libro.getCognomeAutore() + ".csv"), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			writer.writeNext(new String[] {"codiceL", "titolo", "numero pagine", "anno", "autore", "genere", "editore"});
			writer.writeNext(new String[] {
					String.valueOf(libro.getCodiceL()),
					libro.getTitolo(),
					String.valueOf(libro.getNumeroPagine()),
					String.valueOf(libro.getAnno()),
					libro.getNomeAutore() + " " + libro.getCognomeAutore(),
					libro.getGenere(),
					libro.getEditore()}
					);
			writer.close();
			logger.debug("generato file csv libro con codiceL : " + libro.getCodiceL());
			System.out.println("Generato file csv con il libro specificato");
		}
		catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsTxt(Libro libro) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.getDirectory() + "Libro - " + libro.getTitolo() + " - " + libro.getNomeAutore() + " " + libro.getCognomeAutore() + ".txt"));
			writer.write(libro.details());
			writer.flush();
			writer.close();
			logger.debug("generato file txt libro con codiceA : " + libro.getCodiceL());
			System.out.println("Generato file txt con il libro specificato");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}
	
	private String getDirectory() {
		return PropertiesLoader.getDocumentsPathProperty();
	}
	
	private String listaLibriToString() {
		StringBuilder sb = new StringBuilder();
		new LibroDaoImpl().getAll().forEach(l -> sb.append(l).append("\n"));
		return sb.toString();
	}

}
