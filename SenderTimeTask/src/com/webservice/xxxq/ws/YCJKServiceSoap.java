package com.webservice.xxxq.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.1
 * 2019-03-22T09:45:04.432+08:00
 * Generated source version: 3.3.1
 *
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "YCJKServiceSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface YCJKServiceSoap {

    @WebMethod(action = "http://tempuri.org/saveYCJC")
    @RequestWrapper(localName = "saveYCJC", targetNamespace = "http://tempuri.org/", className = "com.webservice.xxxq.ws.SaveYCJC")
    @ResponseWrapper(localName = "saveYCJCResponse", targetNamespace = "http://tempuri.org/", className = "com.webservice.xxxq.ws.SaveYCJCResponse")
    @WebResult(name = "saveYCJCResult", targetNamespace = "http://tempuri.org/")
    public java.lang.String saveYCJC(
        @WebParam(name = "elements", targetNamespace = "http://tempuri.org/")
        java.lang.String elements
    );

    @WebMethod(action = "http://tempuri.org/saveYCJCArray")
    @RequestWrapper(localName = "saveYCJCArray", targetNamespace = "http://tempuri.org/", className = "com.webservice.xxxq.ws.SaveYCJCArray")
    @ResponseWrapper(localName = "saveYCJCArrayResponse", targetNamespace = "http://tempuri.org/", className = "com.webservice.xxxq.ws.SaveYCJCArrayResponse")
    @WebResult(name = "saveYCJCArrayResult", targetNamespace = "http://tempuri.org/")
    public java.lang.String saveYCJCArray(
        @WebParam(name = "elements", targetNamespace = "http://tempuri.org/")
        com.webservice.xxxq.ws.ArrayOfArrayOfString elements
    );
}
