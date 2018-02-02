
package com.webservice.ayhx.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DataAccessException complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="DataAccessException"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cause" type="{http://lang.java}Throwable" minOccurs="0"/&gt;
 *         &lt;element name="define" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="realm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataAccessException", namespace = "http://exception.common.dekn.com.cn", propOrder = {
    "cause",
    "define",
    "message",
    "position",
    "realm"
})
public class DataAccessException {

    @XmlElementRef(name = "cause", namespace = "http://exception.common.dekn.com.cn", type = JAXBElement.class, required = false)
    protected JAXBElement<Throwable> cause;
    @XmlElementRef(name = "define", namespace = "http://exception.common.dekn.com.cn", type = JAXBElement.class, required = false)
    protected JAXBElement<String> define;
    @XmlElementRef(name = "message", namespace = "http://exception.common.dekn.com.cn", type = JAXBElement.class, required = false)
    protected JAXBElement<String> message;
    @XmlElementRef(name = "position", namespace = "http://exception.common.dekn.com.cn", type = JAXBElement.class, required = false)
    protected JAXBElement<String> position;
    @XmlElementRef(name = "realm", namespace = "http://exception.common.dekn.com.cn", type = JAXBElement.class, required = false)
    protected JAXBElement<String> realm;

    /**
     * ��ȡcause���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Throwable }{@code >}
     *     
     */
    public JAXBElement<Throwable> getCause() {
        return cause;
    }

    /**
     * ����cause���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Throwable }{@code >}
     *     
     */
    public void setCause(JAXBElement<Throwable> value) {
        this.cause = value;
    }

    /**
     * ��ȡdefine���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDefine() {
        return define;
    }

    /**
     * ����define���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDefine(JAXBElement<String> value) {
        this.define = value;
    }

    /**
     * ��ȡmessage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMessage() {
        return message;
    }

    /**
     * ����message���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMessage(JAXBElement<String> value) {
        this.message = value;
    }

    /**
     * ��ȡposition���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPosition() {
        return position;
    }

    /**
     * ����position���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPosition(JAXBElement<String> value) {
        this.position = value;
    }

    /**
     * ��ȡrealm���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRealm() {
        return realm;
    }

    /**
     * ����realm���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRealm(JAXBElement<String> value) {
        this.realm = value;
    }

}
