package com.webservice.gy.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.11
 * 2018-05-14T14:22:38.590+08:00
 * Generated source version: 3.1.11
 * 
 */
@WebServiceClient(name = "SaveYCJCService", 
                  wsdlLocation = "http://117.158.13.182:8686/gysanitate/services/SaveYCJCService?wsdl",
                  targetNamespace = "http://service.tblycjc.webservice.client.dekn.com.cn") 
public class SaveYCJCService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://service.tblycjc.webservice.client.dekn.com.cn", "SaveYCJCService");
    public final static QName SaveYCJCServiceHttpPort = new QName("http://service.tblycjc.webservice.client.dekn.com.cn", "SaveYCJCServiceHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://117.158.13.182:8686/gysanitate/services/SaveYCJCService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SaveYCJCService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://117.158.13.182:8686/gysanitate/services/SaveYCJCService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SaveYCJCService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SaveYCJCService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SaveYCJCService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public SaveYCJCService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public SaveYCJCService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public SaveYCJCService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns SaveYCJCServicePortType
     */
    @WebEndpoint(name = "SaveYCJCServiceHttpPort")
    public SaveYCJCServicePortType getSaveYCJCServiceHttpPort() {
        return super.getPort(SaveYCJCServiceHttpPort, SaveYCJCServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SaveYCJCServicePortType
     */
    @WebEndpoint(name = "SaveYCJCServiceHttpPort")
    public SaveYCJCServicePortType getSaveYCJCServiceHttpPort(WebServiceFeature... features) {
        return super.getPort(SaveYCJCServiceHttpPort, SaveYCJCServicePortType.class, features);
    }

}
