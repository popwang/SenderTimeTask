package com.test;

public class BinaryTest {

	public static void main(String[] args) {
//		byte b = (byte)0x15;
//		System.out.println(new Byte(b));
//
//		System.out.println(Integer.toBinaryString((b&0xff)).toString());
//
//		System.out.println(Integer.toBinaryString((b&0xff)+0x100).toString().substring(1));
//		
//		String a = "早上好";
//		byte[] bt = a.getBytes();
//		for(byte c : bt){
//			String tmp = byteToBinaryString(c);
//			System.out.println(tmp.length());
//			System.out.println(tmp);
//		}
//		
//		byte head = (byte)0x15;
//		byte cmd = (byte)0x01;
//		byte id = (byte)0;
//		byte cid = (byte)0;
//		byte tid = (byte)0;
//		String date = "{\"DeviceId\" : \"ZB-00000001\"}";
//		int datelength = date.getBytes().length;
//		byte dl = (byte)datelength;
//		byte cc = (byte)(8+datelength);
//		byte tail = (byte)0x02;
//		int length = 11+datelength;
//		byte[] data = new byte[length];
//		data[0]=head;
//		data[1]=cmd;
//		data[2]=id;
//		data[3]=cid;
//		data[4]=cid;
//		data[5]=tid;
//		data[6]=tid;
//		data[7]=dl;
//		data[8]=cmd;
//		data[1]=cmd;
//		data[1]=cmd;
		
//		short b = (short)((byte)129);
//		System.out.println(b);
		
//		String tmp = "01000100C91200320209000310170505095442010103010402010300D2030103271F6901030438700103031671010301B3820103000083010300A603D42804";
//		System.out.println(tmp.length()/2);
//		StringBuffer sb = new StringBuffer("");
//		for(int i=0;i<tmp.length();i++){
//			sb.append(tmp.charAt(i));
//			if(i%2==1){
//				sb.append(",");
//			}
//		}
//		System.out.println(sb.toString());
//		String[] strs = sb.toString().split(",");
//		for(int i=0;i<strs.length;i++){
//			strs[i]="0x"+strs[i];
//		}
//		for(String s : strs){
//			System.out.print(s+",");
//		}
		
		int i = 23938293;
		System.out.println(i&0x1F);
		System.out.println();
		System.out.println(i%32);
	}
	
	public static String byteToBinaryString(byte b){
		return Integer.toBinaryString((b&0xff)+0x100).toString().substring(1);
	}
	
	/**
	 * 将byte数组转成十六进制的字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 将byte数组转成二进制的字符串
	 * @param bytes
	 * @return
	 */
	public static String byteToBinaryString(byte[] bytes) {
		String str = "";
		for (byte b : bytes) {
			str += Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);
		}
		return str;
	}
}
