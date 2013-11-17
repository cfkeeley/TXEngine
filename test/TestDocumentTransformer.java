/**
 * Enterprise Computer Solutions 
 * http://www.encs.co.uk 
 */


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import javax.xml.transform.stream.StreamResult;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uk.co.encs.transformation.engine.config.ConfigEntityBean;
import uk.co.encs.transformation.engine.config.ConfigFileReader;
import uk.co.encs.transformation.engine.transformer.DocumentTransformer;


/**
 * @author Chris Keeley ECS http://www.encs.co.uk
 */
public class TestDocumentTransformer extends TestCase {
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * Class constructor
	 * @param test
	 */
	public TestDocumentTransformer(final String test) {
		super(test);
	}
	/**
	 * Instantiate a new test suites
	 * @return the current test suite
	 */
	public static Test suite( ) {
		TestSuite suite = new TestSuite();
		suite.addTest(new TestDocumentTransformer("testConfigFileReader"));
		suite.addTest(new TestDocumentTransformer("testDocumentTransformer"));
		return suite;
	}
	/**
	 * TODO finish this test
	 * Test reading of the config file and setting of config values in the config bean
	 * This method simulates the functionality of the config file reader with respect to populating 
	 * the config bean with the values read from the config file
	 */
	public void testConfigFileReader() {
		/**
		 * init the reader and the config file bean to hold the values.
		 */
		ConfigFileReader reader = new ConfigFileReader();
		ConfigEntityBean configBean = new ConfigEntityBean();
		/**
		 * init the config file reader with a path to the config file
		 */
		reader.readConfigFile("config/txengine.xml");
		Document configFile  = reader.getDocument();
		/**
		 * Get an element by name from the config file.
		 */
		NodeList loglevel = configFile.getElementsByTagName("loglevel");
		NodeList parsers = configFile.getElementsByTagName("parser");
		/**
		 * set the log level obtained from the config file
		 */ 
		configBean.setLogLevel(loglevel.item(0).getTextContent());
		this.logger.debug("Getting loglevel from config bean: " + configBean.getLogLevel());
		Hashtable<String, String> parserTable = new Hashtable<String, String>();
		/**
		 * push each found parser id into the config bean
		 */
		for(int ix = 0; ix < parsers.getLength(); ix++) {
			Node attr = parsers.item(ix);
			parserTable.put(attr.getAttributes().getNamedItem("type").getTextContent(), attr.getTextContent());
			this.logger.debug("Setting available parser: "+attr.getTextContent());
		}
		configBean.setParsers(parserTable);
	}
	/**
	 * Test the document transformer 
	 */
	public void testDocumentTransformer() {
		int counter = 0;
		/**
		 * specify the input file
		 */
		String document = new String("input/random.csv");
		this.logger.info("Using input file: " + document);
		/**
		 * the transformer itself
		 */
		DocumentTransformer transformer = new DocumentTransformer();
		/**
		 * transform the document
		 */
		transformer.transformDocument(document);
		/**
		 * get the resulting data
		 */
		StreamResult result = transformer.getStreamResult();
		/**
		 * get a string from the stream result
		 */
		String data = result.getOutputStream().toString();
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter("output/test.xml"));
			output.write(data);
			output.close();
			this.logger.info("Generated intermediate XML schema: " + data);
		} catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			counter++;
		}
	}
}
