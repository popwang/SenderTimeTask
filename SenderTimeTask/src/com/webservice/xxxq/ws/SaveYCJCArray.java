
package com.webservice.xxxq.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="elements" type="{http://tempuri.org/}ArrayOfArrayOfString" minOccurs="0"/&gt;
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
    "elements"
})
@XmlRootElement(name = "saveYCJCArray")
public class SaveYCJCArray {

    protected ArrayOfArrayOfString elements;

    /**
     * ��ȡelements���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfArrayOfString }
     *     
     */
    public ArrayOfArrayOfString getElements() {
        return elements;
    }

    /**
     * ����elements���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfArrayOfString }
     *     
     */
    public void setElements(ArrayOfArrayOfString value) {
        this.elements = value;
    }

}
