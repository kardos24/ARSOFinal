package utilidades;

public class PlantillaEjercicio4 {

	public static void main(String[] args) throws Exception {

		final String urlListado = "http://www.rtve.es/m/alacarta/programsbychannel/?media=tve&channel=la1&modl=canales&filterFindPrograms=todas";

		final String id = "acacias-38";
		
		final String ficheroResultado = "xml/acacias-38-procesado.xml";
		
		// DOM: Obtener la factoría y el analizador (builder) 
		
		// StAX: Obtener un writer para generar el fichero resultado
		
		// StAX: escribir la cabecera del documento: elemento raíz, espacios de nombres, enlace con esquema, etc.
		
		/*** Parte 1: analizar el documento de listado de programas ***/
		
		// DOM: construir el árbol DOM del listado
				
		// DOM: analizar el árbol y extraer la información básica: id (no lo dan), nombre, url e imagen
		
		// StAX: escribir en el documento la información básica
						
		/*** Parte 2: analizar el documento con las emisiones del programa ***/
		
		// DOM: componer la URL con las emisiones (a partir del id)

		// Parche: añadir un elemento raíz al documento (se adjunta ayuda en el enunciado)
						
		// DOM: obtener el árbol DOM (del documento modificado en memoria)
				
		// DOM: para cada emisión:
			
			// DOM: extraer la información: título, fecha, tiempo y url 
		
			// StAX: escribir cada emisión en el documento XML
		
			
		// StAX: finalizar elemenento raíz y documento
		
		// StAX: cerrar el flujo de escritura
		
		
		System.out.println("fin.");
	}
}
