
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.webservice.yc.ws;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.11
 * 2018-07-17T11:51:11.296+08:00
 * Generated source version: 3.1.11
 * 
 */

@javax.jws.WebService(
                      serviceName = "JKInfo",
                      portName = "JKInfoSoap12",
                      targetNamespace = "http://tempuri.org/",
                      wsdlLocation = "http://42.63.25.70:9000/yczj/webservice/jkinfo.asmx?wsdl",
                      endpointInterface = "com.webservice.yc.JKInfoSoap")
                      
public class JKInfoSoap12Impl implements JKInfoSoap {

    private static final Logger LOG = Logger.getLogger(JKInfoSoap12Impl.class.getName());

    /* (non-Javadoc)
     * @see com.webservice.yc.JKInfoSoap#xhtTJDateRec(java.lang.String mKeyPwd, java.lang.String mJsonStr)*
     */
    public java.lang.String xhtTJDateRec(java.lang.String mKeyPwd, java.lang.String mJsonStr) { 
        LOG.info("Executing operation xhtTJDateRec");
        System.out.println(mKeyPwd);
        System.out.println(mJsonStr);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.webservice.yc.JKInfoSoap#xhtKQDateRec(java.lang.String mKeyPwd, java.lang.String mJsonStr)*
     */
    public java.lang.String xhtKQDateRec(java.lang.String mKeyPwd, java.lang.String mJsonStr) { 
        LOG.info("Executing operation xhtKQDateRec");
        System.out.println(mKeyPwd);
        System.out.println(mJsonStr);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.webservice.yc.JKInfoSoap#xhtSJJDateRec(java.lang.String mKeyPwd, java.lang.String mJsonStr)*
     */
    public java.lang.String xhtSJJDateRec(java.lang.String mKeyPwd, java.lang.String mJsonStr) { 
        LOG.info("Executing operation xhtSJJDateRec");
        System.out.println(mKeyPwd);
        System.out.println(mJsonStr);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.webservice.yc.JKInfoSoap#xhtYCDateRec(java.lang.String mKeyPwd, java.lang.String mJsonStr)*
     */
    public java.lang.String xhtYCDateRec(java.lang.String mKeyPwd, java.lang.String mJsonStr) { 
        LOG.info("Executing operation xhtYCDateRec");
        System.out.println(mKeyPwd);
        System.out.println(mJsonStr);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}