/**
 * Enterprise Computer Solutions 
 * http://www.encs.co.uk 
 */
package uk.co.encs.transformation.engine.parser;

import java.io.IOException;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 * @author Chris Keeley ECS http://www.encs.co.uk
 * This interface defines the contract between the reader and the parser.
 * The InputSource may be passed in as a variety of stream types and 
 * it is therefore recommended that a method be implemented to retrieve the relevant 
 * <code>BufferedReader</code> from the InputSource.
 */
public interface DocumentParser {
	/**
	 * Parse the input source file assigning each token to either a start or end element in the content handler.
	 * The low level class InputSource allows for a high degree of flexibility when passing in the source.
	 * @param inputSource 
	 * @param contentHandler 
	 * @throws IOException 
	 * @throws SAXException
	 */
	public void parseDocument(InputSource inputSource, ContentHandler contentHandler) throws IOException, SAXException;
}
