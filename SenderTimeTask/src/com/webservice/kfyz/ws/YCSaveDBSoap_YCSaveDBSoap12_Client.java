
package com.webservice.kfyz.ws;

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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.1
 * 2019-08-15T14:51:09.758+08:00
 * Generated source version: 3.3.1
 *
 */
public final class YCSaveDBSoap_YCSaveDBSoap12_Client {

    private static final QName SERVICE_NAME = new QName("http://tempuri.org/", "YCSaveDB");

    private YCSaveDBSoap_YCSaveDBSoap12_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = YCSaveDB.WSDL_LOCATION;
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

        YCSaveDB ss = new YCSaveDB(wsdlURL, SERVICE_NAME);
        YCSaveDBSoap port = ss.getYCSaveDBSoap12();

        {
        System.out.println("Invoking updateStates...");
        boolean _updateStates__return = port.updateStates();
        System.out.println("updateStates.result=" + _updateStates__return);


        }
        {
        System.out.println("Invoking updateYCRealData...");
        java.lang.String _updateYCRealData_sn = "";
        java.lang.String _updateYCRealData_datas = "";
        boolean _updateYCRealData__return = port.updateYCRealData(_updateYCRealData_sn, _updateYCRealData_datas);
        System.out.println("updateYCRealData.result=" + _updateYCRealData__return);


        }
        {
        System.out.println("Invoking getProjectList...");
        java.lang.String _getProjectList_uName = "";
        java.lang.String _getProjectList_password = "";
        int _getProjectList_usersId = 0;
        java.lang.String _getProjectList_proName = "";
        java.lang.String _getProjectList__return = port.getProjectList(_getProjectList_uName, _getProjectList_password, _getProjectList_usersId, _getProjectList_proName);
        System.out.println("getProjectList.result=" + _getProjectList__return);


        }
        {
        System.out.println("Invoking updateRealtimeData...");
        java.lang.String _updateRealtimeData_sn = "";
        java.lang.String _updateRealtimeData_datas = "";
        java.lang.String _updateRealtimeData__return = port.updateRealtimeData(_updateRealtimeData_sn, _updateRealtimeData_datas);
        System.out.println("updateRealtimeData.result=" + _updateRealtimeData__return);


        }
        {
        System.out.println("Invoking getTransferJson...");
        java.lang.String _getTransferJson__return = port.getTransferJson();
        System.out.println("getTransferJson.result=" + _getTransferJson__return);


        }
        {
        System.out.println("Invoking getAppVersion...");
        java.lang.String _getAppVersion_userName = "";
        java.lang.String _getAppVersion_password = "";
        java.lang.String _getAppVersion__return = port.getAppVersion(_getAppVersion_userName, _getAppVersion_password);
        System.out.println("getAppVersion.result=" + _getAppVersion__return);


        }
        {
        System.out.println("Invoking getYCPH...");
        java.lang.String _getYCPH_uName = "";
        java.lang.String _getYCPH_password = "";
        int _getYCPH_usersId = 0;
        int _getYCPH_page = 0;
        int _getYCPH_size = 0;
        java.lang.String _getYCPH_type = "";
        java.lang.String _getYCPH__return = port.getYCPH(_getYCPH_uName, _getYCPH_password, _getYCPH_usersId, _getYCPH_page, _getYCPH_size, _getYCPH_type);
        System.out.println("getYCPH.result=" + _getYCPH__return);


        }
        {
        System.out.println("Invoking checkUsers...");
        java.lang.String _checkUsers_userName = "";
        java.lang.String _checkUsers_userPassword = "";
        java.lang.String _checkUsers__return = port.checkUsers(_checkUsers_userName, _checkUsers_userPassword);
        System.out.println("checkUsers.result=" + _checkUsers__return);


        }
        {
        System.out.println("Invoking getProjectDetails...");
        java.lang.String _getProjectDetails_uName = "";
        java.lang.String _getProjectDetails_password = "";
        int _getProjectDetails_projectId = 0;
        java.lang.String _getProjectDetails_sn = "";
        java.lang.String _getProjectDetails__return = port.getProjectDetails(_getProjectDetails_uName, _getProjectDetails_password, _getProjectDetails_projectId, _getProjectDetails_sn);
        System.out.println("getProjectDetails.result=" + _getProjectDetails__return);


        }
        {
        System.out.println("Invoking getYCCurve...");
        java.lang.String _getYCCurve_uName = "";
        java.lang.String _getYCCurve_password = "";
        int _getYCCurve_ycparamId = 0;
        java.lang.String _getYCCurve_startDate = "";
        java.lang.String _getYCCurve_endDate = "";
        java.lang.String _getYCCurve__return = port.getYCCurve(_getYCCurve_uName, _getYCCurve_password, _getYCCurve_ycparamId, _getYCCurve_startDate, _getYCCurve_endDate);
        System.out.println("getYCCurve.result=" + _getYCCurve__return);


        }
        {
        System.out.println("Invoking updateYCRealtimeData...");
        java.lang.String _updateYCRealtimeData_sn = "";
        java.lang.String _updateYCRealtimeData_datas = "";
        boolean _updateYCRealtimeData__return = port.updateYCRealtimeData(_updateYCRealtimeData_sn, _updateYCRealtimeData_datas);
        System.out.println("updateYCRealtimeData.result=" + _updateYCRealtimeData__return);


        }
        {
        System.out.println("Invoking getYCWX...");
        java.lang.String _getYCWX_uName = "";
        java.lang.String _getYCWX_password = "";
        int _getYCWX_usersId = 0;
        int _getYCWX_page = 0;
        int _getYCWX_size = 0;
        java.lang.String _getYCWX__return = port.getYCWX(_getYCWX_uName, _getYCWX_password, _getYCWX_usersId, _getYCWX_page, _getYCWX_size);
        System.out.println("getYCWX.result=" + _getYCWX__return);


        }

        System.exit(0);
    }

}