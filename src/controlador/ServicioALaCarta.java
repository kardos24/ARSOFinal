package controlador;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import modelo.Favoritos;
import modelo.ListadoProgramas;
import modelo.ProgramaResultado;
import procesadoresXML.ManejadorValidacion;
import procesadoresXML.ProgramaDOM;
import procesadoresXML.ProgramaSAX;
import programas.TipoEmision;
import programas.TipoPrograma;
import utilidades.Utils;

public class ServicioALaCarta {

	private static final String XML_BD = "xml-bd";

	private static ServicioALaCarta instance;

	private Map<String, TipoPrograma> programList;

	private Unmarshaller unmarshallerProgram;
	private Unmarshaller unmarshallerFavoritos;

	private Marshaller marshallerProgram;
	private Marshaller marshallerFavoritos;

	private ServicioALaCarta() {
		try {
			JAXBContext contextP = JAXBContext.newInstance("programas");
			unmarshallerProgram = contextP.createUnmarshaller();
			marshallerProgram = contextP.createMarshaller();
			marshallerProgram.setProperty("jaxb.formatted.output", true);
			marshallerProgram.setProperty("jaxb.schemaLocation",
					"http://www.um.es/as xml/ejercicio1-2.xsd");

			JAXBContext contextF = JAXBContext.newInstance(Favoritos.class);
			unmarshallerFavoritos = contextF.createUnmarshaller();
			marshallerFavoritos = contextF.createMarshaller();
			marshallerFavoritos.setProperty("jaxb.formatted.output", true);
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

	public List<ProgramaResultado> getListadoProgramas() {
		ManejadorValidacion manager = ProgramaSAX.getManagerAnalizeSAX(false);
		if (manager == null) {
			return new LinkedList<>();
		} else {
			return manager.getPrograma();
		}
	}

	public TipoPrograma getPrograma(String id) throws IllegalArgumentException,
			JAXBException {
		File program = recuperarPrograma(id);
		return (TipoPrograma) unmarshallerProgram.unmarshal(program);
	}

	public String getProgramaAtom(String id) throws Exception {
		TipoPrograma program = getPrograma(id);
		// FIXME Realizar transformación Atom

		return null;
	}

	public TipoPrograma getProgramaFiltrado(String id, String titulo)
			throws IllegalArgumentException, JAXBException {
		TipoPrograma programResult;
		programResult = getPrograma(id);
		Iterator<TipoEmision> it = programResult.getEmision().iterator();
		while (it.hasNext()) {
			String tit = it.next().getTitulo();
			if (tit.contains(titulo)) {
				it.remove();
			}
		}
		return programResult;
	}

	public ListadoProgramas getListadoProgramasXML() {
		return new ListadoProgramas(getListadoProgramas());
	}

	private File recuperarPrograma(String id) throws IllegalArgumentException {
		ckeckIfExistBBDD();
		File file = new File(getPathProgram(id));
		// Si no existe se recupera
		// Si existe y es antiguo, mayor a un 1 día también se recupera
		if (!file.exists()
				|| new Date(file.lastModified()).before(Utils.ayer())) {
			ProgramaDOM.getListProgramDOM(id);
			file = new File(getPathProgram(id));
			if (!file.exists()) {
				// error no existe el id o está mal introducido
				throw new IllegalArgumentException("El id no existe");
			}
		}
		return file;
	}

	private void ckeckIfExistBBDD() {
		File folder = new File(XML_BD);
		if (!folder.exists())
			folder.mkdir();

	}

	// ///////////////////// GESTIÓN FAVORITOS /////////////////////////////
	public String crearFavoritos() throws JAXBException {
		Favoritos fav = new Favoritos();
		marshallerFavoritos.marshal(fav,
				new File(getPathFavoritos(fav.getId())));
		return fav.getId();
	}

	public boolean addProgramaFavorito(String idFavoritos, String idPrograma)
			throws JAXBException {
		Favoritos fav = getFavoritos(idFavoritos);
		List<ProgramaResultado> listProgram = getListadoProgramas();
		Optional<ProgramaResultado> program = listProgram.stream()
				.filter(id -> id.getId().equals(idPrograma)).findAny();

		if (program.isPresent()) {
			Boolean addResult = fav.getProgramList().add(program.get());
			marshallerFavoritos.marshal(fav,
					new File(getPathFavoritos(fav.getId())));
			return addResult;
		} else {
			return false;
		}
	}

	public boolean removeProgramaFavorito(String idFavoritos, String idPrograma)
			throws JAXBException {
		Favoritos fav = getFavoritos(idFavoritos);
		List<ProgramaResultado> listProgram = getListadoProgramas();
		Optional<ProgramaResultado> program = listProgram.stream()
				.filter(id -> id.getId().equals(idPrograma)).findAny();

		if (program.isPresent()) {
			Boolean removeResult = fav.getProgramList().remove(program.get());
			marshallerFavoritos.marshal(fav,
					new File(getPathFavoritos(fav.getId())));
			return removeResult;
		} else {
			return false;
		}
	}

	public Favoritos getFavoritos(String idFavoritos) throws JAXBException {
		File fileFav = new File(getPathFavoritos(idFavoritos));
		if (fileFav.exists()) {
			return (Favoritos) unmarshallerFavoritos.unmarshal(fileFav);
		} else {
			throw new IllegalArgumentException("El id no existe");
		}
	}

	private String getPathFavoritos(String id) {
		return "favoritos-" + id + ".xml";
	}

	private String getPathProgram(String id) {
		return XML_BD + "/" + id + ".xml";
	}
}
