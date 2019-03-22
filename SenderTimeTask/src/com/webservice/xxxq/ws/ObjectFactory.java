
package com.webservice.xxxq.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.webservice.xxxq.ws package. 
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

    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.webservice.xxxq.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SaveYCJC }
     * 
     */
    public SaveYCJC createSaveYCJC() {
        return new SaveYCJC();
    }

    /**
     * Create an instance of {@link SaveYCJCResponse }
     * 
     */
    public SaveYCJCResponse createSaveYCJCResponse() {
        return new SaveYCJCResponse();
    }

    /**
     * Create an instance of {@link SaveYCJCArray }
     * 
     */
    public SaveYCJCArray createSaveYCJCArray() {
        return new SaveYCJCArray();
    }

    /**
     * Create an instance of {@link ArrayOfArrayOfString }
     * 
     */
    public ArrayOfArrayOfString createArrayOfArrayOfString() {
        return new ArrayOfArrayOfString();
    }

    /**
     * Create an instance of {@link SaveYCJCArrayResponse }
     * 
     */
    public SaveYCJCArrayResponse createSaveYCJCArrayResponse() {
        return new SaveYCJCArrayResponse();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
