package practicas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

		Pattern p = Pattern.compile("/m/alacarta/videos/(.+)/\\?media=tve");
		Matcher m;

		NodeList elementos = d.getElementsByTagName("li");

		// System.out.println(elementos.getLength());
		for (int i = 0; i < elementos.getLength(); i++) {

			NodeList nl = elementos.item(i).getChildNodes();

			// System.out.println(nl.getLength());
			for (int j = 0; j < nl.getLength(); j++) {
				Node n = nl.item(j);
				Element e;

				// System.out.println(n.getNodeName());

				if (n.getNodeName().equals("h3")) {
					System.out.println(n.getTextContent());
				} else if (n.getNodeName().equals("a")) {
					e = (Element) n;
					System.out.println(e.getAttribute("href"));

					m = p.matcher(e.getAttribute("href"));
					if (m.find()) {
						System.out.println(m.group(1));

						// Funcion que recoja todas las emisiones del programa
					}
				} else if (n.getNodeName().equals("p") && n.getFirstChild().getNodeName().equals("img")) {
					e = (Element) n.getFirstChild();
					System.out.println(e.getAttribute("src"));
				}
			}
			System.out.println();
		}

	}
}
