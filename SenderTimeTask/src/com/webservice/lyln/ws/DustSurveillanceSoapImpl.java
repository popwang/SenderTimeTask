
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.webservice.lyln.ws;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.1
 * 2019-09-02T11:23:33.112+08:00
 * Generated source version: 3.3.1
 *
 */

@javax.jws.WebService(
                      serviceName = "DustSurveillance",
                      portName = "DustSurveillanceSoap",
                      targetNamespace = "http://tempuri.org/",
                      wsdlLocation = "http://39.97.230.214/dust/api/DustSurveillance.asmx?wsdl",
                      endpointInterface = "com.webservice.lyln.ws.DustSurveillanceSoap")

public class DustSurveillanceSoapImpl implements DustSurveillanceSoap {

    private static final Logger LOG = Logger.getLogger(DustSurveillanceSoapImpl.class.getName());

    /* (non-Javadoc)
     * @see com.webservice.lyln.ws.DustSurveillanceSoap#uploadDustInfo(java.lang.String sign, java.lang.String unitno, java.lang.String content)*
     */
    public java.lang.String uploadDustInfo(java.lang.String sign, java.lang.String unitno, java.lang.String content) {
        LOG.info("Executing operation uploadDustInfo");
        System.out.println(sign);
        System.out.println(unitno);
        System.out.println(content);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
