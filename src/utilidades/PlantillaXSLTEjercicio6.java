package utilidades;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

public class PlantillaXSLTEjercicio6 {
	
	public void ejemploRecuperacionPlantilla() throws Exception {
		
		// En primer lugar, intentamos recuperar la plantilla de la ra�z del archivo JAR
		// Funcionar� si el c�digo est� empaquetado en un JAR
		
		URL recurso = getClass().getResource("/plantilla.xsl");
		
		InputStream is = null;
		
		if (recurso != null) // La ha encontrado en el JAR
			is = recurso.openStream(); 
		else // Est� en el sistema de ficheros
			is = new FileInputStream("xml/plantilla.xsl"); 

		TransformerFactory factoria = TransformerFactory.newInstance();
		
		Transformer transformador = 
		   factoria.newTransformer(new StreamSource(is)); 
		
		// Establecer el origen y destino de la transformaci�n ...
	}
	
	public static void main(String[] args) throws Exception {
		
		// El c�digo de ejemplo se proporciona en un m�todo de instancia (no static)
		// Por ese motivo, se construye un objeto
				
		PlantillaXSLTEjercicio6 ejemplo = new PlantillaXSLTEjercicio6();
		
		ejemplo.ejemploRecuperacionPlantilla();
	}

}
