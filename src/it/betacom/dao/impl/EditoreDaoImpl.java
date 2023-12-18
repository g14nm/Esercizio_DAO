package it.betacom.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.betacom.dao.EditoreDao;
import it.betacom.model.Editore;

public class EditoreDaoImpl implements EditoreDao {
	
	private static final Logger logger = LogManager.getLogger(EditoreDaoImpl.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";

	@Override
	public List<Editore> getAll() {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = DBHandler.getInstance().getConnessione().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM editori");
			List<Editore> editori = new ArrayList<Editore>();
			while(resultSet.next()) {
				editori.add(new Editore(resultSet.getInt("codiceE"), resultSet.getString("nome")));
			}
			return editori;
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
	public Editore getById(int codiceE) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement("SELECT * FROM editori WHERE codiceE = ?");
			statement.setInt(1, codiceE);
			resultSet = statement.executeQuery();
			if(resultSet.next())
				return new Editore(resultSet.getInt("codiceE"), resultSet.getString("nome"));
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
	public void insert(Editore editore) {
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"INSERT INTO editori (nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, editore.getNome());
			int n = statement.executeUpdate();
			if(n != 0) {
				generatedKeys = statement.getGeneratedKeys();
				generatedKeys.next();
				editore.setCodiceE(generatedKeys.getInt(1));
				logger.debug("inserito nella tabella \"editori\" il record: " + editore.getCodiceE() + ", " + editore.getNome());
				System.out.println("Inserito editore con nome: " + editore.getNome());
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
	public void update(Editore editore) {
		PreparedStatement statement = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"UPDATE editori SET nome = ? WHERE codiceE = ?");
			statement.setString(1, editore.getNome());
			statement.setInt(2, editore.getCodiceE());
			int n = statement.executeUpdate();
			if(n != 0) {
				logger.debug("aggiornato nella tabella \"editori\" il campo 'nome' del record con codiceE : " + editore.getCodiceE() + " in: \"" + editore.getNome());
				System.out.println("Aggiornato il nome dell'editore con codiceE [" + editore.getCodiceE() + "] in \"" + editore.getNome() + "\"");
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
	public void deleteById(int codiceE) {
		PreparedStatement statement = null;
		try {
			statement = DBHandler.getInstance().getConnessione().prepareStatement(
					"DELETE FROM editori WHERE codiceE = ?");
			statement.setInt(1, codiceE);
			int n = statement.executeUpdate();
			if(n != 0) {
				logger.debug("rimosso dalla tabella \"editori\" il record con codiceE: " + codiceE);
				System.out.println("Rimosso editore con codiceE [" + codiceE + "]");
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
