package modelo;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"id","programList"})
@XmlRootElement(name = "favoritos")
public class Favoritos {

	@XmlAttribute(required = true)
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
