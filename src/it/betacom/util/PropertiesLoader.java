package it.betacom.util;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesLoader {
	
	private static final String PROPERTIES_FILE_PATH = "./config.properties";
	private static final Logger logger = LogManager.getLogger(PropertiesLoader.class.getName());
	private static final String ERRORE = "Errore! Controllare file di log";
	
	public static Properties loadProperties() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(PROPERTIES_FILE_PATH));
			return properties;
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
			return null;
		}
	}
	
	public static String getDocumentsPathProperty() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(PROPERTIES_FILE_PATH));
			String dirName = properties.getProperty("documents_path");
			Path dirPath = Paths.get(dirName);
			if(!Files.exists(dirPath)) {
				Files.createDirectory(dirPath);
				logger.debug("creata directory " + dirPath.toString());
			}
			return dirName + "/";
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(ERRORE);
			return null;
		}
	}
	
}
