package com.webservice.xxxq.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.3.1
 * 2019-03-22T09:45:04.447+08:00
 * Generated source version: 3.3.1
 *
 */
@WebServiceClient(name = "YCJKService",
                  wsdlLocation = "http://xxxq.jyvjd.com/PM25/wservices/YCJKService.asmx?wsdl",
                  targetNamespace = "http://tempuri.org/")
public class YCJKService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "YCJKService");
    public final static QName YCJKServiceHttpGet = new QName("http://tempuri.org/", "YCJKServiceHttpGet");
    public final static QName YCJKServiceSoap = new QName("http://tempuri.org/", "YCJKServiceSoap");
    public final static QName YCJKServiceSoap12 = new QName("http://tempuri.org/", "YCJKServiceSoap12");
    public final static QName YCJKServiceHttpPost = new QName("http://tempuri.org/", "YCJKServiceHttpPost");
    static {
        URL url = null;
        try {
            url = new URL("http://xxxq.jyvjd.com/PM25/wservices/YCJKService.asmx?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(YCJKService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://xxxq.jyvjd.com/PM25/wservices/YCJKService.asmx?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public YCJKService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public YCJKService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public YCJKService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public YCJKService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public YCJKService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public YCJKService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns YCJKServiceHttpGet
     */
    @WebEndpoint(name = "YCJKServiceHttpGet")
    public YCJKServiceHttpGet getYCJKServiceHttpGet() {
        return super.getPort(YCJKServiceHttpGet, YCJKServiceHttpGet.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns YCJKServiceHttpGet
     */
    @WebEndpoint(name = "YCJKServiceHttpGet")
    public YCJKServiceHttpGet getYCJKServiceHttpGet(WebServiceFeature... features) {
        return super.getPort(YCJKServiceHttpGet, YCJKServiceHttpGet.class, features);
    }


    /**
     *
     * @return
     *     returns YCJKServiceSoap
     */
    @WebEndpoint(name = "YCJKServiceSoap")
    public YCJKServiceSoap getYCJKServiceSoap() {
        return super.getPort(YCJKServiceSoap, YCJKServiceSoap.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns YCJKServiceSoap
     */
    @WebEndpoint(name = "YCJKServiceSoap")
    public YCJKServiceSoap getYCJKServiceSoap(WebServiceFeature... features) {
        return super.getPort(YCJKServiceSoap, YCJKServiceSoap.class, features);
    }


    /**
     *
     * @return
     *     returns YCJKServiceSoap
     */
    @WebEndpoint(name = "YCJKServiceSoap12")
    public YCJKServiceSoap getYCJKServiceSoap12() {
        return super.getPort(YCJKServiceSoap12, YCJKServiceSoap.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns YCJKServiceSoap
     */
    @WebEndpoint(name = "YCJKServiceSoap12")
    public YCJKServiceSoap getYCJKServiceSoap12(WebServiceFeature... features) {
        return super.getPort(YCJKServiceSoap12, YCJKServiceSoap.class, features);
    }


    /**
     *
     * @return
     *     returns YCJKServiceHttpPost
     */
    @WebEndpoint(name = "YCJKServiceHttpPost")
    public YCJKServiceHttpPost getYCJKServiceHttpPost() {
        return super.getPort(YCJKServiceHttpPost, YCJKServiceHttpPost.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns YCJKServiceHttpPost
     */
    @WebEndpoint(name = "YCJKServiceHttpPost")
    public YCJKServiceHttpPost getYCJKServiceHttpPost(WebServiceFeature... features) {
        return super.getPort(YCJKServiceHttpPost, YCJKServiceHttpPost.class, features);
    }

}