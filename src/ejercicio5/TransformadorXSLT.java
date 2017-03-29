package ejercicio5;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformadorXSLT {
	public static void main(String[] args) throws Exception {
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformador = factoria.newTransformer(new StreamSource("xml/ejercicio5.xsl"));

		Source origen = new StreamSource("xml/ejercicio2.xml");
		Result destino = new StreamResult("xml/ejercicio5.xml");

		transformador.transform(origen, destino);
	}
}
