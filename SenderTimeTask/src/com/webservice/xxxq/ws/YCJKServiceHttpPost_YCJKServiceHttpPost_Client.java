
package com.webservice.xxxq.ws;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.3.1
 * 2019-03-22T09:45:04.402+08:00
 * Generated source version: 3.3.1
 *
 */
public final class YCJKServiceHttpPost_YCJKServiceHttpPost_Client {

    private static final QName SERVICE_NAME = new QName("http://tempuri.org/", "YCJKService");

    private YCJKServiceHttpPost_YCJKServiceHttpPost_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = YCJKService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        YCJKService ss = new YCJKService(wsdlURL, SERVICE_NAME);
        YCJKServiceHttpPost port = ss.getYCJKServiceHttpPost();

        {
        System.out.println("Invoking saveYCJC...");
        java.lang.String _saveYCJC_elements = "";
        java.lang.String _saveYCJC__return = port.saveYCJC(_saveYCJC_elements);
        System.out.println("saveYCJC.result=" + _saveYCJC__return);


        }

        System.exit(0);
    }

}
