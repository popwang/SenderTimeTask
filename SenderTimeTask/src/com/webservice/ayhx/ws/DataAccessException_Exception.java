
package com.webservice.ayhx.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.1.11
 * 2017-07-01T22:08:16.951+08:00
 * Generated source version: 3.1.11
 */

@WebFault(name = "DataAccessException", targetNamespace = "http://service.tblycjc.webservice.client.dekn.com.cn")
public class DataAccessException_Exception extends Exception {
	private static final long serialVersionUID = 1L;
	private com.webservice.ayhx.ws.DataAccessException dataAccessException;

    public DataAccessException_Exception() {
        super();
    }
    
    public DataAccessException_Exception(String message) {
        super(message);
    }
    
    public DataAccessException_Exception(String message, Throwable cause) {
        super(message);
    }

    public DataAccessException_Exception(String message, com.webservice.ayhx.ws.DataAccessException dataAccessException) {
        super(message);
        this.dataAccessException = dataAccessException;
    }

    public DataAccessException_Exception(String message, com.webservice.ayhx.ws.DataAccessException dataAccessException, Throwable cause) {
        super(message);
        this.dataAccessException = dataAccessException;
    }

    public com.webservice.ayhx.ws.DataAccessException getFaultInfo() {
        return this.dataAccessException;
    }
}
