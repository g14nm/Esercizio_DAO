package it.betacom.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.betacom.dao.GenereDao;
import it.betacom.model.Genere;

public class GenereDaoImpl implements GenereDao {
	
	private static final Logger logger = LogManager.getLogger(GenereDaoImpl.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";

	@Override
	public List<Genere> getAll() {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = DBHandler.getInstance().getConnessione().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM generi");
			List<Genere> generi = new ArrayList<Genere>();
			while(resultSet.next()) {
				generi.add(new Genere(resultSet.getInt(1), resultSet.getString(2)));
			}
			return generi;
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
	public Genere getById(int codiceG) {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = DBHandler.getInstance().getConnessione().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM generi WHERE codiceG = " + codiceG);
			if(resultSet.next())
				return new Genere(resultSet.getInt(1), resultSet.getString(2));
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
	public void insert(Genere genere) {
		PreparedStatement statement = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"INSERT INTO generi (descrizione) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, genere.getDescrizione());
			int n = statement.executeUpdate();
			if(n != 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				generatedKeys.next();
				genere.setCodiceG(generatedKeys.getInt(1));
				logger.debug("inserito nella tabella \"generi\" il record: " + genere.getCodiceG() + ", " + genere.getDescrizione());
				System.out.println("Inserito genere con descrizione: " + genere.getDescrizione());
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
	public void update(Genere genere) {
		PreparedStatement statement = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"UPDATE generi SET descrizione = ? WHERE codiceG = ?");
			statement.setString(1, genere.getDescrizione());
			statement.setInt(2, genere.getCodiceG());
			int n = statement.executeUpdate();
			if(n != 0) {
				logger.debug("aggiornato nella tabella \"generi\" il record con codiceG : " + genere.getCodiceG());
				System.out.println("Aggiornata descrizione genere con codiceG " + genere.getCodiceG() + " in \"" + genere.getDescrizione() + "\"");
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
	public void deleteById(int codiceG) {
		Statement statement = null;
		try {
			statement = DBHandler.getInstance().getConnessione().createStatement();
			int n = statement.executeUpdate("DELETE FROM generi WHERE codiceG = " + codiceG);
			if(n != 0) {
				logger.debug("rimosso dalla tabella \"generi\" il record con codiceG: " + codiceG);
				System.out.println("Rimosso genere con codiceG " + codiceG);
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
