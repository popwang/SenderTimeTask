package com.vo;
/**
 * 20180808增加了project中的一些属性字段，这两个表的对象逐渐融合
 * @author pactera
 *
 */
public class EquipmentData {
	private String v_equipment_name;
	private String v_project_name;
	private Integer i_equipment_id;
	private String v_real_equipment_name;
	private String v_url;
	//传感器状态
	private double p001;
	//PM2.5
	private double p002;
	//PM10
	private double p003;
	//风速
	private double p004;
	//风向
	private double p005;
	//温度
	private double p006;
	//湿度
	private double p007;
	//噪音
	private double p008;
	//PM100
	private double p009;
	//气压
	private double p010;
	//保留3
	private double p011;
	//保留4
	private double p012;
	//保留5
	private double p013;
	//经度
	private double p014;
	//纬度
	private double p015;
	
	private double p016;
	private double p017;
	private double p018;
	private double p019;
	private double p020;
	private double p021;
	private double p022;
	private double p023;
	private double p024;
	private double p025;
	private double p026;
	private double p027;
	private double p028;
	private double p029;
	private double p030;
	
	private String dtm_update;
	private String dtm_setdate;
	private String windSpeed;
	
	public String getWindSpeed() {
		if(this.p004<0.3){
			windSpeed="无风";
		}else if(this.p004>=0.3 && this.p004<1.6){
			windSpeed="软风";
		}else if(this.p004>=1.6 && this.p004<3.4){
			windSpeed="轻风";
		}else if(this.p004>=3.4 && this.p004<5.5){
			windSpeed="微风";
		}else if(this.p004>=5.5 && this.p004<8.0){
			windSpeed="和风";
		}else if(this.p004>=8.0 && this.p004<10.8){
			windSpeed="清风";
		}else if(this.p004>=10.8 && this.p004<13.9){
			windSpeed="强风";
		}else if(this.p004>=13.9 && this.p004<17.2){
			windSpeed="劲风";
		}else if(this.p004>=17.2 && this.p004<20.8){
			windSpeed="大风";
		}else if(this.p004>=20.8 && this.p004<24.5){
			windSpeed="烈风";
		}else if(this.p004>=24.5 && this.p004<28.5){
			windSpeed="狂风";
		}else if(this.p004>=28.5 && this.p004<32.7){
			windSpeed="暴风";
		}else if(this.p004>=32.7){
			windSpeed="台风";
		}
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	@Override
	public String toString(){
		return "状态："+this.getP001()+
				"；PM2.5："+ this.getP002()+
				"；PM10："+ this.getP003()+
				"；风速："+ this.getP004()+
				"；风向："+ this.getP005()+
				"；温度："+ this.getP006()+
				"；湿度："+ this.getP007()+
				"；噪声："+ this.getP008()+
				"；PM100："+ this.getP009()+
				"；气压："+ this.getP010();
	}
	
	public String getV_project_name() {
		return v_project_name;
	}

	public void setV_project_name(String v_project_name) {
		this.v_project_name = v_project_name;
	}

	public Integer getI_equipment_id() {
		return i_equipment_id;
	}

	public void setI_equipment_id(Integer i_equipment_id) {
		this.i_equipment_id = i_equipment_id;
	}
	/**
	 * 纬度
	 * @return
	 */
	public double getP015() {
		return p015;
	}

	public void setP015(double p015) {
		this.p015 = p015;
	}
	/**
	 * 气压
	 * @return
	 */
	public double getP010() {
		return p010;
	}

	public void setP010(double p010) {
		this.p010 = p010;
	}

	/**
	 * 风力等级
	 * @return
	 */
	public double getP011() {
		return p011;
	}

	public void setP011(double p011) {
		this.p011 = p011;
	}

	public double getP012() {
		return p012;
	}

	public void setP012(double p012) {
		this.p012 = p012;
	}

	public double getP013() {
		return p013;
	}

	public void setP013(double p013) {
		this.p013 = p013;
	}
	/**
	 * //经度
	 * @return
	 */
	public double getP014() {
		return p014;
	}

	public void setP014(double p014) {
		this.p014 = p014;
	}

	public String getV_equipment_name() {
		return v_equipment_name;
	}
	public void setV_equipment_name(String v_equipment_name) {
		this.v_equipment_name = v_equipment_name;
	}
	/**
	 * 传感器状态
	 * @return
	 */
	public double getP001() {
		return p001;
	}
	public void setP001(double p001) {
		this.p001 = p001;
	}
	/**
	 * PM2.5
	 * @return
	 */
	public double getP002() {
		return p002;
	}
	public void setP002(double p002) {
		this.p002 = p002;
	}
	/**
	 * PM10
	 * @return
	 */
	public double getP003() {
		return p003;
	}
	public void setP003(double p003) {
		this.p003 = p003;
	}
	/**
	 * 风速
	 * @return
	 */
	public double getP004() {
		return p004;
	}
	public void setP004(double p004) {
		this.p004 = p004;
	}
	/**
	 * 风向
	 * @return
	 */
	public double getP005() {
		return p005;
	}
	
	public void setP005(double p005) {
		this.p005 = p005;
	}
	/**
	 * 温度
	 * @return
	 */
	public double getP006() {
		return p006;
	}
	public void setP006(double p006) {
		this.p006 = p006;
	}
	/**
	 * 湿度
	 * @return
	 */
	public double getP007() {
		return p007;
	}
	public void setP007(double p007) {
		this.p007 = p007;
	}
	/**
	 * 噪音
	 * @return
	 */
	public double getP008() {
		return p008;
	}
	public void setP008(double p008) {
		this.p008 = p008;
	}
	/**
	 * PM100
	 * @return
	 */
	public double getP009() {
		return p009;
	}
	public void setP009(double p009) {
		this.p009 = p009;
	}
	
	public String getDtm_update() {
		return dtm_update;
	}
	public void setDtm_update(String dtm_update) {
		this.dtm_update = dtm_update;
	}
	public String getDtm_setdate() {
		return dtm_setdate;
	}
	public void setDtm_setdate(String dtm_setdate) {
		this.dtm_setdate = dtm_setdate;
	}

	public String getV_real_equipment_name() {
		return v_real_equipment_name;
	}

	public void setV_real_equipment_name(String v_real_equipment_name) {
		this.v_real_equipment_name = v_real_equipment_name;
	}
	public String getV_url() {
		return v_url;
	}
	public void setV_url(String v_url) {
		this.v_url = v_url;
	}

	public double getP016() {
		return p016;
	}

	public void setP016(double p016) {
		this.p016 = p016;
	}

	public double getP017() {
		return p017;
	}

	public void setP017(double p017) {
		this.p017 = p017;
	}

	public double getP018() {
		return p018;
	}

	public void setP018(double p018) {
		this.p018 = p018;
	}

	public double getP019() {
		return p019;
	}

	public void setP019(double p019) {
		this.p019 = p019;
	}

	public double getP020() {
		return p020;
	}

	public void setP020(double p020) {
		this.p020 = p020;
	}

	public double getP021() {
		return p021;
	}

	public void setP021(double p021) {
		this.p021 = p021;
	}

	public double getP022() {
		return p022;
	}

	public void setP022(double p022) {
		this.p022 = p022;
	}

	public double getP023() {
		return p023;
	}

	public void setP023(double p023) {
		this.p023 = p023;
	}

	public double getP024() {
		return p024;
	}

	public void setP024(double p024) {
		this.p024 = p024;
	}

	public double getP025() {
		return p025;
	}

	public void setP025(double p025) {
		this.p025 = p025;
	}

	public double getP026() {
		return p026;
	}

	public void setP026(double p026) {
		this.p026 = p026;
	}

	public double getP027() {
		return p027;
	}

	public void setP027(double p027) {
		this.p027 = p027;
	}

	public double getP028() {
		return p028;
	}

	public void setP028(double p028) {
		this.p028 = p028;
	}

	public double getP029() {
		return p029;
	}

	public void setP029(double p029) {
		this.p029 = p029;
	}

	public double getP030() {
		return p030;
	}

	public void setP030(double p030) {
		this.p030 = p030;
	}
}
