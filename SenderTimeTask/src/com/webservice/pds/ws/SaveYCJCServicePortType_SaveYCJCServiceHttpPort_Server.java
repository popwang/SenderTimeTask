
package com.webservice.pds.ws;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.3.1
 * 2019-05-30T17:29:59.057+08:00
 * Generated source version: 3.3.1
 *
 */

public class SaveYCJCServicePortType_SaveYCJCServiceHttpPort_Server{

    protected SaveYCJCServicePortType_SaveYCJCServiceHttpPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new SaveYCJCServiceHttpPortImpl();
        String address = "http://123.163.55.113:8686/pdssanitate/services/SaveYCJCService";
        Endpoint.publish(address, implementor);
    }

    public static void main(String args[]) throws java.lang.Exception {
        new SaveYCJCServicePortType_SaveYCJCServiceHttpPort_Server();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
