/**
 * Enterprise Computer Solutions 
 * http://www.encs.co.uk 
 */
package uk.co.encs.transformation.engine.config;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author Chris Keeley ECS http://www.encs.co.uk
 * Read in the contents of the config file and assign the values into the config
 * bean entity.
 */
public class ConfigFileReader {
	private Document configFile;
	/**
	 * default class constructor
	 */
	public ConfigFileReader() {
		// do nothing
	}
	
	/**
	 * Read the configuration file into a dom document
	 * @param configFilePath 
	 *
	 */
	public void readConfigFile(String configFilePath) {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			configFile = docBuilder.parse (new File(configFilePath));
		} catch (SAXException ex) {
//			 TODO deal with SAXException
			ex.printStackTrace();
		} catch (IOException ex) {
			// TODO deal with IOException
			ex.printStackTrace();
		} catch (ParserConfigurationException ex) {
//			 TODO deal with ParserConfigurationException
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return the configuration file document
	 */
	public Document getDocument() {
		return this.configFile;
	}
}
