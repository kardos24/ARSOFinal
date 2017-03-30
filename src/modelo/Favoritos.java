package modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ejercicio3.ProgramaResultado;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"id","programList"})
@XmlRootElement(name = "favoritos")
public class Favoritos {

	@XmlAttribute(required = true)
	private String id;
	private List<ProgramaResultado> programList;
	
	public Favoritos(){
		id = UUID.randomUUID().toString();
//		programList = new LinkedList<>();
	}
	public Favoritos(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public List<ProgramaResultado> getProgramList() {
		if(programList == null){
			programList = new LinkedList<>();
		}
		return programList;
	}
	public void setProgramList(List<ProgramaResultado> programList) {
		this.programList = programList;
	}
	
	

}
