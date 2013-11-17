/**
 * Enterprise Computer Solutions 
 * http://www.encs.co.uk 
 */
package uk.co.encs.transformation.engine.reader;

import java.io.IOException;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import uk.co.encs.transformation.engine.parser.DocumentParser;

/**
 * @author Chris Keeley ECS http://www.encs.co.uk
 *
 */
public class DocumentReader extends Reader {
	
	private DocumentParser parser;
	
	/**
	 * Class constructor
	 * Assign a document parser to this document reader.
	 * @param parser
	 */
	public DocumentReader(DocumentParser parser) {
		this.setDocumentParser(parser);
	}
	
	/**
	 * Set the document parser
	 * @param parser
	 */
	private void setDocumentParser(DocumentParser parser) {
		this.parser = parser;
	}
	
	/**
	 * Pass the input source to the parser module.
	 * @param source the input source to parse
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public void parse(InputSource source) throws IOException, SAXException {
		ContentHandler ch = this.getContentHandler();
		parser.parseDocument(source, ch);
	}
}
