package ejercicio4;

public class ProductoDOM {
	private String asin;
	private String titulo;
	private String imagenPeque;
	private String imagenGrande;
	private double precioMin;
	private String url;

	public ProductoDOM(String asin, String titulo, String imagenPeque, String imagenGrande, double precioMin,
			String url) {
		this.asin = asin;
		this.titulo = titulo;
		this.imagenPeque = imagenPeque;
		this.imagenGrande = imagenGrande;
		this.precioMin = precioMin;
		this.url = url;
	}

	public String getAsin() {
		return asin;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getImagenPeque() {
		return imagenPeque;
	}

	public String getImagenGrande() {
		return imagenGrande;
	}

	public double getPrecioMin() {
		return precioMin;
	}

	public String getUrl() {
		return url;
	}
}
