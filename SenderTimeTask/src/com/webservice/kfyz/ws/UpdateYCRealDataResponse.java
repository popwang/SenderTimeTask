
package com.webservice.kfyz.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡupdateYCRealDataResult���Ե�ֵ��
     * 
     */
    public boolean isUpdateYCRealDataResult() {
        return updateYCRealDataResult;
    }

    /**
     * ����updateYCRealDataResult���Ե�ֵ��
     * 
     */
    public void setUpdateYCRealDataResult(boolean value) {
        this.updateYCRealDataResult = value;
    }

}
