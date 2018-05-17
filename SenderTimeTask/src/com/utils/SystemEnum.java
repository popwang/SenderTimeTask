package com.utils;
/**
 * 对接平台系统枚举类
 * @author Administrator
 */
public enum SystemEnum {
	AY_SM_SYSTEM(1,"安阳三门峡"),
	AY_HX_SYSTEM(2,"安阳滑县"),
	ZZ_TRA_SYSTEM(3,"郑州交委"),
	SD_JN_SYSTEM(4,"山东济宁"),
	PDS_SYSTEM(5,"河南平顶山"),
	ZZ_TBJ_SYSTEM(6,"郑州特比佳"),
	SD_RZ_SYSTEM(7,"山东日照"),
	LY_RY_SYSTEM(8,"洛阳汝阳"),
	HA_KF_SYSTEM(9,"河南开封"),
	HA_XY_SYSTEM(10,"河南信阳"),
	HA_ZK_SYSTEM(11,"河南周口"),
	HB_SJZ_SYSTEM(12,"河北石家庄"),
	SX_XA_SYSTEM(13,"陕西西安"),
	XA_GXQ_SYSTEM(14,"陕西西安高新区"),
	SD_JINAN_SYSTEM(15,"山东济南"),
	XA_DXQ_SYSTEM(16,"陕西西安大兴区"),
	HA_KF2_SYSTEM(17,"河南开封2"),
	HN_CS_SYSTEM(18,"湖南长沙"),
	XA_LT_SYSTEM(19,"陕西西安临潼区"),
	SD_HZ_SYSTEM(20,"山东菏泽"),
	XA_BQ_SYSTEM(21,"陕西西安灞桥区"),
	LY_YC_SYSTEM(22,"洛阳伊川"),
	XA_LH_SYSTEM(23,"陕西西安莲湖区"),
	SX_YC_SYSTEM(24,"山西运城"),
	HA_NY_SYSTEM(25,"河南南阳"),
	SX_WN_SYSTEM(26,"陕西渭南"),
	GD_SZ_SYSTEM(27,"广东深圳"),
	XA_DY_SYSTEM(28,"陕西西安雁塔区"),
	AY_HX2_SYSTEM(29,"安阳滑县2"),
	ZZ_GY_SYSTEM(30,"郑州巩义"),
	ZK_XC_SYSTEM(31,"周口项城"),
	WEATHER_SYSTEM(99,"天气预报");
	
	private int id;
	private SystemEnum(int id,String name){
		this.id = id;
		this.name = name;
	}
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public static void main(String[] args){
		System.out.println(SystemEnum.AY_HX_SYSTEM);
	}
	
}
