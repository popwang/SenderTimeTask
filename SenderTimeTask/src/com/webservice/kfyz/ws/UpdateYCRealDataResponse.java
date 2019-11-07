
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
 *         &lt;element name="UpdateYCRealDataResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "updateYCRealDataResult"
})
@XmlRootElement(name = "UpdateYCRealDataResponse")
public class UpdateYCRealDataResponse {

    @XmlElement(name = "UpdateYCRealDataResult")
    protected boolean updateYCRealDataResult;

    /**
     * 获取updateYCRealDataResult属性的值。
     * 
     */
    public boolean isUpdateYCRealDataResult() {
        return updateYCRealDataResult;
    }

    /**
     * 设置updateYCRealDataResult属性的值。
     * 
     */
    public void setUpdateYCRealDataResult(boolean value) {
        this.updateYCRealDataResult = value;
    }

}
