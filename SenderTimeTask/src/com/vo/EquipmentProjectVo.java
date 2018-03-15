package com.vo;

public class EquipmentProjectVo {
	private String v_equipment_name;
	private String v_project_name;
	private String v_url;
	private String v_system;
	private String dtm_add;
	private Integer i_system_id;
	private String v_real_equipment_name;
	
	public String getV_real_equipment_name() {
		return v_real_equipment_name;
	}
	public void setV_real_equipment_name(String v_real_equipment_name) {
		this.v_real_equipment_name = v_real_equipment_name;
	}
	/**
	 * @return the v_equipment_name
	 */
	public String getV_equipment_name() {
		return v_equipment_name;
	}
	/**
	 * @param v_equipment_name the v_equipment_name to set
	 */
	public void setV_equipment_name(String v_equipment_name) {
		this.v_equipment_name = v_equipment_name;
	}
	/**
	 * @return the v_project_name
	 */
	public String getV_project_name() {
		return v_project_name;
	}
	/**
	 * @param v_project_name the v_project_name to set
	 */
	public void setV_project_name(String v_project_name) {
		this.v_project_name = v_project_name;
	}
	/**
	 * @return the v_url
	 */
	public String getV_url() {
		return v_url;
	}
	/**
	 * @param v_url the v_url to set
	 */
	public void setV_url(String v_url) {
		this.v_url = v_url;
	}
	/**
	 * @return the v_system
	 */
	public String getV_system() {
		return v_system;
	}
	/**
	 * @param v_system the v_system to set
	 */
	public void setV_system(String v_system) {
		this.v_system = v_system;
	}
	/**
	 * @return the dtm_add
	 */
	public String getDtm_add() {
		return dtm_add;
	}
	/**
	 * @param dtm_add the dtm_add to set
	 */
	public void setDtm_add(String dtm_add) {
		this.dtm_add = dtm_add;
	}
	/**
	 * @return the i_system_id
	 */
	public Integer getI_system_id() {
		return i_system_id;
	}
	/**
	 * @param i_system_id the i_system_id to set
	 */
	public void setI_system_id(Integer i_system_id) {
		this.i_system_id = i_system_id;
	}
}
