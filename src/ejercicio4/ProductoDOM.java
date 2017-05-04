package ejercicio4;

public class ProductoDOM {
	private String asin;
	private String titulo;
	private String url;
	private String imagenPeque;
	private String imagenGrande;
	private Double precioMin;

	public ProductoDOM(String asin, String titulo, String url) {
		this.asin = asin;
		this.titulo = titulo;
		this.url = url;
	}

	public String getAsin() {
		return asin;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUrl() {
		return url;
	}

	public String getImagenPeque() {
		return imagenPeque;
	}

	public void setImagenPeque(String imagenPeque) {
		this.imagenPeque = imagenPeque;
	}

	public String getImagenGrande() {
		return imagenGrande;
	}

	public void setImagenGrande(String imagenGrande) {
		this.imagenGrande = imagenGrande;
	}

	public Double getPrecioMin() {
		return precioMin;
	}

	public void setPrecioMin(double precioMin) {
		this.precioMin = precioMin;
	}

}
