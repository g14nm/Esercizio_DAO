package it.betacom.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.betacom.dao.AutoreDao;
import it.betacom.model.Autore;

public class AutoreDaoImpl implements AutoreDao {

	private static final Logger logger = LogManager.getLogger(AutoreDaoImpl.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";
	
	@Override
	public List<Autore> getAll() {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = DBHandler.getInstance().getConnessione().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM autori");
			List<Autore> autori = new ArrayList<Autore>();
			while(resultSet.next()) {
				int annoM = resultSet.getInt("annoM");
				//controlla se l'anno di morte ha valore NULL nel db
				if(resultSet.wasNull())
					autori.add(new Autore(resultSet.getInt("codiceA"), resultSet.getString("nomeA"), resultSet.getString("cognome"), resultSet.getInt("annoN"), resultSet.getString("sesso").charAt(0), resultSet.getString("nazione")));
				else
					autori.add(new Autore(resultSet.getInt("codiceA"), resultSet.getString("nomeA"), resultSet.getString("cognome"), resultSet.getInt("annoN"), annoM, resultSet.getString("sesso").charAt(0), resultSet.getString("nazione")));
			}
			return autori;
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
	public Autore getById(int codiceA) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement("SELECT * FROM autori WHERE codiceA = ?");
			statement.setInt(1, codiceA);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				int annoM = resultSet.getInt("annoM");
				//controlla se l'anno di morte ha valore NULL nel db
				if(resultSet.wasNull())
					return new Autore(resultSet.getInt("codiceA"), resultSet.getString("nomeA"), resultSet.getString("cognome"), resultSet.getInt("annoN"), resultSet.getString("sesso").charAt(0), resultSet.getString("nazione"));
				else
					return new Autore(resultSet.getInt("codiceA"), resultSet.getString("nomeA"), resultSet.getString("cognome"), resultSet.getInt("annoN"), annoM, resultSet.getString("sesso").charAt(0), resultSet.getString("nazione"));
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
	public void insert(Autore autore) {
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		try {
			if(autore.getAnnoMorte() != null) {
				statement = DBHandler.getInstance().getConnessione().prepareStatement(
						"INSERT INTO autori (nomeA, cognome, annoN, annoM, sesso, nazione) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				statement.setInt(4, autore.getAnnoMorte());
				statement.setString(5, String.valueOf(autore.getSesso()));
				statement.setString(6, autore.getNazione());
			}
			else {
				statement = DBHandler.getInstance().getConnessione().prepareStatement(
						"INSERT INTO autori (nomeA, cognome, annoN, sesso, nazione) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				statement.setString(4, String.valueOf(autore.getSesso()));
				statement.setString(5, autore.getNazione());
			}
			statement.setString(1, autore.getNome());
			statement.setString(2, autore.getCognome());
			statement.setInt(3, autore.getAnnoNascita());
			int n = statement.executeUpdate();
			if(n != 0) {
				generatedKeys = statement.getGeneratedKeys();
				generatedKeys.next();
				autore.setCodiceA(generatedKeys.getInt(1));
				logger.debug("inserito nella tabella \"autori\" il record: " + autore.getCodiceA() + ", " + autore.getNome() + ", " + autore.getCognome() + ", " + autore.getAnnoNascita() + ", " + autore.getAnnoMorte() + ", " + autore.getSesso() + ", " + autore.getNazione());
				System.out.println("Inserito autore con nome e cognome: " + autore.getNome() + " " + autore.getCognome());
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
	public void update(Autore autore) {
		PreparedStatement statement = null;
		try {
			if(autore.getAnnoMorte() != null) {
				statement = DBHandler.getInstance().getConnessione().prepareStatement(
						"UPDATE autori SET nomeA = ?, cognome = ?, annoN = ?, annoM = ?, sesso = ?, nazione = ? WHERE codiceA = ?");
				statement.setInt(4, autore.getAnnoMorte());
				statement.setString(5, String.valueOf(autore.getSesso()));
				statement.setString(6, autore.getNazione());
				statement.setInt(7, autore.getCodiceA());
			}
			else {
				statement = DBHandler.getInstance().getConnessione().prepareStatement(
						"UPDATE autori SET nomeA = ?, cognome = ?, annoN = ?, sesso = ?, nazione = ? WHERE codiceA = ?" , Statement.RETURN_GENERATED_KEYS);
				statement.setString(4, String.valueOf(autore.getSesso()));
				statement.setString(5, autore.getNazione());
				statement.setInt(6, autore.getCodiceA());
			}
			statement.setString(1, autore.getNome());
			statement.setString(2, autore.getCognome());
			statement.setInt(3, autore.getAnnoNascita());
			int n = statement.executeUpdate();
			if(n != 0) {
				String recordAggiornato = autore.getNome() + ", " + autore.getCognome() + ", " + autore.getAnnoNascita() + ", " + autore.getAnnoMorte() + ", " + autore.getSesso() + ", " + autore.getNazione();
				logger.debug("aggiornato nella tabella \"autori\" il record con codiceA : " + autore.getCodiceA() + " in: \"" + recordAggiornato + "\"");
				System.out.println("Aggiornato autore con codiceA [" + autore.getCodiceA() + "] in: \"" + recordAggiornato + "\"");
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
	public void deleteById(int codiceA) {
		PreparedStatement statement = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"DELETE FROM autori WHERE codiceA = ?");
			statement.setInt(1, codiceA);
			int n = statement.executeUpdate();
			if(n != 0) {
				logger.debug("rimosso dalla tabella \"autori\" il record con codiceA: " + codiceA);
				System.out.println("Rimosso autore con codiceA [" + codiceA + "]");
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
