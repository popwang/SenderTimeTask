package com.vo;

public class EquipmentData {
	private String v_equipment_name;
	private String v_project_name;
	private Integer i_equipment_id;
	//������״̬
	private double p001;
	//PM2.5
	private double p002;
	//PM10
	private double p003;
	//����
	private double p004;
	//����
	private double p005;
	//�¶�
	private double p006;
	//ʪ��
	private double p007;
	//����
	private double p008;
	//PM100
	private double p009;
	//��ѹ
	private double p010;
	//����3
	private double p011;
	//����4
	private double p012;
	//����5
	private double p013;
	//����
	private double p014;
	//γ��
	private double p015;
	private String dtm_update;
	private String dtm_setdate;
	
	@Override
	public String toString(){
		return "״̬��"+this.getP001()+
				"��PM2.5��"+ this.getP002()+
				"��PM10��"+ this.getP003()+
				"�����٣�"+ this.getP004()+
				"������"+ this.getP005()+
				"���¶ȣ�"+ this.getP006()+
				"��ʪ�ȣ�"+ this.getP007()+
				"��������"+ this.getP008()+
				"��PM100��"+ this.getP009()+
				"����ѹ��"+ this.getP010();
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
	 * γ��
	 * @return
	 */
	public double getP015() {
		return p015;
	}

	public void setP015(double p015) {
		this.p015 = p015;
	}
	/**
	 * ��ѹ
	 * @return
	 */
	public double getP010() {
		return p010;
	}

	public void setP010(double p010) {
		this.p010 = p010;
	}

	/**
	 * �����ȼ�
	 * @return
	 */
	public double getP011() {
		if(this.p004<0.3){
			p011=0;
		}else if(this.p004>=0.3 && this.p004<1.6){
			p001=1;
		}else if(this.p004>=1.6 && this.p004<3.4){
			p001=2;
		}else if(this.p004>=3.4 && this.p004<5.5){
			p001=3;
		}else if(this.p004>=5.5 && this.p004<8.0){
			p001=4;
		}else if(this.p004>=8.0 && this.p004<10.8){
			p001=5;
		}else if(this.p004>=10.8 && this.p004<13.9){
			p001=6;
		}else if(this.p004>=13.9 && this.p004<17.2){
			p001=7;
		}else if(this.p004>=17.2 && this.p004<20.8){
			p001=8;
		}else if(this.p004>=20.8 && this.p004<24.5){
			p001=9;
		}else if(this.p004>=24.5 && this.p004<28.5){
			p001=10;
		}else if(this.p004>=28.5 && this.p004<32.7){
			p001=11;
		}else if(this.p004>=32.7){
			p001=12;
		}
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
	 * //����
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
	 * ������״̬
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
	 * ����
	 * @return
	 */
	public double getP004() {
		return p004;
	}
	public void setP004(double p004) {
		this.p004 = p004;
	}
	/**
	 * ����
	 * @return
	 */
	public double getP005() {
		return p005;
	}
	
	public void setP005(double p005) {
		this.p005 = p005;
	}
	/**
	 * �¶�
	 * @return
	 */
	public double getP006() {
		return p006;
	}
	public void setP006(double p006) {
		this.p006 = p006;
	}
	/**
	 * ʪ��
	 * @return
	 */
	public double getP007() {
		return p007;
	}
	public void setP007(double p007) {
		this.p007 = p007;
	}
	/**
	 * ����
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
}
