
package com.webservice.ry.ws;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.1.11
 * 2017-08-23T13:33:03.766+08:00
 * Generated source version: 3.1.11
 * 
 */
 
public class SaveYCJCServicePortType_SaveYCJCServiceHttpPort_Server{

    protected SaveYCJCServicePortType_SaveYCJCServiceHttpPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new SaveYCJCServiceHttpPortImpl();
        String address = "http://117.158.183.213:8081/rysanitate/services/SaveYCJCService";
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
