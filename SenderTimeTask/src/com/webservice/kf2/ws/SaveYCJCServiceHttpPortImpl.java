
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.webservice.kf2.ws;

import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 3.1.11
 * 2017-11-24T16:35:42.067+08:00
 * Generated source version: 3.1.11
 * 
 */

@javax.jws.WebService(
                      serviceName = "SaveYCJCService",
                      portName = "SaveYCJCServiceHttpPort",
                      targetNamespace = "http://service.tblycjc.webservice.client.dekn.com.cn",
                      wsdlLocation = "http://221.176.156.141:8686/kfsanitate/services/SaveYCJCService?wsdl",
                      endpointInterface = "com.webservice.kf2.ws.SaveYCJCServicePortType")
                      
public class SaveYCJCServiceHttpPortImpl implements SaveYCJCServicePortType {

    private static final Logger LOG = Logger.getLogger(SaveYCJCServiceHttpPortImpl.class.getName());

    /* (non-Javadoc)
     * @see com.webservice.kf2.ws.SaveYCJCServicePortType#saveYCJC(java.lang.String in0)*
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
