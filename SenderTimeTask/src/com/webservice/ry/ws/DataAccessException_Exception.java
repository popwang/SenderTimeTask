
package com.webservice.ry.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.1.11
 * 2017-08-23T13:33:03.735+08:00
 * Generated source version: 3.1.11
 */

@WebFault(name = "DataAccessException", targetNamespace = "http://service.tblycjc.webservice.client.dekn.com.cn")
public class DataAccessException_Exception extends Exception {
    
	private static final long serialVersionUID = -7698094531257146578L;
	private com.webservice.ry.ws.DataAccessException dataAccessException;

    public DataAccessException_Exception() {
        super();
    }
    
    public DataAccessException_Exception(String message) {
        super(message);
    }
    
    public DataAccessException_Exception(String message, Throwable cause) {
        super(message);
    }

    public DataAccessException_Exception(String message, com.webservice.ry.ws.DataAccessException dataAccessException) {
        super(message);
        this.dataAccessException = dataAccessException;
    }

    public DataAccessException_Exception(String message, com.webservice.ry.ws.DataAccessException dataAccessException, Throwable cause) {
        super(message);
        this.dataAccessException = dataAccessException;
    }

    public com.webservice.ry.ws.DataAccessException getFaultInfo() {
        return this.dataAccessException;
    }
}
