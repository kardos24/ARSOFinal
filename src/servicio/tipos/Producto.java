//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.04.29 a las 06:55:04 PM CEST 
//


package servicio.tipos;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para producto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="producto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url-imagen-peque" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="url-imagen-grande" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="precio-mas-bajo" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="url-informacion" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *       &lt;/sequence>
 *       &lt;attribute name="asin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "producto", propOrder = {
    "titulo",
    "urlImagenPeque",
    "urlImagenGrande",
    "precioMasBajo",
    "urlInformacion"
})
public class Producto {

    @XmlElement(required = true)
    protected String titulo;
    @XmlElement(name = "url-imagen-peque", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String urlImagenPeque;
    @XmlElement(name = "url-imagen-grande", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String urlImagenGrande;
    @XmlElement(name = "precio-mas-bajo", required = true)
    protected BigDecimal precioMasBajo;
    @XmlElement(name = "url-informacion", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String urlInformacion;
    @XmlAttribute(name = "asin", required = true)
    protected String asin;

    /**
     * Obtiene el valor de la propiedad titulo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define el valor de la propiedad titulo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

    /**
     * Obtiene el valor de la propiedad urlImagenPeque.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlImagenPeque() {
        return urlImagenPeque;
    }

    /**
     * Define el valor de la propiedad urlImagenPeque.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlImagenPeque(String value) {
        this.urlImagenPeque = value;
    }

    /**
     * Obtiene el valor de la propiedad urlImagenGrande.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlImagenGrande() {
        return urlImagenGrande;
    }

    /**
     * Define el valor de la propiedad urlImagenGrande.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlImagenGrande(String value) {
        this.urlImagenGrande = value;
    }

    /**
     * Obtiene el valor de la propiedad precioMasBajo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrecioMasBajo() {
        return precioMasBajo;
    }

    /**
     * Define el valor de la propiedad precioMasBajo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrecioMasBajo(BigDecimal value) {
        this.precioMasBajo = value;
    }

    /**
     * Obtiene el valor de la propiedad urlInformacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlInformacion() {
        return urlInformacion;
    }

    /**
     * Define el valor de la propiedad urlInformacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlInformacion(String value) {
        this.urlInformacion = value;
    }

    /**
     * Obtiene el valor de la propiedad asin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsin() {
        return asin;
    }

    /**
     * Define el valor de la propiedad asin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsin(String value) {
        this.asin = value;
    }

}
