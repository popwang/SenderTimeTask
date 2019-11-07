package com.webservice.lyln.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.3.1
 * 2019-09-02T11:23:33.133+08:00
 * Generated source version: 3.3.1
 *
 */
@WebServiceClient(name = "DustSurveillance",
                  wsdlLocation = "http://39.97.230.214/dust/api/DustSurveillance.asmx?wsdl",
                  targetNamespace = "http://tempuri.org/")
public class DustSurveillance extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "DustSurveillance");
    public final static QName DustSurveillanceSoap = new QName("http://tempuri.org/", "DustSurveillanceSoap");
    public final static QName DustSurveillanceSoap12 = new QName("http://tempuri.org/", "DustSurveillanceSoap12");
    static {
        URL url = null;
        try {
            url = new URL("http://39.97.230.214/dust/api/DustSurveillance.asmx?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(DustSurveillance.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://39.97.230.214/dust/api/DustSurveillance.asmx?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public DustSurveillance(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DustSurveillance(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DustSurveillance() {
        super(WSDL_LOCATION, SERVICE);
    }

    public DustSurveillance(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public DustSurveillance(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public DustSurveillance(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns DustSurveillanceSoap
     */
    @WebEndpoint(name = "DustSurveillanceSoap")
    public DustSurveillanceSoap getDustSurveillanceSoap() {
        return super.getPort(DustSurveillanceSoap, DustSurveillanceSoap.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DustSurveillanceSoap
     */
    @WebEndpoint(name = "DustSurveillanceSoap")
    public DustSurveillanceSoap getDustSurveillanceSoap(WebServiceFeature... features) {
        return super.getPort(DustSurveillanceSoap, DustSurveillanceSoap.class, features);
    }


    /**
     *
     * @return
     *     returns DustSurveillanceSoap
     */
    @WebEndpoint(name = "DustSurveillanceSoap12")
    public DustSurveillanceSoap getDustSurveillanceSoap12() {
        return super.getPort(DustSurveillanceSoap12, DustSurveillanceSoap.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DustSurveillanceSoap
     */
    @WebEndpoint(name = "DustSurveillanceSoap12")
    public DustSurveillanceSoap getDustSurveillanceSoap12(WebServiceFeature... features) {
        return super.getPort(DustSurveillanceSoap12, DustSurveillanceSoap.class, features);
    }

}