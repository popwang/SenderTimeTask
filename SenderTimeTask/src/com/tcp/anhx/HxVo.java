package com.tcp.anhx;

import java.util.List;

public class HxVo {
	private String appId = "datacollect";
	private String appKey = "123456789";
	private String datatype = "RAISEDUST";
	private String datakind = "MinData";
	private List<Data> data;
	
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
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
}
