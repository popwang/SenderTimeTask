
package com.webservice.xxxq.ws;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.3.1
 * 2019-03-22T09:45:04.444+08:00
 * Generated source version: 3.3.1
 *
 */

public class YCJKServiceSoap_YCJKServiceSoap12_Server{

    protected YCJKServiceSoap_YCJKServiceSoap12_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new YCJKServiceSoap12Impl();
        String address = "http://xxxq.jyvjd.com/PM25/wservices/YCJKService.asmx";
        Endpoint.publish(address, implementor);
    }

    public static void main(String args[]) throws java.lang.Exception {
        new YCJKServiceSoap_YCJKServiceSoap12_Server();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
