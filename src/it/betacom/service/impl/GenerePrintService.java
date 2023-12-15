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

import it.betacom.dao.GenereDao;
import it.betacom.dao.impl.GenereDaoImpl;
import it.betacom.model.Genere;
import it.betacom.service.PrintService;

public class GenerePrintService implements PrintService<Genere> {

	private static final String OUTPUT_DIRECTORY = "./Archivio_" + LocalDate.now().toString().replace('-', '_') + "/";

	private static final Logger logger = LogManager.getLogger(GenerePrintService.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";

	private String listaGeneriToString() {
		StringBuilder sb = new StringBuilder();
		GenereDao genereDao = new GenereDaoImpl();
		for(Genere g : genereDao.getAll()) {
			sb.append(g.getCodiceG() + " | " + g.getDescrizione());
			sb.append("\n");
		}
		return new String(sb);
	}

	@Override
	public void saveListAsPdf() {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(OUTPUT_DIRECTORY + "ListaGeneri.pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph((listaGeneriToString())));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void saveListAsTxt() {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "ListaGeneri.txt"));
			writer.write(listaGeneriToString());
			logger.debug("generato file txt lista generi");
			System.out.println("Generato file txt con la lista dei generi");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(writer != null) try {writer.close();} catch (IOException e) {logger.error(e.getMessage()); System.out.println(ERRORE);}
		}
	}

	@Override
	public void saveAsPdf(Genere genere) {
		try {
			PdfDocument pdf = new PdfDocument(new PdfWriter(OUTPUT_DIRECTORY + "Genere - " + genere.getDescrizione() + ".pdf"));
			Document document = new Document(pdf);
			document.add(new Paragraph(genere.getCodiceG() + " | " + genere.getDescrizione()));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAsTxt(Genere genere) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "Genere - " + genere.getDescrizione() + ".txt"));
			writer.write(genere.getCodiceG() + " | " + genere.getDescrizione());
			logger.debug("generato file txt genere con codiceG : " + genere.getCodiceG());
			System.out.println("Generato file txt con il genere specificato");
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(writer != null) try {writer.close();} catch (IOException e) {logger.error(e.getMessage()); System.out.println(ERRORE);}
		}
	}
}
