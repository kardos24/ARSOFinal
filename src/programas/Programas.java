//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.03.12 a las 08:52:55 PM CET 
//


package programas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="programa">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="url-programa" type="{http://www.example.org/ejercicio1-2}tipo_url"/>
 *                   &lt;element name="url-portada" type="{http://www.example.org/ejercicio1-2}tipo_url"/>
 *                   &lt;element name="emision" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                             &lt;element name="tiempo-emision" type="{http://www.example.org/ejercicio1-2}tipo_hora"/>
 *                             &lt;element name="url-emision" type="{http://www.example.org/ejercicio1-2}tipo_url"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="identificador" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "programa"
})
@XmlRootElement(name = "programas")
public class Programas {

    @XmlElement(required = true)
    protected Programas.Programa programa;

    /**
     * Obtiene el valor de la propiedad programa.
     * 
     * @return
     *     possible object is
     *     {@link Programas.Programa }
     *     
     */
    public Programas.Programa getPrograma() {
        return programa;
    }

    /**
     * Define el valor de la propiedad programa.
     * 
     * @param value
     *     allowed object is
     *     {@link Programas.Programa }
     *     
     */
    public void setPrograma(Programas.Programa value) {
        this.programa = value;
    }


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
     *         &lt;element name="url-programa" type="{http://www.example.org/ejercicio1-2}tipo_url"/>
     *         &lt;element name="url-portada" type="{http://www.example.org/ejercicio1-2}tipo_url"/>
     *         &lt;element name="emision" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *                   &lt;element name="tiempo-emision" type="{http://www.example.org/ejercicio1-2}tipo_hora"/>
     *                   &lt;element name="url-emision" type="{http://www.example.org/ejercicio1-2}tipo_url"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "emision"
    })
    public static class Programa {

        @XmlElement(required = true)
        protected String nombre;
        @XmlElement(name = "url-programa", required = true)
        protected String urlPrograma;
        @XmlElement(name = "url-portada", required = true)
        protected String urlPortada;
        @XmlElement(required = true)
        protected List<Programas.Programa.Emision> emision;
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
         * {@link Programas.Programa.Emision }
         * 
         * 
         */
        public List<Programas.Programa.Emision> getEmision() {
            if (emision == null) {
                emision = new ArrayList<Programas.Programa.Emision>();
            }
            return this.emision;
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
         *         &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}date"/>
         *         &lt;element name="tiempo-emision" type="{http://www.example.org/ejercicio1-2}tipo_hora"/>
         *         &lt;element name="url-emision" type="{http://www.example.org/ejercicio1-2}tipo_url"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "titulo",
            "fecha",
            "tiempoEmision",
            "urlEmision"
        })
        public static class Emision {

            @XmlElement(required = true)
            protected String titulo;
            @XmlElement(required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar fecha;
            @XmlElement(name = "tiempo-emision", required = true)
            protected String tiempoEmision;
            @XmlElement(name = "url-emision", required = true)
            protected String urlEmision;

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
             * Obtiene el valor de la propiedad fecha.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getFecha() {
                return fecha;
            }

            /**
             * Define el valor de la propiedad fecha.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setFecha(XMLGregorianCalendar value) {
                this.fecha = value;
            }

            /**
             * Obtiene el valor de la propiedad tiempoEmision.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTiempoEmision() {
                return tiempoEmision;
            }

            /**
             * Define el valor de la propiedad tiempoEmision.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTiempoEmision(String value) {
                this.tiempoEmision = value;
            }

            /**
             * Obtiene el valor de la propiedad urlEmision.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUrlEmision() {
                return urlEmision;
            }

            /**
             * Define el valor de la propiedad urlEmision.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUrlEmision(String value) {
                this.urlEmision = value;
            }

        }

    }

}
