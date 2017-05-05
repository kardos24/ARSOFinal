package ejercicio4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import servicio.utilidades.Utilidades;
import servicio.utilidades.EspacioNombreAmazon;
import servicio.utilidades.SignedRequestsHelper;

import static servicio.utilidades.Constantes.*;

public class AnalizadorDOM {
	private static DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

	public static void main(String[] args) throws Exception {
		getListProgramDOM(5);
	}

	public static void getListProgramDOM() {
		getListProgramDOM(null, null);
	}

	public static void getListProgramDOM(Integer max) {
		getListProgramDOM(max, null);
	}

	public static void getListProgramDOM(String id) {
		getListProgramDOM(null, id);
	}

	private static void getListProgramDOM(Integer max, String id) {
		try {
			DocumentBuilder analizador;
			analizador = factoria.newDocumentBuilder();

			Document document = analizador.parse(
					"http://www.rtve.es/m/alacarta/programsbychannel/?media=tve&channel=la1&modl=canales&filterFindPrograms=todas");

			// Pattern pattern =
			// Pattern.compile("/m/alacarta/videos/(.+)/\\?media=tve");
			// Matcher matcher;

			NodeList elementos = document.getElementsByTagName("li");

			if (max == null || max > elementos.getLength()) {
				max = elementos.getLength();
			} else if (max <= 0) {
				max = 1;
			}

			// for (int i = 0; i < elementos.getLength(); i++) {
			// for (int i = 0; i < 5; i++) {
			for (int i = 0; i < max; i++) {
				NodeList nl = elementos.item(i).getChildNodes();

				List<EmisionDOM> emisiones = null;
				List<ProductoDOM> productos = null;
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
					} else if (n.getNodeName().equals("p") && n.getFirstChild().getNodeName().equals("img")) {
						e = (Element) n.getFirstChild();
						urlImagen = e.getAttribute("src");
					}
				}
				if (identificador != null && (id == null || Objects.equals(identificador, id))) {
					emisiones = getBroadcastFromChannel(identificador, 1);
					productos = getProductsFromAmazon(nombre);
					writeXMLWithStax(identificador, nombre, urlPrograma, urlImagen, emisiones, productos);
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException | XMLStreamException e1) {
			e1.printStackTrace();
		}

	}

	private static void writeXMLWithStax(String identificador, String nombre, String urlPrograma, String urlImagen,
			List<EmisionDOM> emisiones, List<ProductoDOM> productos) throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = xof.createXMLStreamWriter(new FileOutputStream("xml-bd/" + identificador + ".xml"));

		writer.writeStartDocument();
		writer.writeStartElement("programa");

		// > Espacio de nombres por omisión
		writer.writeNamespace("", "http://www.example.org/programacionRTVE");
		writer.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		writer.writeAttribute("http://www.w3.org/2001/XMLSchema-instance", "schemaLocation",
				"http://www.example.org/programacionRTVE programacionRTVE.xsd");

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
			writer.writeCharacters(Utilidades.convertirFechaTexto(e.getFecha()));
			writer.writeEndElement(); // fecha

			writer.writeStartElement("tiempo-emision");
			writer.writeCharacters(e.getDuracion().trim());
			writer.writeEndElement(); // tiempo-emision

			writer.writeStartElement("url-emision");
			writer.writeCharacters(e.getUrl());
			writer.writeEndElement(); // url-emision

