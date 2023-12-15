package it.betacom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.betacom.dao.AutoreDao;
import it.betacom.dao.EditoreDao;
import it.betacom.dao.GenereDao;
import it.betacom.dao.LibroDao;
import it.betacom.model.Autore;
import it.betacom.model.Editore;
import it.betacom.model.Genere;
import it.betacom.model.Libro;

public class LibroDaoImpl implements LibroDao {
	
	private static final Logger logger = LogManager.getLogger(LibroDaoImpl.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";
	
	@Override
	public List<Libro> getAll() {
		Statement statement = null;
		ResultSet resultSet = null;
		ResultSet resultSetAutore = null;
		ResultSet resultSetGenere = null;
		ResultSet resultSetEditore = null;
		AutoreDao autoreDao = new AutoreDaoImpl();
		GenereDao genereDao = new GenereDaoImpl();
		EditoreDao editoreDao = new EditoreDaoImpl();
		try {
			Connection connection = DBHandler.getInstance().getConnessione();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM libri");
			List<Libro> libri = new ArrayList<Libro>();
			while(resultSet.next()) {
				Libro libro = new Libro();
				libro.setCodiceL(resultSet.getInt("codiceL"));
				libro.setTitolo(resultSet.getString("titolo"));
				libro.setNumPag(resultSet.getInt("numPag"));
				libro.setAnno(resultSet.getInt("anno"));
				Autore autore = autoreDao.getById(resultSet.getInt("codiceA"));
				Genere genere = genereDao.getById(resultSet.getInt("codiceG"));
				Editore editore = editoreDao.getById(resultSet.getInt("codiceE"));
				libro.setNomeAutore(autore.getNome());
				libro.setCognomeAutore(autore.getCognome());
				libro.setGenere(genere.getDescrizione());
				libro.setEditore(editore.getNome());
				libri.add(libro);
			}
			return libri;
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
			e.printStackTrace();
			return null;
		}
		finally {
			if(statement != null) try {statement.close();} catch (SQLException e) {logger.error(e.getMessage());}
			if(resultSet != null) try {resultSet.close();} catch (SQLException e) {logger.error(e.getMessage());}
			if(resultSetAutore != null) try {resultSetAutore.close();} catch (SQLException e) {logger.error(e.getMessage());}
			if(resultSetGenere != null) try {resultSetGenere.close();} catch (SQLException e) {logger.error(e.getMessage());}
			if(resultSetEditore != null) try {resultSetEditore.close();} catch (SQLException e) {logger.error(e.getMessage());}
			DBHandler.getInstance().chiudiConnessione();
		}
	}

	@Override
	public Libro getById(int codiceL) {
		Statement statement = null;
		ResultSet resultSet = null;
		AutoreDao autoreDao = new AutoreDaoImpl();
		GenereDao genereDao = new GenereDaoImpl();
		EditoreDao editoreDao = new EditoreDaoImpl();
		try {
			statement = DBHandler.getInstance().getConnessione().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM libri WHERE codiceL = " + codiceL);
			if(resultSet.next()) {
				Libro libro = new Libro();
				libro.setCodiceL(resultSet.getInt("codiceL"));
				libro.setTitolo(resultSet.getString("titolo"));
				libro.setNumPag(resultSet.getInt("numPag"));
				libro.setAnno(resultSet.getInt("anno"));
				Autore autore = autoreDao.getById(resultSet.getInt("codiceA"));
				Genere genere = genereDao.getById(resultSet.getInt("codiceG"));
				Editore editore = editoreDao.getById(resultSet.getInt("codiceE"));
				libro.setNomeAutore(autore.getNome());
				libro.setCognomeAutore(autore.getCognome());
				libro.setGenere(genere.getDescrizione());
				libro.setEditore(editore.getNome());
				return libro;
			}
			return null;
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
			return null;
		}
		finally {
			if(statement != null) try {statement.close();} catch (SQLException e) {logger.error(e.getMessage());}
			if(resultSet != null) try {resultSet.close();} catch (SQLException e) {logger.error(e.getMessage());}
			DBHandler.getInstance().chiudiConnessione();
		}
	}

	@Override
	public void insert(Libro libro) {
		PreparedStatement statement = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"INSERT INTO libri (titolo, numPag, anno, codiceA, codiceG, codiceE) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, libro.getTitolo());
			statement.setInt(2, libro.getNumPag());
			statement.setInt(3, libro.getAnno());
			statement.setInt(4, libro.getCodiceA());
			statement.setInt(5, libro.getCodiceG());
			statement.setInt(6, libro.getCodiceE());
			int n = statement.executeUpdate();
			if(n != 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				generatedKeys.next();
				libro.setCodiceL(generatedKeys.getInt(1));
				logger.debug("inserito nella tabella \"libri\" il record: " + libro.getCodiceL() + ", " + libro.getTitolo() + ", " + libro.getNumPag() + ", " + libro.getAnno() + ", " + libro.getCodiceA() + ", " + libro.getCodiceG() + ", " + libro.getCodiceE());
				System.out.println("Inserito libro con titolo: " + libro.getTitolo());
			}
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(statement != null) try {statement.close();} catch (SQLException e) {logger.error(e.getMessage());}
			DBHandler.getInstance().chiudiConnessione();
		}
	}

	@Override
	public void update(Libro libro) {
		PreparedStatement statement = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"UPDATE libri SET titolo = ?, numPag = ?, anno = ?, codiceA = ?, codiceG = ?, codiceE = ? WHERE codiceL = ?" , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, libro.getTitolo());
			statement.setInt(2, libro.getNumPag());
			statement.setInt(3, libro.getAnno());
			statement.setInt(4, libro.getCodiceA());
			statement.setInt(5, libro.getCodiceG());
			statement.setInt(6, libro.getCodiceE());
			statement.setInt(7, libro.getCodiceL());
			int n = statement.executeUpdate();
			if(n != 0) {
				String nuovoRecord = libro.getTitolo() + ", " + libro.getNumPag() + ", " + libro.getAnno() + ", " + libro.getCodiceA() + ", " + libro.getCodiceG() + ", " + libro.getCodiceE();
				logger.debug("aggiornato nella tabella \"libri\" il record con codiceL : " + libro.getCodiceL() + " in: \"" + nuovoRecord + "\"");
				System.out.println("Aggiornato libro con codiceL " + libro.getCodiceL() + " in: \"" + nuovoRecord + "\"");
			}
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(statement != null) try {statement.close();} catch (SQLException e) {logger.error(e.getMessage());}
			DBHandler.getInstance().chiudiConnessione();
		}
	}

	@Override
	public void deleteById(int codiceL) {
		Statement statement = null;
		try {
			statement = DBHandler.getInstance().getConnessione().createStatement();
			int n = statement.executeUpdate("DELETE FROM libri WHERE codiceL = " + codiceL);
			if(n != 0) {
				logger.debug("rimosso dalla tabella \"libri\" il record con codiceL: " + codiceL);
				System.out.println("Rimosso libro con codiceL " + codiceL);
			}
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(statement != null) try {statement.close();} catch (SQLException e) {logger.error(e.getMessage());}
			DBHandler.getInstance().chiudiConnessione();
		}
	}

}
