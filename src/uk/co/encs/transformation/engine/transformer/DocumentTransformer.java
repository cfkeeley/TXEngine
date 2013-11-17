/**
 * Enterprise Computer Solutions 
 * http://www.encs.co.uk 
 */
package uk.co.encs.transformation.engine.transformer;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import uk.co.encs.parser.csv.CsvParser;
import uk.co.encs.transformation.engine.parser.DocumentParser;
import uk.co.encs.transformation.engine.reader.DocumentReader;

/**
 * @author Chris Keeley ECS http://www.encs.co.uk
 *
 */
public class DocumentTransformer {
	
	private Logger logger = Logger.getLogger(this.getClass().toString());
	private StreamResult outputStream;
	
	/**
	 * Class constructor
	 */
	public DocumentTransformer() {
		// do nothing
	}
	
	/**
	 * Parse document into the intermediate XML format.
	 * @param documentName
	 */
	public void transformDocument(String documentName) {
		final TransformerHandler transformerHandler = this.getTransformerHandler();
		if(transformerHandler != null) {
			this.outputStream = new StreamResult(new ByteArrayOutputStream());
			transformerHandler.setResult(this.outputStream);
			DocumentParser parser = new CsvParser();
			DocumentReader reader = new DocumentReader(parser);
			try {
				InputSource csvSource = new InputSource(new FileReader(documentName));
				reader.setContentHandler(transformerHandler);
				reader.parse(csvSource);
			} catch (FileNotFoundException ex) {
				this.logger.error("File not found: "+documentName);
			} catch (IOException ex) {
				this.logger.error("Problem encountered during I/O on file: "+documentName);
				ex.printStackTrace();
			} catch (SAXException ex) {
				this.logger.error("Problem encountered during SAX Parse on file: "+documentName);
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * This method returns the StreamResult object for the transformation handler
	 * @return the stream result for the transformation
	 */
	public StreamResult getStreamResult() {
		return this.outputStream;
	}
	
	/**
	 * Get a handler on the TransformerHandler
	 * @return a handle on the transformation handler
	 */
	private TransformerHandler getTransformerHandler()
	{
		final TransformerFactory transformerFactory = TransformerFactory.newInstance();
		TransformerHandler transformerHandler = null;			
	    if (transformerFactory.getFeature(SAXTransformerFactory.FEATURE)) {  
	    	final SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory)transformerFactory;
	    	try {
				transformerHandler = saxTransformerFactory.newTransformerHandler();
			} catch (TransformerConfigurationException ex) {
				//	TODO deal with TransformerConfigurationException
				ex.printStackTrace();
			}
	    }
	return transformerHandler;
	}
}
