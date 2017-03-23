package controlador;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import practicas.ManejadorValidacion;
import practicas.ProgramaDOM;
import practicas.ProgramaSAX;
import programas.TipoPrograma;
import utilidades.Utils;

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
			marshaller.setProperty("jaxb.schemaLocation", "http://www.um.es/as xml/ejercicio1-2.xsd");
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
		ckeckIfExistBBDD();
		File program = recuperarPrograma(id);
		return (TipoPrograma) unmarshaller.unmarshal(program);
	}
	
	private File recuperarPrograma(String id){
		File file = new File("xml-bd/" + id + ".xml");
		if(!file.exists()){
			ProgramaDOM.getListProgramDOM(id, null);
			file = new File("xml-bd/" + id + ".xml");
			if(!file.exists()){
				// error no existe el id o está mal introducido
			}
		}
		Date lastUpdate = new Date(file.lastModified());
		if(lastUpdate.before(Utils.ayer())){
			//actualizada
		}
		return file;
	}

	private void ckeckIfExistBBDD() {
		File folder = new File("xml-bd");
		if (!folder.exists())
			folder.mkdir();

	}

	public String getProgramaAtom(String id) throws JAXBException {
		TipoPrograma program = getPrograma(id); // FIXME Realizar transformación
												// Atom

		return null;
	}

}
