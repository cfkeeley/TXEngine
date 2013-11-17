/**
 * Enterprise Computer Solutions 
 * http://www.encs.co.uk 
 */
package uk.co.encs.transformation.engine.config;

import java.util.Hashtable;

/**
 * @author Chris Keeley ECS http://www.encs.co.uk
 * This value bean is a container for all config file 
 * values parsed from the configuration file at runtime
 */
public class ConfigEntityBean {
	
	private String logLevel;
	
	private Hashtable<String, String> parsers;
	/**
	 * @return the logLevel
	 */
	public String getLogLevel() {
		return this.logLevel;
	}
	/**
	 * @return the parsers
	 */
	public Hashtable<String, String> getParsers() {
		return this.parsers;
	}
	/**
	 * @param logLevel the logLevel to set
	 */
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	/**
	 * @param parsers the parsers to set
	 */
	public void setParsers(Hashtable<String, String> parsers) {
		this.parsers = parsers;
	}
}
