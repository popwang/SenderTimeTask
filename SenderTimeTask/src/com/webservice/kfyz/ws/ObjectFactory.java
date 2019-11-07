
package com.webservice.kfyz.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.webservice.kfyz.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SaveDBHeader_QNAME = new QName("http://tempuri.org/", "SaveDBHeader");
    private final static QName _Boolean_QNAME = new QName("http://tempuri.org/", "boolean");
    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.webservice.kfyz.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateYCRealtimeData }
     * 
     */
    public UpdateYCRealtimeData createUpdateYCRealtimeData() {
        return new UpdateYCRealtimeData();
    }

    /**
     * Create an instance of {@link UpdateYCRealtimeDataResponse }
     * 
     */
    public UpdateYCRealtimeDataResponse createUpdateYCRealtimeDataResponse() {
        return new UpdateYCRealtimeDataResponse();
    }

    /**
     * Create an instance of {@link SaveDBHeader }
     * 
     */
    public SaveDBHeader createSaveDBHeader() {
        return new SaveDBHeader();
    }

    /**
     * Create an instance of {@link UpdateRealtimeData }
     * 
     */
    public UpdateRealtimeData createUpdateRealtimeData() {
        return new UpdateRealtimeData();
    }

    /**
     * Create an instance of {@link UpdateRealtimeDataResponse }
     * 
     */
    public UpdateRealtimeDataResponse createUpdateRealtimeDataResponse() {
        return new UpdateRealtimeDataResponse();
    }

    /**
     * Create an instance of {@link UpdateYCRealData }
     * 
     */
    public UpdateYCRealData createUpdateYCRealData() {
        return new UpdateYCRealData();
    }

    /**
     * Create an instance of {@link UpdateYCRealDataResponse }
     * 
     */
    public UpdateYCRealDataResponse createUpdateYCRealDataResponse() {
        return new UpdateYCRealDataResponse();
    }

    /**
     * Create an instance of {@link UpdateStates }
     * 
     */
    public UpdateStates createUpdateStates() {
        return new UpdateStates();
    }

    /**
     * Create an instance of {@link UpdateStatesResponse }
     * 
     */
    public UpdateStatesResponse createUpdateStatesResponse() {
        return new UpdateStatesResponse();
    }

    /**
     * Create an instance of {@link GetAppVersion }
     * 
     */
    public GetAppVersion createGetAppVersion() {
        return new GetAppVersion();
    }

    /**
     * Create an instance of {@link GetAppVersionResponse }
     * 
     */
    public GetAppVersionResponse createGetAppVersionResponse() {
        return new GetAppVersionResponse();
    }

    /**
     * Create an instance of {@link CheckUsers }
     * 
     */
    public CheckUsers createCheckUsers() {
        return new CheckUsers();
    }

    /**
     * Create an instance of {@link CheckUsersResponse }
     * 
     */
    public CheckUsersResponse createCheckUsersResponse() {
        return new CheckUsersResponse();
    }

    /**
     * Create an instance of {@link GetProjectList }
     * 
     */
    public GetProjectList createGetProjectList() {
        return new GetProjectList();
    }

    /**
     * Create an instance of {@link GetProjectListResponse }
     * 
     */
    public GetProjectListResponse createGetProjectListResponse() {
        return new GetProjectListResponse();
    }

    /**
     * Create an instance of {@link GetProjectDetails }
     * 
     */
    public GetProjectDetails createGetProjectDetails() {
        return new GetProjectDetails();
    }

    /**
     * Create an instance of {@link GetProjectDetailsResponse }
     * 
     */
    public GetProjectDetailsResponse createGetProjectDetailsResponse() {
        return new GetProjectDetailsResponse();
    }

    /**
     * Create an instance of {@link GetYCCurve }
     * 
     */
    public GetYCCurve createGetYCCurve() {
        return new GetYCCurve();
    }

    /**
     * Create an instance of {@link GetYCCurveResponse }
     * 
     */
    public GetYCCurveResponse createGetYCCurveResponse() {
        return new GetYCCurveResponse();
    }

    /**
     * Create an instance of {@link GetYCPH }
     * 
     */
    public GetYCPH createGetYCPH() {
        return new GetYCPH();
    }

    /**
     * Create an instance of {@link GetYCPHResponse }
     * 
     */
    public GetYCPHResponse createGetYCPHResponse() {
        return new GetYCPHResponse();
    }

    /**
     * Create an instance of {@link GetYCWX }
     * 
     */
    public GetYCWX createGetYCWX() {
        return new GetYCWX();
    }

    /**
     * Create an instance of {@link GetYCWXResponse }
     * 
     */
    public GetYCWXResponse createGetYCWXResponse() {
        return new GetYCWXResponse();
    }

    /**
     * Create an instance of {@link GetTransferJson }
     * 
     */
    public GetTransferJson createGetTransferJson() {
        return new GetTransferJson();
    }

    /**
     * Create an instance of {@link GetTransferJsonResponse }
     * 
     */
    public GetTransferJsonResponse createGetTransferJsonResponse() {
        return new GetTransferJsonResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDBHeader }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaveDBHeader }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "SaveDBHeader")
    public JAXBElement<SaveDBHeader> createSaveDBHeader(SaveDBHeader value) {
        return new JAXBElement<SaveDBHeader>(_SaveDBHeader_QNAME, SaveDBHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
