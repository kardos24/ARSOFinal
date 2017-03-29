package ejercicio4;

import java.util.Date;

public class EmisionDOM {
	private String titulo;
	private Date fecha;
	private String duracion;
	private String url;

	public EmisionDOM(String titulo, Date fecha, String duracion, String url) {
		this.titulo = titulo;
		this.fecha = fecha;
		this.duracion = duracion;
		this.url = url;
	}

	public String getTitulo() {
		return titulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getDuracion() {
		return duracion;
	}

	public String getUrl() {
		return url;
	}
}
