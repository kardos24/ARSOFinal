package practicas;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorValidacion extends DefaultHandler {

	private List<SAXParseException> errores;

	public ManejadorValidacion() {
		errores = new LinkedList<SAXParseException>();
	}

	@Override
	public void startDocument() throws SAXException {
		errores.clear();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		/*
		 * System.out.println(qName); for (int i = 0; i <
		 * attributes.getLength(); i++) { System.out.println("-> " +
		 * attributes.getLocalName(i) + ": " + attributes.getValue(i)); }
		 */

		if (qName.equals("img")) {
			System.out.println("Titulo: " + attributes.getValue("alt"));
			System.out.println("URL imagen: " + attributes.getValue("src"));
		} else if (qName.equals("a")) {
			System.out.println("Enlace: " + attributes.getValue("href"));
		}
	}

	@Override
	public void endDocument() throws SAXException {
		if (!errores.isEmpty()) {
			System.out.println("El documento contiene " + errores.size() + " error(es):");
			for (SAXParseException e : errores) {
				System.out.println("-> " + e.getMessage());
			}
		}
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		errores.add(e);
	}

	public List<SAXParseException> getErrores() {
		return errores;
	}
}
