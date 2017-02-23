package practicas;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ProgramaSAX {

	public static void main(String[] args) {
		try {
			SAXParserFactory factoria = SAXParserFactory.newInstance();

			SAXParser analizador = factoria.newSAXParser();

			// analizador.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
			// "http://www.w3.org/2001/XMLSchema");

			ManejadorValidacion manejador = new ManejadorValidacion();

			analizador.parse(
					"http://www.rtve.es/m/alacarta/programsbychannel/?media=tve&channel=la1&modl=canales&filterFindPrograms=todas",
					manejador);

			for (SAXParseException error : manejador.getErrores()) {
				System.out.println("Error validación: " + error.getMessage());
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Error sintactico: " + e.getMessage());
		}
	}
}
