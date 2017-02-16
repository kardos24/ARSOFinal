package practicas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorValidacion extends DefaultHandler {

	private List<SAXParseException> errores = new LinkedList<SAXParseException>();
	private List<Double> notas = new LinkedList<Double>();

	private double notaMedia;
	private int diligencias;

	@Override
	public void startDocument() throws SAXException {
		errores.clear();
		notas.clear();
		diligencias = 0;
		notaMedia = 0;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		try {
			if (Objects.equals(qName, "diligencia")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fechaString = attributes.getValue("fecha");
				Date fecha = sdf.parse(fechaString);

				Calendar calendario = Calendar.getInstance();
				calendario.add(Calendar.DAY_OF_MONTH, -30);
				Date fecha30 = calendario.getTime();

				if (fecha30.before(fecha) && fecha.before(new Date())) {
					diligencias++;
				}
			} else if (Objects.equals(qName, "calificacion")) {
				String nota = attributes.getValue("nota");
				Double notaNum = Double.parseDouble(nota);
				notas.add(notaNum);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endDocument() throws SAXException {
		Double sumnota = 0.0;
		for(Double d : notas){
			sumnota += d;
		}
		notaMedia = sumnota / notas.size();
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		// System.out.println(e.getMessage());
		errores.add(e);
		// throw e; //detener analisis
	}

	public List<SAXParseException> getErrores() {
		return errores;
	}

	public int getDiligencias() {
		return diligencias;
	}

	public double getNotaMedia() {
		return notaMedia;
	}

}
