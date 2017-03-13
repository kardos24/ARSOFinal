package utilidades;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

public class PlantillaXSLTEjercicio6 {
	
	public void ejemploRecuperacionPlantilla() throws Exception {
		
		// En primer lugar, intentamos recuperar la plantilla de la raíz del archivo JAR
		// Funcionará si el código está empaquetado en un JAR
		
		URL recurso = getClass().getResource("/plantilla.xsl");
		
		InputStream is = null;
		
		if (recurso != null) // La ha encontrado en el JAR
			is = recurso.openStream(); 
		else // Está en el sistema de ficheros
			is = new FileInputStream("xml/plantilla.xsl"); 

		TransformerFactory factoria = TransformerFactory.newInstance();
		
		Transformer transformador = 
		   factoria.newTransformer(new StreamSource(is)); 
		
		// Establecer el origen y destino de la transformación ...
	}
	
	public static void main(String[] args) throws Exception {
		
		// El código de ejemplo se proporciona en un método de instancia (no static)
		// Por ese motivo, se construye un objeto
				
		PlantillaXSLTEjercicio6 ejemplo = new PlantillaXSLTEjercicio6();
		
		ejemplo.ejemploRecuperacionPlantilla();
	}

}
