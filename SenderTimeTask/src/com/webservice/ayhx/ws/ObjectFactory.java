
package com.webservice.ayhx.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.my.hxwebservice package. 
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

    private final static QName _DataAccessException_QNAME = new QName("http://service.tblycjc.webservice.client.dekn.com.cn", "DataAccessException");
    private final static QName _DataAccessExceptionCause_QNAME = new QName("http://exception.common.dekn.com.cn", "cause");
    private final static QName _DataAccessExceptionDefine_QNAME = new QName("http://exception.common.dekn.com.cn", "define");
    private final static QName _DataAccessExceptionMessage_QNAME = new QName("http://exception.common.dekn.com.cn", "message");
    private final static QName _DataAccessExceptionPosition_QNAME = new QName("http://exception.common.dekn.com.cn", "position");
    private final static QName _DataAccessExceptionRealm_QNAME = new QName("http://exception.common.dekn.com.cn", "realm");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.my.hxwebservice
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
     * Create an instance of {@link DataAccessException }
     * 
     */
    public DataAccessException createDataAccessException() {
        return new DataAccessException();
    }

    /**
     * Create an instance of {@link Throwable }
     * 
     */
    public Throwable createThrowable() {
        return new Throwable();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataAccessException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.tblycjc.webservice.client.dekn.com.cn", name = "DataAccessException")
    public JAXBElement<DataAccessException> createDataAccessException(DataAccessException value) {
        return new JAXBElement<DataAccessException>(_DataAccessException_QNAME, DataAccessException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Throwable }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exception.common.dekn.com.cn", name = "cause", scope = DataAccessException.class)
    public JAXBElement<Throwable> createDataAccessExceptionCause(Throwable value) {
        return new JAXBElement<Throwable>(_DataAccessExceptionCause_QNAME, Throwable.class, DataAccessException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exception.common.dekn.com.cn", name = "define", scope = DataAccessException.class)
    public JAXBElement<String> createDataAccessExceptionDefine(String value) {
        return new JAXBElement<String>(_DataAccessExceptionDefine_QNAME, String.class, DataAccessException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exception.common.dekn.com.cn", name = "message", scope = DataAccessException.class)
    public JAXBElement<String> createDataAccessExceptionMessage(String value) {
        return new JAXBElement<String>(_DataAccessExceptionMessage_QNAME, String.class, DataAccessException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exception.common.dekn.com.cn", name = "position", scope = DataAccessException.class)
    public JAXBElement<String> createDataAccessExceptionPosition(String value) {
        return new JAXBElement<String>(_DataAccessExceptionPosition_QNAME, String.class, DataAccessException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exception.common.dekn.com.cn", name = "realm", scope = DataAccessException.class)
    public JAXBElement<String> createDataAccessExceptionRealm(String value) {
        return new JAXBElement<String>(_DataAccessExceptionRealm_QNAME, String.class, DataAccessException.class, value);
    }

}
