package com.tcp.anhx;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
public class Data {
	private String PM10;
	private String PM2_5;
	private String WS;
	private String WD;
	private String TEMP;
	private String HUM;
	private String NOISE;
	
	private String SITECODE;
	private String MONITORTIME;
	private String DATASOURCES;
	public String getPM10() {
		return PM10;
	}
	public void setPM10(String pM10) {
		PM10 = pM10;
	}
	public String getPM2_5() {
		return PM2_5;
	}
	public void setPM2_5(String pM2_5) {
		PM2_5 = pM2_5;
	}
	public String getWS() {
		return WS;
	}
	public void setWS(String wS) {
		WS = wS;
	}
	public String getWD() {
		return WD;
	}
	public void setWD(String wD) {
		WD = wD;
	}
	public String getTEMP() {
		return TEMP;
	}
	public void setTEMP(String tEMP) {
		TEMP = tEMP;
	}
	public String getHUM() {
		return HUM;
	}
	public void setHUM(String hUM) {
		HUM = hUM;
	}
	public String getNOISE() {
		return NOISE;
	}
	public void setNOISE(String nOISE) {
		NOISE = nOISE;
	}
	public String getSITECODE() {
		return SITECODE;
	}
	public void setSITECODE(String sITECODE) {
		SITECODE = sITECODE;
	}
	public String getMONITORTIME() {
		return MONITORTIME;
	}
	public void setMONITORTIME(String mONITORTIME) {
		MONITORTIME = mONITORTIME;
	}
	public String getDATASOURCES() {
		return DATASOURCES;
	}
	public void setDATASOURCES(String dATASOURCES) {
		DATASOURCES = dATASOURCES;
	}
}
