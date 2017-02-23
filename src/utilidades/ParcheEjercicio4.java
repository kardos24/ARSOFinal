package utilidades;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Scanner;

public class ParcheEjercicio4 {

	public static String retornarXML(String urlEmisiones) throws Exception {

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

		return reader.toString();

		// El objeto reader puede ser el parámetro del método build del
		// analizador DOM

	}

}
