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

import it.betacom.dao.impl.AutoreDaoImpl;
import it.betacom.model.Autore;
import it.betacom.service.PrintService;
import it.betacom.util.PropertiesLoader;

public class AutorePrintService implements PrintService<Autore> {

	private static final Logger logger = LogManager.getLogger(AutorePrintService.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";

	@Override
	public void saveListAsPdf() {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(this.getDirectory() + "Lista Autori.pdf"));
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
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(this.getDirectory() + "Lista Autori.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			writer.writeNext(new String[] {"codiceA", "nome", "cognome", "anno nascita", "anno morte", "sesso", "nazione"});
			new AutoreDaoImpl().getAll().forEach(a -> writer.writeNext(new String[] {
					String.valueOf(a.getCodiceA()),
					a.getNome(),
					a.getCognome(),
					String.valueOf(a.getAnnoNascita()),
					((a.getAnnoMorte() == null) ? "in vita" : String.valueOf(a.getAnnoMorte())),
					String.valueOf(a.getSesso()),
					a.getNazione()})
					);
			writer.close();
			logger.debug("generato file csv lista autori");
			System.out.println("Generato file csv con la lista degli autori");
		}
		catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveListAsTxt() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.getDirectory() + "Lista Autori.txt"));
			writer.write(listaAutoriToString());
			writer.flush();
			writer.close();
			logger.debug("generato file txt lista autori");
			System.out.println("Generato file txt con la lista degli autori");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsPdf(Autore autore) {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(this.getDirectory() + "Autore - " + autore.getNome() + " " + autore.getCognome() + ".pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph(autore.details()));
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
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(this.getDirectory() + "Autore - " + autore.getNome() + " " + autore.getCognome() + ".csv"), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			writer.writeNext(new String[] {"codiceA", "nome", "cognome", "anno nascita", "anno morte", "sesso", "nazione"});
			writer.writeNext(new String[] {
					String.valueOf(autore.getCodiceA()),
					autore.getNome(),
					autore.getCognome(),
					String.valueOf(autore.getAnnoNascita()),
					((autore.getAnnoMorte() == null) ? "in vita" : String.valueOf(autore.getAnnoMorte())),
					String.valueOf(autore.getSesso()),
					autore.getNazione()}
					);
			writer.close();
			logger.debug("generato file csv autore con codiceA : " + autore.getCodiceA());
			System.out.println("Generato file csv con l'autore specificato");
		}
		catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsTxt(Autore autore) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.getDirectory() + "Autore - " + autore.getNome() + " " + autore.getCognome() + ".txt"));
			writer.write(autore.details());
			writer.flush();
			writer.close();
			logger.debug("generato file txt autore con codiceA : " + autore.getCodiceA());
			System.out.println("Generato file txt con l'autore specificato");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}
	
	private String getDirectory() {
		return PropertiesLoader.getDocumentsPathProperty();
	}
	
	public static String listaAutoriToString() {
		StringBuilder sb = new StringBuilder();
		new AutoreDaoImpl().getAll().forEach(a -> sb.append(a).append("\n"));
		return sb.toString();
	}
	
}
