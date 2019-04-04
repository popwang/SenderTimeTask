package com.tcp.cd;

public class DevUpMsg {
	
     public String devsn; //2.	设备编号：生产厂家监测设备的编号，4~10个字符
     public String factcode; //3.	厂家代码：例如：WZ，生产厂家在接入平台前，需要向平台管理方申请厂家代码，平台管理方保证厂家代码唯一性；
     public boolean status; //4.	设备状态：设备状态，1个字符，取值范围：默认为“0”无故障；
     public float pm25; //5.	PM2.5：监测参数，取值范围“0.0～500.0”；
     public float pm10; //6.	PM10： 监测参数，取值范围“0.0～2000.0”；
     public float tsp; //7.	TSP：监测参数，取值范围“0.0～20.0”，可以为空；
     public float noise ;// 8.	噪声：监测参数，取值范围“30.0～130.0”；
     public float temp ;// 9.	温度: 监测参数，取值范围“-30.00～+70.0”；
     public float humidity ;// 10.	湿度: 监测参数，取值范围“0.0～100.0”；
     public float windspeed; //11.	风速: 监测参数，取值范围“0.0～60.0”；
     public float winddir ;// 12.	风向: 监测参数，取值范围“0.0～360.0”；
     public float airpressure;//13.	气压：监测参数，取值范围“500.0～1100.0”,可以为空；
     public float longitude ;//经度， 正东负西
     public float latitude;//纬度，正北负南
     
	public String getDevsn() {
		return devsn;
	}
	public void setDevsn(String devsn) {
		this.devsn = devsn;
	}
	public String getFactcode() {
		return factcode;
	}
	public void setFactcode(String factcode) {
		this.factcode = factcode;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public float getPm25() {
		return pm25;
	}
	public void setPm25(float pm25) {
		this.pm25 = pm25;
	}
	public float getPm10() {
		return pm10;
	}
	public void setPm10(float pm10) {
		this.pm10 = pm10;
	}
	public float getTsp() {
		return tsp;
	}
	public void setTsp(float tsp) {
		this.tsp = tsp;
	}
	public float getNoise() {
		return noise;
	}
	public void setNoise(float noise) {
		this.noise = noise;
	}
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	public float getWindspeed() {
		return windspeed;
	}
	public void setWindspeed(float windspeed) {
		this.windspeed = windspeed;
	}
	public float getWinddir() {
		return winddir;
	}
	public void setWinddir(float winddir) {
		this.winddir = winddir;
	}
	public float getAirpressure() {
		return airpressure;
	}
	public void setAirpressure(float airpressure) {
		this.airpressure = airpressure;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
}
