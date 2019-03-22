
package com.webservice.xxxq.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
     * 获取elements属性的值。
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
     * 设置elements属性的值。
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
