package controlador;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import practicas.ManejadorValidacion;
import practicas.ProgramaSAX;
import programas.Programas.Programa;

public class ServicioALaCarta {

	private static ServicioALaCarta instance;

	private Map<String, Programa> programList;

	private ServicioALaCarta() {
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

	public Programa getPrograma(String id) {
		return null;
	}

	public String getProgramaAtom(String id) {
		Programa program = getPrograma(id);

		return null;
	}

}
