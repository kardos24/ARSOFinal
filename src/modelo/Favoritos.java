package modelo;

import java.util.List;
import java.util.UUID;

public class Favoritos {
	
	private String id;
	private List<ProgramaResultado> programList;
	
	public Favoritos(){
		id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public List<ProgramaResultado> getProgramList() {
		return programList;
	}
	public void setProgramList(List<ProgramaResultado> programList) {
		this.programList = programList;
	}
	
	

}
