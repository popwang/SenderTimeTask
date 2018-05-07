package com.tcp.anhx;

public class HxVo {
	private String appId = "datacollect";
	private String appKey = "123456789";
	private String datatype = "RAISEDUST";
	private String datakind = "MinData";
	private Data data;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getDatakind() {
		return datakind;
	}
	public void setDatakind(String datakind) {
		this.datakind = datakind;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
}
