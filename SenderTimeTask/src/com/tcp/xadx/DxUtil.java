package com.tcp.xadx;

import java.util.Calendar;

import com.utils.ByteUtil;
import com.vo.EquipmentData;

public class DxUtil {
	public static int XOR = 169; 
	private EquipmentData e;
	
	public EquipmentData getE() {
		return e;
	}

	public void setE(EquipmentData e) {
		this.e = e;
	}

	public DxUtil(EquipmentData e) {
		this.e = e;
	}
	
	/**
	 * 获取SIM卡好字节数组
	 * @return
	 */
	public byte[] createSimCardBytes(){
		byte[] b = new byte[5];
		long phone = Long.parseLong(e.getV_equipment_name());
		b[0] = (byte)(phone/65536/65536%256);
		b[1] = (byte)(phone/65536/256%256);
		b[2] = (byte)(phone/65536%256);
		b[3] = (byte)(phone/256%256);
		b[4] = (byte)(phone%256);
		return b;
	}
	
	public static void main(String[] args) {
		//f7eebc0465
		long a = 247+65536L*65536*256;
		long b = 238+65536L*65536;
		int c = 188+65536*256;
		int d = 4+65536;
		int e = 101+256;
		System.out.println(65536*65536*256);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(a+b+c+d+e);
	}
	
	public byte[] gpsToBytes(){
		byte[] b = new byte[14];
		Calendar cal = Calendar.getInstance();
		/**
		 * 前6位位日期字节
		 */
		b[0] = (byte)cal.get(Calendar.DAY_OF_MONTH);
		b[1] = (byte)cal.get(Calendar.MONTH);
		b[2] = (byte)(cal.get(Calendar.YEAR)-2000);
		b[3] = (byte)cal.get(Calendar.HOUR_OF_DAY);
		b[4] = (byte)cal.get(Calendar.MINUTE);
		b[5] = (byte)cal.get(Calendar.SECOND);
		/**
		 * 6-9位经度int字节
		 */
		b[9] = (byte)(((int)e.getP014())%256);
		b[8] = (byte)(((int)e.getP014())/256%256);
		b[7] = (byte)(((int)e.getP014())/65536%256);
		b[6] = (byte)(((int)e.getP014())/65536/256%256);
		/**
		 * 10-13纬度int字节
		 */
		b[13] = (byte)(((int)e.getP015())%256);
		b[12] = (byte)(((int)e.getP015())/256%256);
		b[11] = (byte)(((int)e.getP015())/65536%256);
		b[10] = (byte)(((int)e.getP015())/65536/256%256);
		return b;
	}
	
	/**
	 * 计算和异或校验码
	 * @param s
	 * @return
	 */
	public byte getCheckCode(byte[] s) {
		int sum = 0;
		for(int i=0;i<s.length-1;i++) {
			sum+=s[i];
		}
		return (byte)(sum^XOR);
	}
	
	/**
	 * 获取序列号字节数组
	 * @return
	 */
	public byte[] getSequenceBytes() {
		byte[] s = new byte[18];
		s[0] = (byte)18;
		s[1] = 0x00;
		//复制sim号
		System.arraycopy(createSimCardBytes(), 0, s, 2, 5);
		//中间10位全为1
		for(int i=7;i<17;i++) {
			s[i] = 0x01;
		}
		//最后一位校验码
		s[17]=getCheckCode(s);
		return s;
	}
	
	/**
	 * 噪声数组
	 * @return
	 */
	public byte[] getNoiseBytes() {
		byte[] n = new byte[9];
		n[0] = (byte)9;
		n[1] = 0x0001;
		//复制sim号
		System.arraycopy(createSimCardBytes(), 0, n, 2, 5);
		/**
		 * 噪声数据
		 */
		n[7] = (byte)(int)e.getP008();
		//最后一位校验码
		n[8] = getCheckCode(n);
		return n;
	}
	/**
	 * pm2.5
	 * @return
	 */
	public byte[] getPM25Bytes() {
		byte[] b = new byte[10];
		b[0] = (byte)10;
		b[1] = 0x0002;
		//复制sim号
		System.arraycopy(createSimCardBytes(), 0, b, 2, 5);
		/**
		 * pm2.5数值 
		 */
		b[7] = (byte)(((int)e.getP002())/256);
		b[8] = (byte)(((int)e.getP002())%256);
		b[9] = getCheckCode(b);
		return b;
	}
	/**
	 * pm10
	 * @return
	 */
	public byte[] getPM10Bytes() {
		byte[] b = new byte[10];
		b[0] = (byte)10;
		b[1] = 0x0003;
		//复制sim号
		System.arraycopy(createSimCardBytes(), 0, b, 2, 5);
		/**
		 * pm10数值，2字节
		 */
		b[7] = (byte)(((int)e.getP003())/256);
		b[8] = (byte)(((int)e.getP003())%256);
		b[9] = getCheckCode(b);
		return b;
	}
	/**
	 * gps
	 * @return
	 */
	public byte[] getGPSBytes() {
		byte[] b = new byte[22];
		b[0] = (byte)10;
		b[1] = (byte)0x0004;
		
		//复制sim号
		System.arraycopy(createSimCardBytes(), 0, b, 2, 5);
		
		/**
		 * gps数值,14字节 ,7-20
		 */
		System.arraycopy(gpsToBytes(), 0, b, 7, 14);
		
		b[21] = getCheckCode(b);
		return b;
	}
	/**
	 * 丢包率
	 * @return
	 */
	public byte[] getDropRateBytes() {
		byte[] b = new byte[10];
		b[0] = (byte)10;
		b[1] = 0x0010;
		//复制sim号
		System.arraycopy(createSimCardBytes(), 0, b, 2, 5);
		/**
		 * 丢包率，2字节
		 */
		b[7] = 0x0000;
		b[8] = 0x0000;
		
		b[9] = getCheckCode(b);
		
		return b;
	}
}
