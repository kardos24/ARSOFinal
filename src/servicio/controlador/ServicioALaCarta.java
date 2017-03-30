package servicio.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import ejercicio3.AnalizadorSAX;
import ejercicio3.ManejadorSAX;
import ejercicio3.ProgramaResultado;
import ejercicio4.AnalizadorDOM;
import modelo.Favoritos;
import modelo.ListadoProgramas;
import servicio.tipos.TipoEmision;
import servicio.tipos.TipoPrograma;
import servicio.utilidades.Utilidades;

public class ServicioALaCarta {

	private static final String XML_BD = "xml-bd";
	private static final String FOLDER_FAV = "Favoritos";

	// Bloque de codigo que comprueba si existen las carpetas xml y xml-bd
	// En caso que no existan, las crean.
	static {
		File[] folders = { new File("xml"), new File(XML_BD),
				new File(XML_BD + "/" + FOLDER_FAV) };

		for (File folder : folders) {
			if (!folder.exists())
				folder.mkdir();
		}
	}

	private static ServicioALaCarta instance;

	private Map<String, TipoPrograma> programList;

	private Unmarshaller unmarshallerProgram;
	private Unmarshaller unmarshallerFavoritos;

	private Marshaller marshallerProgram;
	private Marshaller marshallerFavoritos;

	private ServicioALaCarta() {
		try {
			JAXBContext contextP = JAXBContext.newInstance("servicio.tipos");
			unmarshallerProgram = contextP.createUnmarshaller();
			marshallerProgram = contextP.createMarshaller();
			marshallerProgram.setProperty("jaxb.formatted.output", true);
			marshallerProgram.setProperty("jaxb.schemaLocation",
					"http://www.example.org/ejercicio2 xml/ejercicio2.xsd");

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
		ManejadorSAX manager = AnalizadorSAX.getManagerAnalizeSAX(false);
		if (manager == null) {
			return new LinkedList<>();
		} else {
			return manager.getPrograma();
		}
	}

	public TipoPrograma getPrograma(String id) throws IllegalArgumentException,
			JAXBException, UnsupportedEncodingException, FileNotFoundException {
		File program = recuperarPrograma(id);

		// Para poner la codificacion UTF-8
		InputStream is = new FileInputStream(program);
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");

		// Object obj = unmarshallerProgram.unmarshal(isr);
		// return (TipoPrograma) obj;

		return (TipoPrograma) JAXBIntrospector.getValue(unmarshallerProgram
				.unmarshal(isr));
	}

	public String getProgramaAtom(String id) throws Exception {
		TipoPrograma program = getPrograma(id);
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformador = factoria.newTransformer(new StreamSource(
				"xml/ejercicio5.xsl"));

		StringWriter contenido = new StringWriter();

		JAXBSource origen = new JAXBSource(marshallerProgram, program);
		Result destino = new StreamResult(contenido);

		transformador.transform(origen, destino);

		return contenido.toString();
	}

	public TipoPrograma getProgramaFiltrado(String id, String titulo)
			throws IllegalArgumentException, JAXBException,
			UnsupportedEncodingException, FileNotFoundException {
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
		File file = new File(getPathProgram(id));
		// Si no existe se recupera
		// Si existe y es antiguo, mayor a un 1 día también se recupera
		if (!file.exists()
				|| new Date(file.lastModified()).before(Utilidades.ayer())) {
			AnalizadorDOM.getListProgramDOM(id);
			file = new File(getPathProgram(id));
			if (!file.exists()) {
				// error no existe el id o está mal introducido
				throw new IllegalArgumentException("El id no existe");
			}
		}
		return file;
	}

	// ///////////////////// GESTIÓN FAVORITOS /////////////////////////////
	public String crearFavoritos() throws JAXBException {
		Favoritos fav = new Favoritos();
		String id = UUID.randomUUID().toString();

		marshallerFavoritos.marshal(fav, new File(getPathFavoritos(id)));

		return id;
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
		return XML_BD + "/" + FOLDER_FAV + "/favoritos-" + id + ".xml";
	}

	private String getPathProgram(String id) {
		return XML_BD + "/" + id + ".xml";
	}

	public static void main(String[] args) throws Exception {
		ServicioALaCarta servicio = ServicioALaCarta.getInstance();

		List<ProgramaResultado> l = servicio.getListadoProgramas();

		System.out.println("Resultado del listado SAX:");
		for (ProgramaResultado programa : l) {
			System.out.println(programa.getTitulo() + " - " + programa.getId());
		}
		System.out.println();

		System.out.println("Listado de programas:");
		for (int i = 0; i < 5; i++) {
			TipoPrograma p = servicio.getPrograma(l.get(i).getId());

			System.out.println(p.getNombre());
			System.out.println("\tId: " + p.getIdentificador());
			System.out.println("\tURL Portada: " + p.getUrlPortada());
			System.out.println("\tURL Programa: " + p.getUrlPrograma());
			System.out.println("\tNum. emisiones: " + p.getEmision().size());
		}
		System.out.println();

		System.out.println("Listado de Atom del primer elemento:");
		System.out.println(servicio.getProgramaAtom(l.get(0).getId()));
		System.out.println();

		String idFav = servicio.crearFavoritos();

		servicio.addProgramaFavorito(idFav, "aguila-roja");
		servicio.addProgramaFavorito(idFav, "acacias-38");

		Favoritos f = servicio.getFavoritos(idFav);

		servicio.removeProgramaFavorito(idFav, "acacias-38");

		System.out.println("Manejo de favoritos:");
		for (ProgramaResultado prog : f.getProgramList()) {
			System.out.println(prog.getTitulo() + " - " + prog.getId());
		}
		System.out.println();
	}
}
