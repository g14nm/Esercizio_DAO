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

import it.betacom.dao.impl.EditoreDaoImpl;
import it.betacom.model.Editore;
import it.betacom.service.PrintService;
import it.betacom.util.PropertiesLoader;

public class EditorePrintService implements PrintService<Editore> {
	
	private static final Logger logger = LogManager.getLogger(EditorePrintService.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";
	
	@Override
	public void saveListAsPdf() {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(this.getDirectory() + "Lista Editori.pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph(listaEditoriToString()));
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
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(this.getDirectory() + "Lista Editori.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			writer.writeNext(new String[] {"codiceE", "nome"});
			new EditoreDaoImpl().getAll().forEach(e -> writer.writeNext(new String[] {String.valueOf(e.getCodiceE()), e.getNome()}));
			writer.close();
			logger.debug("generato file csv lista editori");
			System.out.println("Generato file csv con la lista degli editori");
		}
		catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveListAsTxt() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.getDirectory() + "Lista Editori.txt"));
			writer.write(listaEditoriToString());
			writer.flush();
			writer.close();
			logger.debug("generato file txt lista editori");
			System.out.println("Generato file txt con la lista degli editori");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsPdf(Editore editore) {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(this.getDirectory() + "Editore - " + editore.getNome() + ".pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph(editore.toString()));
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
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(this.getDirectory() + "Editore - " + editore.getNome() + ".csv"), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			writer.writeNext(new String[] {"codiceE", "nome"});
			writer.writeNext(new String[] {String.valueOf(editore.getCodiceE()), editore.getNome()});
			writer.close();
			logger.debug("generato file csv editore con codiceE : " + editore.getCodiceE());
			System.out.println("Generato file csv con l'editore specificato");
		}
		catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}

	@Override
	public void saveAsTxt(Editore editore) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.getDirectory() + "Editore - " + editore.getNome() + ".txt"));
			writer.write(editore.toString());
			writer.flush();
			writer.close();
			logger.debug("generato file txt editore con codiceE : " + editore.getCodiceE());
			System.out.println("Generato file txt con l'editore specificato");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}
	
	private String getDirectory() {
		return PropertiesLoader.getDocumentsPathProperty();
	}
	
	private String listaEditoriToString() {
		StringBuilder sb = new StringBuilder();
		new EditoreDaoImpl().getAll().forEach(e -> sb.append(e).append("\n"));
		return sb.toString();
	}

}
