package it.betacom.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.betacom.util.PropertiesLoader;

public class DBHandler {
	
	private static DBHandler instance;
	private Connection connection;
	private String connectionUrlAndSchema;
	private String user;
	private String password;
	
	private static final Logger logger = LogManager.getLogger(DBHandler.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";
	
	private DBHandler() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			logger.debug("driver caricato correttamente");
			Properties properties = PropertiesLoader.loadProperties();
			this.connectionUrlAndSchema = "jdbc:mysql://" + properties.getProperty("db.url") + "/" + properties.getProperty("db.schema");
			this.user = properties.getProperty("db.user");
			this.password = properties.getProperty("db.password");
			logger.debug("caricate proprietà della connessione al db dal file di configurazione");
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
		}
	}
	
	public static DBHandler getInstance() {
		if(instance == null)
			instance = new DBHandler();
		return instance;
	}

	public Connection getConnessione() {
		try {
			this.connection = DriverManager.getConnection(this.connectionUrlAndSchema, this.user, this.password);
			logger.debug("avviata connessione a " + this.connectionUrlAndSchema);
			return this.connection;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
			return null;
		}
	}

	public void chiudiConnessione() {
		if(this.connection != null) {
			try {
				this.connection.close();
				logger.debug("terminata connessione a " + this.connectionUrlAndSchema);
			} catch (SQLException e) {
				logger.error(e.getMessage());
				System.out.println(ERRORE);
			};
		}
	}

}
