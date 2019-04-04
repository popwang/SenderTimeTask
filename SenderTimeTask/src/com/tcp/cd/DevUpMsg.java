package com.tcp.cd;

public class DevUpMsg {
	
     public String devsn; //2.	�豸��ţ��������Ҽ���豸�ı�ţ�4~10���ַ�
     public String factcode; //3.	���Ҵ��룺���磺WZ�����������ڽ���ƽ̨ǰ����Ҫ��ƽ̨�������볧�Ҵ��룬ƽ̨������֤���Ҵ���Ψһ�ԣ�
     public boolean status; //4.	�豸״̬���豸״̬��1���ַ���ȡֵ��Χ��Ĭ��Ϊ��0���޹��ϣ�
     public float pm25; //5.	PM2.5����������ȡֵ��Χ��0.0��500.0����
     public float pm10; //6.	PM10�� ��������ȡֵ��Χ��0.0��2000.0����
     public float tsp; //7.	TSP����������ȡֵ��Χ��0.0��20.0��������Ϊ�գ�
     public float noise ;// 8.	��������������ȡֵ��Χ��30.0��130.0����
     public float temp ;// 9.	�¶�: ��������ȡֵ��Χ��-30.00��+70.0����
     public float humidity ;// 10.	ʪ��: ��������ȡֵ��Χ��0.0��100.0����
     public float windspeed; //11.	����: ��������ȡֵ��Χ��0.0��60.0����
     public float winddir ;// 12.	����: ��������ȡֵ��Χ��0.0��360.0����
     public float airpressure;//13.	��ѹ����������ȡֵ��Χ��500.0��1100.0��,����Ϊ�գ�
     public float longitude ;//���ȣ� ��������
     public float latitude;//γ�ȣ���������
     
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
