package com.tcp.xabq2;

public class BQ2Vo {
	private String id;
	private String auth;
	private String ver;
	private int warn;
	private BQ2 data;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public int getWarn() {
		return warn;
	}
	public void setWarn(int warn) {
		this.warn = warn;
	}
	public BQ2 getData() {
		return data;
	}
	public void setData(BQ2 data) {
		this.data = data;
	}
}
