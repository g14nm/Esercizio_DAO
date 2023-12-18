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

import it.betacom.dao.impl.GenereDaoImpl;
import it.betacom.model.Genere;
import it.betacom.service.PrintService;
import it.betacom.util.PropertiesLoader;

public class GenerePrintService implements PrintService<Genere> {

	private static final Logger logger = LogManager.getLogger(GenerePrintService.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";

	@Override
	public void saveListAsPdf() {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(this.getDirectory() + "Lista Generi.pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph(listaGeneriToString()));
			document.close();
			logger.debug("generato file pdf lista generi");
			System.out.println("Generato file pdf con la lista dei generi");
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveListAsCsv() {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(this.getDirectory() + "Lista Generi.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			writer.writeNext(new String[] {"codiceG", "descrizione"});
			new GenereDaoImpl().getAll().forEach(g -> writer.writeNext(new String[] {String.valueOf(g.getCodiceG()), g.getDescrizione()}));
			writer.close();
			logger.debug("generato file csv lista generi");
			System.out.println("Generato file csv con la lista dei generi");
		}
		catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveListAsTxt() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.getDirectory() + "Lista Generi.txt"));
			writer.write(listaGeneriToString());
			writer.flush();
			writer.close();
			logger.debug("generato file txt lista generi");
			System.out.println("Generato file txt con la lista dei generi");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsPdf(Genere genere) {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(this.getDirectory() + "Genere - " + genere.getDescrizione() + ".pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph(genere.toString()));
			document.close();
			logger.debug("generato file pdf genere con codiceG : " + genere.getCodiceG());
			System.out.println("Generato file pdf con il genere specificato");
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsCsv(Genere genere) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(this.getDirectory() + "Genere - " + genere.getDescrizione() + ".csv"), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			writer.writeNext(new String[] {"codiceG", "descrizione"});
			writer.writeNext(new String[] {String.valueOf(genere.getCodiceG()), genere.getDescrizione()});
			writer.close();
			logger.debug("generato file csv genere con codiceG : " + genere.getCodiceG());
			System.out.println("Generato file csv con il genere specificato");
		}
		catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsTxt(Genere genere) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.getDirectory() + "Genere - " + genere.getDescrizione() + ".txt"));
			writer.write(genere.toString());
			writer.flush();
			writer.close();
			logger.debug("generato file txt genere con codiceG : " + genere.getCodiceG());
			System.out.println("Generato file txt con il genere specificato");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}
	
	private String getDirectory() {
		return PropertiesLoader.getDocumentsPathProperty();
	}
	
	private String listaGeneriToString() {
		StringBuilder sb = new StringBuilder();
		new GenereDaoImpl().getAll().forEach(g -> sb.append(g).append("\n"));
		return sb.toString();
	}
	
}
