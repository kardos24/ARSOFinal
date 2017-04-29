//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.04.29 a las 06:55:04 PM CEST 
//


package servicio.tipos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url-programa" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="url-portada" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="emision" type="{http://www.example.org/programacionRTVE}emision" maxOccurs="unbounded"/>
 *         &lt;element name="producto" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="identificador" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nombre",
    "urlPrograma",
    "urlPortada",
    "emision",
    "producto"
})
@XmlRootElement(name = "programa")
public class Programa {

    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(name = "url-programa", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String urlPrograma;
    @XmlElement(name = "url-portada", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String urlPortada;
    @XmlElement(required = true)
    protected List<Emision> emision;
    protected List<Object> producto;
    @XmlAttribute(name = "identificador", required = true)
    protected String identificador;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad urlPrograma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlPrograma() {
        return urlPrograma;
    }

    /**
     * Define el valor de la propiedad urlPrograma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlPrograma(String value) {
        this.urlPrograma = value;
    }

    /**
     * Obtiene el valor de la propiedad urlPortada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlPortada() {
        return urlPortada;
    }

    /**
     * Define el valor de la propiedad urlPortada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlPortada(String value) {
        this.urlPortada = value;
    }

    /**
     * Gets the value of the emision property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emision property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmision().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Emision }
     * 
     * 
     */
    public List<Emision> getEmision() {
        if (emision == null) {
            emision = new ArrayList<Emision>();
        }
        return this.emision;
    }

    /**
     * Gets the value of the producto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the producto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProducto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getProducto() {
        if (producto == null) {
            producto = new ArrayList<Object>();
        }
        return this.producto;
    }

    /**
     * Obtiene el valor de la propiedad identificador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Define el valor de la propiedad identificador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificador(String value) {
        this.identificador = value;
    }

}
