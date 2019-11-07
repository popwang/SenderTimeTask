
package com.webservice.kfyz.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UpdateYCRealtimeDataResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "updateYCRealtimeDataResult"
})
@XmlRootElement(name = "UpdateYCRealtimeDataResponse")
public class UpdateYCRealtimeDataResponse {

    @XmlElement(name = "UpdateYCRealtimeDataResult")
    protected boolean updateYCRealtimeDataResult;

    /**
     * 获取updateYCRealtimeDataResult属性的值。
     * 
     */
    public boolean isUpdateYCRealtimeDataResult() {
        return updateYCRealtimeDataResult;
    }

    /**
     * 设置updateYCRealtimeDataResult属性的值。
     * 
     */
    public void setUpdateYCRealtimeDataResult(boolean value) {
        this.updateYCRealtimeDataResult = value;
    }

}
