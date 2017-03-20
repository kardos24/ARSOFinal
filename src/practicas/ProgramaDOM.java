package practicas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modelo.EmisionDOM;
import utilidades.ParcheEjercicio4;
import utilidades.Utils;

public class ProgramaDOM {
	public static void main(String[] args) throws Exception {
		getListProgramDOM();
	}

	public static void getListProgramDOM() {
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder analizador;
			analizador = factoria.newDocumentBuilder();

			Document document = analizador
					.parse("http://www.rtve.es/m/alacarta/programsbychannel/?media=tve&channel=la1&modl=canales&filterFindPrograms=todas");

			// Pattern pattern =
			// Pattern.compile("/m/alacarta/videos/(.+)/\\?media=tve");
			// Matcher matcher;

			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter writer;

			NodeList elementos = document.getElementsByTagName("li");

			// XXX He puesto que el for solo recorra los primeros 5 elementos
			// del documento

			// for (int i = 0; i < elementos.getLength(); i++) {
			for (int i = 0; i < 5; i++) {
				NodeList nl = elementos.item(i).getChildNodes();

				List<EmisionDOM> emisiones = null;
				String identificador = null, nombre = null, urlPrograma = null, urlImagen = null;

				for (int j = 0; j < nl.getLength(); j++) {
					Node n = nl.item(j);
					Element e;

					if (n.getNodeName().equals("h3")) {
						nombre = n.getTextContent();
					} else if (n.getNodeName().equals("a")) {
						e = (Element) n;
						urlPrograma = e.getAttribute("href");

						identificador = urlPrograma.split("/")[4];
						emisiones = getBroadcastFromChannel(identificador, 1);

						// matcher = pattern.matcher(e.getAttribute("href"));

						/*
						 * if (matcher.find()) {
						 * System.out.println(matcher.group(1));
						 * 
						 * identificador = matcher.group(1); emisiones =
						 * getBroadcastFromChannel(identificador, 1); }
						 */
					} else if (n.getNodeName().equals("p")
							&& n.getFirstChild().getNodeName().equals("img")) {
						e = (Element) n.getFirstChild();
						urlImagen = e.getAttribute("src");
					}
				}

				writer = xof.createXMLStreamWriter(new FileOutputStream(
						"xml-bd/" + identificador + ".xml"));

				writer.writeStartDocument();
				writer.writeStartElement("programa");

				writer.writeAttribute("identificador", identificador);

				writer.writeStartElement("nombre");
				writer.writeCharacters(nombre);
				writer.writeEndElement(); // nombre

				writer.writeStartElement("url-programa");
				writer.writeCharacters(urlPrograma);
				writer.writeEndElement(); // url-programa

				writer.writeStartElement("url-portada");
				writer.writeCharacters(urlImagen);
				writer.writeEndElement(); // url-portada

				for (EmisionDOM e : emisiones) {
					writer.writeStartElement("emision");

					writer.writeStartElement("titulo");
					writer.writeCharacters(e.getTitulo());
					writer.writeEndElement(); // titulo

					writer.writeStartElement("fecha");
					writer.writeCharacters(Utils.convertirFechaTexto(e
							.getFecha()));
					writer.writeEndElement(); // fecha

					writer.writeStartElement("tiempo-emision");
					writer.writeCharacters(e.getDuracion().trim());
					writer.writeEndElement(); // tiempo-emision

					writer.writeStartElement("url-emision");
					writer.writeCharacters(e.getUrl());
					writer.writeEndElement(); // url-emision

					writer.writeEndElement(); // emision
				}

				writer.writeEndElement(); // programa
				writer.writeEndDocument();

				writer.close();
			}

		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		} catch (XMLStreamException e1) {
			e1.printStackTrace();
		}

	}

	private static List<EmisionDOM> getBroadcastFromChannel(String idChannel) {
		List<EmisionDOM> channelListResult = new LinkedList<>();
		AtomicInteger i = new AtomicInteger(1);
		List<EmisionDOM> channelListPage = new LinkedList<>();
		do {
			channelListPage.clear();
			channelListPage.addAll(getBroadcastFromChannel(idChannel,
					i.getAndIncrement()));

		} while (!channelListPage.isEmpty());

		return channelListResult;
	}

	private static List<EmisionDOM> getBroadcastFromChannel(String idChannel,
			Integer numPage) {
		List<EmisionDOM> emisionList = new LinkedList<>();
		try {
			if (numPage == null)
				numPage = 1;

			DocumentBuilderFactory factoria = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder analizador;
			analizador = factoria.newDocumentBuilder();

			Document document = analizador
					.parse(ParcheEjercicio4
							.retornarXML("http://www.rtve.es/m/alacarta/videos/"
									+ idChannel
									+ "/multimedialist_pag.shtml/?media=tve&contentKey=&programName="
									+ idChannel
									+ "&media=tve&paginaBusqueda="
									+ numPage));

			NodeList elementos = document.getElementsByTagName("li");

			String titulo = null, duracion = null, url = null;
			Date fecha = null;

			for (int i = 0; i < elementos.getLength(); i++) {

				NodeList nl = elementos.item(i).getChildNodes();

				for (int j = 0; j < nl.getLength(); j++) {
					Node n = nl.item(j);
					Element e;
					if (n.getNodeName().equals("h4")) {
						titulo = n.getTextContent();
					} else if (n.getNodeName().equals("p")) {
						String[] text = n.getTextContent().split("-");
						for (String s : text) {
							s.trim();
						}

						duracion = text[1];

						if (text[0].startsWith("hoy")) {
							fecha = new Date();
						} else if (text[0].startsWith("ayer")) {
							fecha = Utils.ayer();
						} else if (text[0].startsWith("pasado")) {
							int referencia;
							switch (text[0].split(" ")[1]) {
							case "lunes":
								referencia = Calendar.MONDAY;
								break;
							case "martes":
								referencia = Calendar.TUESDAY;
								break;
							case "miercoles":
								referencia = Calendar.WEDNESDAY;
								break;
							case "jueves":
								referencia = Calendar.THURSDAY;
								break;
							case "viernes":
								referencia = Calendar.FRIDAY;
								break;
							case "sabado":
								referencia = Calendar.SATURDAY;
								break;
							case "domingo":
								referencia = Calendar.SUNDAY;
								break;
							default:
								referencia = 0;
							}
							fecha = Utils.calcularFecha(referencia);
						} else {
							fecha = Utils.convertirTextoFecha(text[0]);
						}
					} else if (n.getNodeName().equals("a")) {
						e = (Element) n;
						url = e.getAttribute("href");
					}
				}

				emisionList.add(new EmisionDOM(titulo, fecha, duracion, url));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedList<>();
		}

		return emisionList;
	}
}
