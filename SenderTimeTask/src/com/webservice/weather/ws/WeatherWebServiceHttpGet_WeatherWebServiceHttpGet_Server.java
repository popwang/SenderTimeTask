
package com.webservice.weather.ws;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.1.11
 * 2017-10-23T19:51:29.242+08:00
 * Generated source version: 3.1.11
 * 
 */
 
public class WeatherWebServiceHttpGet_WeatherWebServiceHttpGet_Server{

    protected WeatherWebServiceHttpGet_WeatherWebServiceHttpGet_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new WeatherWebServiceHttpGetImpl();
        String address = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new WeatherWebServiceHttpGet_WeatherWebServiceHttpGet_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
