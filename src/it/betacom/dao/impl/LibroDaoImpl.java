package it.betacom.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.betacom.dao.LibroDao;
import it.betacom.model.Libro;

public class LibroDaoImpl implements LibroDao {
	
	private static final Logger logger = LogManager.getLogger(LibroDaoImpl.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";
	
	@Override
	public List<Libro> getAll() {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = DBHandler.getInstance().getConnessione().createStatement();
			resultSet = statement.executeQuery(
					"SELECT l.codiceL, l.titolo, l.numPag, l.anno, a.nomeA AS nomeAutore, a.cognome AS cognomeAutore, g.descrizione AS genere, e.nome AS editore"
					+ " FROM libri l"
					+ " NATURAL JOIN autori a"
					+ " NATURAL JOIN generi g"
					+ " NATURAL JOIN editori e"
					+ " ORDER BY l.codiceL");
			List<Libro> libri = new ArrayList<Libro>();
			while(resultSet.next()) {
				libri.add(new Libro(
						resultSet.getInt("codiceL"),
						resultSet.getString("titolo"),
						resultSet.getInt("numPag"),
						resultSet.getInt("anno"),
						resultSet.getString("nomeAutore"),
						resultSet.getString("cognomeAutore"),
						resultSet.getString("genere"),
						resultSet.getString("editore")
						));
			}
			return libri;
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
			return null;
		}
		finally {
			if(resultSet != null) try {resultSet.close();} catch (SQLException e) {logger.error(e.getMessage());}
			if(statement != null) try {statement.close();} catch (SQLException e) {logger.error(e.getMessage());}
			DBHandler.getInstance().chiudiConnessione();
		}
	}

	@Override
	public Libro getById(int codiceL) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"SELECT * FROM libri WHERE codiceL = ?");
			statement.setInt(1, codiceL);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return new Libro(
						codiceL,
						resultSet.getString("titolo"),
						resultSet.getInt("numPag"),
						resultSet.getInt("anno"),
						resultSet.getInt("codiceA"),
						resultSet.getInt("codiceG"),
						resultSet.getInt("codiceE")
						);
			}
			return null;
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
			return null;
		}
		finally {
			if(resultSet != null) try {resultSet.close();} catch (SQLException e) {logger.error(e.getMessage());}
			if(statement != null) try {statement.close();} catch (SQLException e) {logger.error(e.getMessage());}
			DBHandler.getInstance().chiudiConnessione();
		}
	}
	
	@Override
	public Libro getByIdWithDetails(int codiceL) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"SELECT l.titolo, l.numPag, l.anno, a.nomeA AS nomeAutore, a.cognome AS cognomeAutore, g.descrizione AS genere, e.nome AS editore"
							+ " FROM libri l"
							+ " NATURAL JOIN autori a"
							+ " NATURAL JOIN generi g"
							+ " NATURAL JOIN editori e"
							+ " WHERE l.codiceL = ?");
			statement.setInt(1, codiceL);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return new Libro(
						codiceL,
						resultSet.getString("titolo"),
						resultSet.getInt("numPag"),
						resultSet.getInt("anno"),
						resultSet.getString("nomeAutore"),
						resultSet.getString("cognomeAutore"),
						resultSet.getString("genere"),
						resultSet.getString("editore")
						);
			}
			return null;
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
			return null;
		}
		finally {
			if(resultSet != null) try {resultSet.close();} catch (SQLException e) {logger.error(e.getMessage());}
			if(statement != null) try {statement.close();} catch (SQLException e) {logger.error(e.getMessage());}
			DBHandler.getInstance().chiudiConnessione();
		}
	}

	@Override
	public void insert(Libro libro) {
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"INSERT INTO libri (titolo, numPag, anno, codiceA, codiceG, codiceE) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, libro.getTitolo());
			statement.setInt(2, libro.getNumeroPagine());
			statement.setInt(3, libro.getAnno());
			statement.setInt(4, libro.getCodiceA());
			statement.setInt(5, libro.getCodiceG());
			statement.setInt(6, libro.getCodiceE());
			int n = statement.executeUpdate();
			if(n != 0) {
				generatedKeys = statement.getGeneratedKeys();
				generatedKeys.next();
				libro.setCodiceL(generatedKeys.getInt(1));
				logger.debug("inserito nella tabella \"libri\" il record: " + libro.getCodiceL() + ", " + libro.getTitolo() + ", " + libro.getNumeroPagine() + ", " + libro.getAnno() + ", " + libro.getCodiceA() + ", " + libro.getCodiceG() + ", " + libro.getCodiceE());
				System.out.println("Inserito libro con titolo: " + libro.getTitolo());
			}
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
		finally {
			if(generatedKeys != null) try {generatedKeys.close();} catch (SQLException e) {logger.error(e.getMessage());}
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
			statement.setInt(2, libro.getNumeroPagine());
			statement.setInt(3, libro.getAnno());
			statement.setInt(4, libro.getCodiceA());
			statement.setInt(5, libro.getCodiceG());
			statement.setInt(6, libro.getCodiceE());
			statement.setInt(7, libro.getCodiceL());
			int n = statement.executeUpdate();
			if(n != 0) {
				String recordAggiornato = libro.getTitolo() + ", " + libro.getNumeroPagine() + ", " + libro.getAnno() + ", " + libro.getCodiceA() + ", " + libro.getCodiceG() + ", " + libro.getCodiceE();
				logger.debug("aggiornato nella tabella \"libri\" il record con codiceL : " + libro.getCodiceL() + " in: \"" + recordAggiornato + "\"");
				System.out.println("Aggiornato libro con codiceL [" + libro.getCodiceL() + "] in: \"" + recordAggiornato + "\"");
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
		PreparedStatement statement = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"DELETE FROM libri WHERE codiceL = ?");
			statement.setInt(1, codiceL);
			int n = statement.executeUpdate();
			if(n != 0) {
				logger.debug("rimosso dalla tabella \"libri\" il record con codiceL: " + codiceL);
				System.out.println("Rimosso libro con codiceL [" + codiceL + "]");
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
