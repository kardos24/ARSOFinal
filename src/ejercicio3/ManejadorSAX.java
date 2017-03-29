package ejercicio3;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorSAX extends DefaultHandler {
	private List<SAXParseException> errores;
	private List<ProgramaResultado> programaList;
	private ProgramaResultado programaTemp;

	public ManejadorSAX() {
		errores = new LinkedList<>();
		programaList = new LinkedList<>();
	}

	@Override
	public void startDocument() throws SAXException {
		errores.clear();
		programaList.clear();
		programaTemp = null;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("li")) {
			programaTemp = new ProgramaResultado();
		} else if (qName.equals("img")) {
			programaTemp.setTitulo(attributes.getValue("alt"));
		} else if (qName.equals("a")) {
			programaTemp.setId(attributes.getValue("href").split("/")[4]);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("li")) {
			programaList.add(programaTemp);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		if (!errores.isEmpty()) {
			System.out.println("El documento contiene " + errores.size()
					+ " error(es):");
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

	public List<ProgramaResultado> getPrograma() {
		return programaList;
	}
}
