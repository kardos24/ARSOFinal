package modelo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "listProgramas")
public class ListadoProgramas {

	private List<ProgramaResultado> programList;

	public ListadoProgramas(List<ProgramaResultado> programList) {
		super();
		this.programList = programList;
	}

}
