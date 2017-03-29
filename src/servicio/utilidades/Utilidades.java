package servicio.utilidades;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import org.xml.sax.InputSource;

public class Utilidades {

	/*
	 * El metodo retorna una fecha en el pasado haciendo referencia al día:
	 * pasado lunes, pasado martes, etc. El paametro referencia es una constante
	 * de Calendar: Calendar.SUNDAY, Calendar.MONDAY, etc.
	 */
	public static Date calcularFecha(int referencia) {

		Calendar calendar = Calendar.getInstance();
		int hoy = calendar.get(Calendar.DAY_OF_WEEK);

		int diferencia = hoy - referencia;

		if (diferencia > 0) {
			calendar.add(Calendar.DATE, -diferencia);
		} else {
			calendar.add(Calendar.DATE, -7 - diferencia);
		}
		return calendar.getTime();
	}

	public static Date ayer() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	// Ejemplo de patron: 30 ene 2017
	public static Date convertirTextoFecha(String fecha) throws ParseException {

		SimpleDateFormat formateador = new SimpleDateFormat("dd MMM yyyy",
				new Locale("es"));

		return formateador.parse(fecha);
	}

	// Salida en formato XML Schema
	public static String convertirFechaTexto(Date fecha) {

		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

		return formateador.format(fecha);
	}

	public static InputSource retornarXML(String urlEmisiones) throws Exception {

		// Abrimos la conexión con la URL indicando que la codificación de
		// caracteres es UTF-8
		URL url = new URL(urlEmisiones);
		InputStream is = url.openConnection().getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");

		Scanner scanner = new Scanner(isr);

		// Utilizamos StringWriter para componer en memoria el documento XML
		// bien formato
		StringWriter stringWriter = new StringWriter();

		stringWriter.append("<emisiones>");
		while (scanner.hasNextLine()) {

			stringWriter.append(scanner.nextLine());
		}
		stringWriter.append("</emisiones>");
		stringWriter.close();
		scanner.close();

		// Abrimos un flujo de lectura al documento en memoria
		StringReader reader = new StringReader(stringWriter.toString());

		InputSource source = new InputSource(reader);

		return source;
	}
}
