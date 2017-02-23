package practicas;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ProgramaDOM {
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

		DocumentBuilder analizador = factoria.newDocumentBuilder();

		Document d = analizador.parse(
				"http://www.rtve.es/m/alacarta/programsbychannel/?media=tve&channel=la1&modl=canales&filterFindPrograms=todas");

		NodeList lista, elementos = d.getElementsByTagName("li");

		for (int i = 0; i < elementos.getLength(); i++) {

			Node n = elementos.item(i);

			if (n.getLocalName().equals("p") && n.hasChildNodes()) {
				// Este tiene contenido img, donde conseguimos el nombre y la
				// url de la imagen
			} else if (n.getLocalName().equals("a")) {
				// Este es el a, sacar identificador
			}
		}
	}
}
