package practicas;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ProgramaSAX {

	public static void main(String[] args) {
		getManagerAnalizeSAX(true);
	}

	public static ManejadorValidacion getManagerAnalizeSAX(boolean verbose) {
		try {
			SAXParserFactory factoria = SAXParserFactory.newInstance();

			SAXParser analizador = factoria.newSAXParser();

			// analizador.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
			// "http://www.w3.org/2001/XMLSchema");

			ManejadorValidacion manejador = new ManejadorValidacion();

			analizador.parse(
					"http://www.rtve.es/m/alacarta/programsbychannel/?media=tve&channel=la1&modl=canales&filterFindPrograms=todas",
					manejador);

			Pattern p = Pattern.compile("/m/alacarta/videos/(.+)/\\?media=tve");
			Matcher m;

			if (verbose) {
				System.out.println("Inicio analisis con SAX");
				for (int i = 0; i < manejador.getTitulo().size(); i++) {
					// m = p.matcher(manejador.getEnlace().get(i));
					// if (m.find()) {
					// System.out.println(m.group(1));
					// }
					System.out.println(manejador.getTitulo().get(i));
					System.out.println(manejador.getEnlace().get(i).split("/")[4]);
					System.out.println(manejador.getEnlace().get(i));
					System.out.println(manejador.getUrl().get(i));

					System.out.println();
				}
				System.out.println("Fin analisis con SAX");
			}

			return manejador;

		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Error sintactico: " + e.getMessage());
		}
		return null;
	}
}
