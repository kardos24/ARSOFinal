package controlador;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import practicas.ManejadorValidacion;
import practicas.ProgramaSAX;
import programas.TipoPrograma;

public class ServicioALaCarta {

	private static ServicioALaCarta instance;

	private Map<String, TipoPrograma> programList;

	private Unmarshaller unmarshaller;

	private Marshaller marshaller;

	private ServicioALaCarta() {
		try {
			JAXBContext contexto = JAXBContext.newInstance("programas");
			unmarshaller = contexto.createUnmarshaller();
			marshaller = contexto.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			marshaller.setProperty("jaxb.schemaLocation","http://www.um.es/as xml/ejercicio1-2.xsd");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		programList = new HashMap<>();
	}

	public static ServicioALaCarta getInstance() {
		if (instance == null) {
			instance = new ServicioALaCarta();
		}
		return instance;
	}

	public List<String> getListadoProgramas() {
		ManejadorValidacion manager = ProgramaSAX.getManagerAnalizeSAX(false);
		if (manager == null) {
			return new LinkedList<>();
		} else {
			return manager.getTitulo();
		}
	}

	public TipoPrograma getPrograma(String id) throws JAXBException {
			return (TipoPrograma) unmarshaller.unmarshal(new File("xml-bd/"+id+".xml"));
	}

	public String getProgramaAtom(String id) throws JAXBException {
		TipoPrograma program = getPrograma(id);	//FIXME Realizar transformación Atom

		return null;
	}

}
