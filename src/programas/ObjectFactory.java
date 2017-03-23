//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.03.21 a las 07:36:41 PM CET 
//


package programas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the programas package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Programa_QNAME = new QName("http://www.example.org/ejercicio1-2", "programa");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: programas
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoPrograma }
     * 
     */
    public TipoPrograma createTipoPrograma() {
        return new TipoPrograma();
    }

    /**
     * Create an instance of {@link TipoEmision }
     * 
     */
    public TipoEmision createTipoEmision() {
        return new TipoEmision();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoPrograma }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/ejercicio1-2", name = "programa")
    public JAXBElement<TipoPrograma> createPrograma(TipoPrograma value) {
        return new JAXBElement<TipoPrograma>(_Programa_QNAME, TipoPrograma.class, null, value);
    }

}
