package xslt;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformadorXSLT {
	public static void main(String[] args) throws Exception {
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformador = factoria.newTransformer(new StreamSource("xml/ejercicio1-5.xsl"));

		System.out.println("Transformando!");
		Source origen = new StreamSource("xml/ejercicio1-2.xml");
		Result destino = new StreamResult("xml/ejercicio1-5.xml");

		transformador.transform(origen, destino);
	}
}
