package practicas;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modelo.Emision;
import utilidades.ParcheEjercicio4;

public class ProgramaDOM {
	public static void main(String[] args) throws Exception {
		getListProgramDOM();
	}

	public static void getListProgramDOM() {
		try {

			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

			DocumentBuilder analizador;
			analizador = factoria.newDocumentBuilder();

			Document document = analizador.parse(
					"http://www.rtve.es/m/alacarta/programsbychannel/?media=tve&channel=la1&modl=canales&filterFindPrograms=todas");

			Pattern pattern = Pattern.compile("/m/alacarta/videos/(.+)/\\?media=tve");
			Matcher matcher;

			NodeList elementos = document.getElementsByTagName("li");

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

						matcher = pattern.matcher(e.getAttribute("href"));
						if (matcher.find()) {
							System.out.println(matcher.group(1));

							// Funcion que recoja todas las emisiones del
							List<Emision> channels = getBroadcastFromChannel(matcher.group(1), 1);
						}
					} else if (n.getNodeName().equals("p") && n.getFirstChild().getNodeName().equals("img")) {
						e = (Element) n.getFirstChild();
						System.out.println(e.getAttribute("src"));
					}
				}
				System.out.println();
			}
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		}

	}

	private static List<Emision> getBroadcastFromChannel(String idChannel, Integer numPage) {

		List<Emision> emisionList = new LinkedList<>();
		try {
			if (numPage == null)
				numPage = 1;

			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

			DocumentBuilder analizador;
			analizador = factoria.newDocumentBuilder();
			Document document = analizador.parse(ParcheEjercicio4.retornarXML("http://www.rtve.es/m/alacarta/videos/"
					+ idChannel + "/multimedialist_pag.shtml/?media=tve&contentKey=&programName=" + idChannel
					+ "&media=tve&paginaBusqueda=" + numPage));

			NodeList elementos = document.getElementsByTagName("li");
			for (int i = 0; i < elementos.getLength(); i++) {
				Emision emision = new Emision();

				NodeList nl = elementos.item(i).getChildNodes();

				for (int j = 0; j < nl.getLength(); j++) {
					Node n = nl.item(j);
					Element e;
					if (n.getNodeName().equals("h3")) {
						// NO es necesario ya lo tenemos
					} else if (n.getNodeName().equals("h4")) {
						// Capitulo
					} else if (n.getNodeName().equals("p")) {
						// fecha
					} else if (n.getNodeName().equals("a")) {
						// url con el capitulo
					}
				}
				
				emisionList.add(emision);
			}
		} catch (Exception e) {
			return new LinkedList<>();
		}

		return emisionList;
	}
}
