
package com.webservice.kf2.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.1.11
 * 2017-11-24T16:35:42.053+08:00
 * Generated source version: 3.1.11
 */

@WebFault(name = "DataAccessException", targetNamespace = "http://service.tblycjc.webservice.client.dekn.com.cn")
public class DataAccessException_Exception extends Exception {
	private static final long serialVersionUID = 1L;
	private com.webservice.kf2.ws.DataAccessException dataAccessException;

    public DataAccessException_Exception() {
        super();
    }
    
    public DataAccessException_Exception(String message) {
        super(message);
    }
    
    public DataAccessException_Exception(String message, Throwable cause) {
    }

    public DataAccessException_Exception(String message, com.webservice.kf2.ws.DataAccessException dataAccessException) {
        super(message);
        this.dataAccessException = dataAccessException;
    }

    public DataAccessException_Exception(String message, com.webservice.kf2.ws.DataAccessException dataAccessException, Throwable cause) {
        this.dataAccessException = dataAccessException;
    }

    public com.webservice.kf2.ws.DataAccessException getFaultInfo() {
        return this.dataAccessException;
    }
}
