package practicas;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorValidacion extends DefaultHandler {

	private List<SAXParseException> errores;

	private List<String> titulo, url, enlace, idProgram;

	public ManejadorValidacion() {
		errores = new LinkedList<>();
		titulo = new LinkedList<>();
		url = new LinkedList<>();
		enlace = new LinkedList<>();
		idProgram = new LinkedList<>();
	}

	@Override
	public void startDocument() throws SAXException {
		errores.clear();
		titulo.clear();
		url.clear();
		enlace.clear();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		/*
		 * System.out.println(qName); for (int i = 0; i <
		 * attributes.getLength(); i++) { System.out.println("-> " +
		 * attributes.getLocalName(i) + ": " + attributes.getValue(i)); }
		 */

		if (qName.equals("img")) {
			titulo.add(attributes.getValue("alt"));
			url.add(attributes.getValue("src"));
		} else if (qName.equals("a")) {
			enlace.add(attributes.getValue("href"));
			idProgram.add(attributes.getValue("href").split("/")[4]);
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

	public List<String> getTitulo() {
		return titulo;
	}

	public List<String> getUrl() {
		return url;
	}

	public List<String> getEnlace() {
		return enlace;
	}

	public List<String> getIdPrograma() {
		return idProgram;
	}
}
