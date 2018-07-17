package com.webservice.yc;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
public class YcVo {
	private String SBID;
	private String Noise;
	private String PM10;
	private String PM25;
	private String TSP;
	private String TEMP2;
	private String HUM;
	private String WS;
	private String WD;
	public String getSBID() {
		return SBID;
	}
	public void setSBID(String sBID) {
		SBID = sBID;
	}
	public String getNoise() {
		return Noise;
	}
	public void setNoise(String noise) {
		Noise = noise;
	}
	public String getPM10() {
		return PM10;
	}
	public void setPM10(String pM10) {
		PM10 = pM10;
	}
	public String getPM25() {
		return PM25;
	}
	public void setPM25(String pM25) {
		PM25 = pM25;
	}
	public String getTSP() {
		return TSP;
	}
	public void setTSP(String tSP) {
		TSP = tSP;
	}
	public String getTEMP2() {
		return TEMP2;
	}
	public void setTEMP2(String tEMP2) {
		TEMP2 = tEMP2;
	}
	public String getHUM() {
		return HUM;
	}
	public void setHUM(String hUM) {
		HUM = hUM;
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
}
