
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.webservice.pds.ws;

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
 * 2019-05-30T17:29:59.047+08:00
 * Generated source version: 3.3.1
 *
 */

@javax.jws.WebService(
                      serviceName = "SaveYCJCService",
                      portName = "SaveYCJCServiceHttpPort",
                      targetNamespace = "http://service.tblycjc.webservice.client.dekn.com.cn",
                      wsdlLocation = "http://123.163.55.113:8686/pdssanitate/services/SaveYCJCService?wsdl",
                      endpointInterface = "com.webservice.pds.ws.SaveYCJCServicePortType")

public class SaveYCJCServiceHttpPortImpl implements SaveYCJCServicePortType {

    private static final Logger LOG = Logger.getLogger(SaveYCJCServiceHttpPortImpl.class.getName());

    /* (non-Javadoc)
     * @see com.webservice.pds.ws.SaveYCJCServicePortType#saveYCJC(java.lang.String in0)*
     */
    public java.lang.String saveYCJC(java.lang.String in0) throws DataAccessException_Exception   {
        LOG.info("Executing operation saveYCJC");
        System.out.println(in0);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new DataAccessException_Exception("DataAccessException...");
    }

}
