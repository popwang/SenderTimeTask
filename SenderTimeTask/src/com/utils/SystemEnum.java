package com.utils;
/**
 * �Խ�ƽ̨ϵͳö����
 * @author Administrator
 */
public enum SystemEnum {
	AY_SM_SYSTEM(1,"��������Ͽ"),
	AY_HX_SYSTEM(2,"��������"),
	ZZ_TRA_SYSTEM(3,"֣�ݽ�ί"),
	SD_JN_SYSTEM(4,"ɽ������"),
	PDS_SYSTEM(5,"����ƽ��ɽ"),
	ZZ_TBJ_SYSTEM(6,"֣���رȼ�"),
	SD_RZ_SYSTEM(7,"ɽ������"),
	LY_RY_SYSTEM(8,"��������"),
	HA_KF_SYSTEM(9,"���Ͽ���"),
	HA_XY_SYSTEM(10,"��������"),
	HA_ZK_SYSTEM(11,"�����ܿ�"),
	HB_SJZ_SYSTEM(12,"�ӱ�ʯ��ׯ"),
	SX_XA_SYSTEM(13,"��������"),
	XA_GXQ_SYSTEM(14,"��������������"),
	SD_JINAN_SYSTEM(15,"ɽ������"),
	XA_DXQ_SYSTEM(16,"��������������"),
	HA_KF2_SYSTEM(17,"���Ͽ���2"),
	HN_CS_SYSTEM(18,"���ϳ�ɳ"),
	XA_LT_SYSTEM(19,"��������������"),
	SD_HZ_SYSTEM(20,"ɽ������"),
	XA_BQ_SYSTEM(21,"�������������"),
	LY_YC_SYSTEM(22,"��������"),
	XA_LH_SYSTEM(23,"��������������"),
	SX_YC_SYSTEM(24,"ɽ���˳�"),
	HA_NY_SYSTEM(25,"��������"),
	SX_WN_SYSTEM(26,"����μ��"),
	GD_SZ_SYSTEM(27,"�㶫����"),
	XA_DY_SYSTEM(28,"��������������"),
	AY_HX2_SYSTEM(29,"��������2"),
	ZZ_GY_SYSTEM(30,"֣�ݹ���"),
	ZK_XC_SYSTEM(31,"�ܿ����"),
	WN_HC_SYSTEM(32,"μ�Ϻ���"),
	XA_QD_SYSTEM(33,"�����ض���"),
	AY_NEW_SYSTEM(34,"������ƽ̨"),
	ZZ_XC_SYSTEM(35,"֣���߼��ƽ̨"),
	SC_CQ_SYSTEM(36,"�����ɿ���ƽ̨"),
	HA_DZ_SYSTEM(37,"���ϵ���"),
	NX_YC_SYSTEM(38,"��������"),
	GD_SZ2_SYSTEM(39,"�㶫����2"),
	XA_BQ2_SYSTEM(40,"���������������ƽ̨"),
	ZZ_XC2_SYSTEM(41,"֣���߼��ƽ̨2"),
	XA_INTER_SYSTEM(42,"�������ʸ�����"),
	XA_BL_SYSTEM(43,"����������"),
	XA_QJ_SYSTEM(44,"����������"),
	ZZ_ZB_SYSTEM(45,"֣���а�ƽ̨"),
	ZZ_ZJ_SYSTEM(46,"֣���н��ľ�"),
	SC_CQ2_SYSTEM(47,"����������ƽ̨"),
	HA_LY_SYSTEM(48,"�����м�����ƽ̨"),
	HA_PY_SYSTEM(49,"�����ƽ̨"),
	HB_WH_SYSTEM(50,"�����人ƽ̨"),
	ZZ_SIX_SYSTEM(51,"֣��6����ƽ̨"),
	LY_MJ_SYSTEM(52,"�����Ͻ�ס����ƽ̨"),
	SX_YC2_SYSTEM(53,"ɽ���˳�2"),
	ZZ_TBJ2_SYSTEM(54,"֣���رȼ�2"),
	SX_XX_SYSTEM(55,"������������"),
	ZK_SQ_SYSTEM(56,"�����ܿ�����ƽ̨"),
	SC_CD_SYSTEM(57,"�Ĵ��ɶ�ˮԴ���ƽ̨"),
	HB_XA_SYSTEM(58,"�ӱ��۰�ƽ̨"),
	INSERT_SYSTEM(98,"�����滻"),
	WEATHER_SYSTEM(99,"����Ԥ��");
	
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
