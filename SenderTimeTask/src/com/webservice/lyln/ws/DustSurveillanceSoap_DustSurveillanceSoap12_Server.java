
package com.webservice.lyln.ws;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.3.1
 * 2019-09-02T11:23:33.131+08:00
 * Generated source version: 3.3.1
 *
 */

public class DustSurveillanceSoap_DustSurveillanceSoap12_Server{

    protected DustSurveillanceSoap_DustSurveillanceSoap12_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new DustSurveillanceSoap12Impl();
        String address = "http://39.97.230.214/dust/api/DustSurveillance.asmx";
        Endpoint.publish(address, implementor);
    }

    public static void main(String args[]) throws java.lang.Exception {
        new DustSurveillanceSoap_DustSurveillanceSoap12_Server();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