			writer.writeEndElement(); // emision
		}

		for (ProductoDOM p : productos) {
			writer.writeStartElement("producto");

			writer.writeStartElement("titulo");
			writer.writeCharacters(p.getTitulo());
			writer.writeEndElement(); // titulo

			if (p.getImagenPeque() != null) {
				writer.writeStartElement("url-imagen-peque");
				writer.writeCharacters(p.getImagenPeque());
				writer.writeEndElement(); // url-imagen-peque
			}

			if (p.getImagenGrande() != null) {
				writer.writeStartElement("url-imagen-grande");
				writer.writeCharacters(p.getImagenGrande());
				writer.writeEndElement(); // url-imagen-grande
			}

			if (p.getPrecioMin() != null) {
				writer.writeStartElement("precio-mas-bajo");
				writer.writeCharacters(Double.toString(p.getPrecioMin()));
				writer.writeEndElement(); // precio-mas-bajo
			}

			writer.writeStartElement("url-informacion");
			writer.writeCharacters(p.getUrl());
			writer.writeEndElement(); // url-informacion

			writer.writeEndElement(); // producto
		}

		writer.writeEndElement(); // programa
		writer.writeEndDocument();

		writer.close();

	}

	private static List<EmisionDOM> getBroadcastFromChannel(String idChannel) {
		List<EmisionDOM> channelListResult = new LinkedList<>();
		AtomicInteger i = new AtomicInteger(1);
		List<EmisionDOM> channelListPage = new LinkedList<>();
		do {
			channelListPage.clear();
			channelListPage.addAll(getBroadcastFromChannel(idChannel, i.getAndIncrement()));
			channelListResult.addAll(channelListPage);

		} while (!channelListPage.isEmpty());

		return channelListResult;
	}

	private static List<EmisionDOM> getBroadcastFromChannel(String idChannel, Integer numPage) {
		List<EmisionDOM> emisionList = new LinkedList<>();
		try {
			if (numPage == null)
				numPage = 1;

			DocumentBuilder analizador;
			analizador = factoria.newDocumentBuilder();

			Document document = analizador.parse(Utilidades.retornarXML("http://www.rtve.es/m/alacarta/videos/"
					+ idChannel + "/multimedialist_pag.shtml/?media=tve&contentKey=&programName=" + idChannel
					+ "&media=tve&paginaBusqueda=" + numPage));

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
							fecha = Utilidades.ayer();
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
							fecha = Utilidades.calcularFecha(referencia);
						} else {
							fecha = Utilidades.convertirTextoFecha(text[0]);
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

	private static List<ProductoDOM> getProductsFromAmazon(String product) {
		List<ProductoDOM> productList = new LinkedList<>();
		SignedRequestsHelper helper;

		try {
			helper = SignedRequestsHelper.getInstance(AWS_ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("Service", "AWSECommerceService");
		params.put("AssociateTag", AWS_ASSOCIATE_TAG);
		params.put("Operation", "ItemSearch");
		params.put("Condition", "All");
		params.put("SearchIndex", "DVD");
		params.put("Title", product);
		params.put("ResponseGroup", "Large");

		String requestUrl = helper.sign(params);

		XPathFactory factoria = XPathFactory.newInstance();
		XPath xpath = factoria.newXPath();
		xpath.setNamespaceContext(new EspacioNombreAmazon());

		XPathExpression consulta;
		try {
			consulta = xpath.compile("//a:Items/a:Item");

			NodeList resultado = (NodeList) consulta.evaluate(new InputSource(requestUrl), XPathConstants.NODESET);
			String asin, titulo, imgPeque, imgGrande, url;
			double precio;

			for (int i = 0; i < resultado.getLength(); i++) {
				Element e;

				ProductoDOM p;

				consulta = xpath.compile("a:ASIN");
				e = (Element) consulta.evaluate(resultado.item(i), XPathConstants.NODE);
				asin = e.getTextContent();

				consulta = xpath.compile("a:ItemAttributes/a:Title");
				e = (Element) consulta.evaluate(resultado.item(i), XPathConstants.NODE);
				titulo = e.getTextContent();

				consulta = xpath.compile("a:DetailPageURL");
				e = (Element) consulta.evaluate(resultado.item(i), XPathConstants.NODE);
				url = e.getTextContent();

				p = new ProductoDOM(asin, titulo, url);

				consulta = xpath.compile("a:SmallImage/a:URL");
				e = (Element) consulta.evaluate(resultado.item(i), XPathConstants.NODE);

				if (e != null) {
					imgPeque = e.getTextContent();
					p.setImagenPeque(imgPeque);
				}

				consulta = xpath.compile("a:LargeImage/a:URL");
				e = (Element) consulta.evaluate(resultado.item(i), XPathConstants.NODE);

				if (e != null) {
					imgGrande = e.getTextContent();
					p.setImagenGrande(imgGrande);
				}

				consulta = xpath.compile("a:OfferSummary/a:LowestNewPrice/a:FormattedPrice");
				e = (Element) consulta.evaluate(resultado.item(i), XPathConstants.NODE);

				if (e != null) {
					precio = Double.parseDouble(e.getTextContent().split(" ")[1].replaceAll(",", "."));
					p.setPrecioMin(precio);
				}

				productList.add(p);
			}

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return productList;
	}
}
