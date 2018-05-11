package com.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 开封数据对接工具类，包含一些通用的字节转换方法，与开封协议耦合较多。
 * @author pactera
 *
 */
public class ByteUtil {
	
	public static Log log = LogFactory.getLog(ByteUtil.class);
	/**
	 * 头字节
	 */
	public static byte HEAD = 0x15;
	/**
	 * 控制码-注册
	 */
	public static byte CMD_R = 0x01;
	/**
	 * 控制码-噪声发送
	 */
	public static byte CMD_V = 0x03;
	/**
	 * 控制码-扬尘发送
	 */
	public static byte CMD_P = 0x05;
	/**
	 * 帧流水号
	 */
	public static byte ID = 0;
	/**
	 * 当前帧序号
	 */
	public static byte CID = 0;
	/**
	 * 总帧数
	 */
	public static byte TID = 0;
	/**
	 * 尾字节
	 */
	public static byte TAIL = 0x02;
	/**
	 * 每帧的辅助字节数
	 */
	public static int OTHERS = 11;
	
	public static byte[] HEADERS = new byte[]{HEAD,CMD_R,ID,CID,CID,TID,TID};
	
	public static void main(String[] args) {
		System.out.println(getEpcCode("720"));
		System.out.println(getEpcCode("760"));
	}
	
	/**
	 * 生成深圳EPC设备编号
	 * @param equipmentcode
	 * @return
	 */
	public static String getEpcCode(String equipmentcode){
		byte[] bytes = new byte[12];
		bytes[0] = 0x0E;
		System.arraycopy("ZBLWYCJC".getBytes(), 0, bytes, 1, 8);
		System.arraycopy(equipmentcode.getBytes(), 0, bytes, 9, 3);
		return bytesToHexString(bytes).toUpperCase();
	}
	
	/**
	 * 将short类型转为长度为2的byte数组
	 * @param a
	 * @return
	 */
	public static byte[] shortToByteArray(short a) {  
	    return new byte[] {
	        (byte) ((a >> 8) & 0xFF),
	        (byte) (a & 0xFF)  
	    };
	}
	
	/**
	 * int转byte数组，大端序
	 * @param a
	 * @return
	 */
	public static byte[] intToByteArray(int a) {  
	    return new byte[] {  
	        (byte) ((a >> 24) & 0xFF),  
	        (byte) ((a >> 16) & 0xFF),     
	        (byte) ((a >> 8) & 0xFF),     
	        (byte) (a & 0xFF)  
	    };  
	} 
	
	/**
	 * 将byte数组转成十六进制的字符串
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
	public static String byteToBinaryString(byte[] bytes){
		String str = "";
		for(byte b : bytes){
			str +=Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);
		}
		return str;
	}
	
    public static String byteToBinaryString2(byte[] bytes){
    	String str = "";
    	for(byte b : bytes){
    		str +=Integer.toBinaryString(b);
    	}
    	return str;
    }
    /**
     * 根据操作类型，组装三类发送信息byte数组
     * @return
     */
    public static byte[] getSendDataBytes(String einfo,int operator){
    	//数据域信息
		byte[] ebyte = einfo.getBytes();
		int esize = ebyte.length;
		//数据域长度信息
		short dl = (short)esize;
		byte[] dlbyte = shortToByteArray(dl);
		
		int datasize = OTHERS + esize;
		byte[] data = new byte[datasize];
		if(operator==CMD_R){
			arrayCopy(getRegisterHeaderBytes(),data);
		}else if(operator==CMD_V){
			arrayCopy(getVoiceHeaderBytes(),data);
		}else if(operator==CMD_P){
			arrayCopy(getPmHeaderBytes(),data);
		}
		data[7]=dlbyte[1];
		data[8]=dlbyte[0];
		System.arraycopy(einfo.getBytes(), 0, data, 9, esize);
		data[datasize-2] = getCheckCode(data);
		data[datasize-1] = TAIL;
		log.info(bytesToHexString(data));
		return data;
    }
    
    /**
	 * 获取校验码,校验码为数据字段各字节相加，与256取模
	 * @param bytes
	 * @return
	 */
	public static byte getCheckCode(byte[] bytes){
		byte a = 0;
		for(int i=0;i<bytes.length;i++){
			if(i==0 || i==bytes.length-2 || i==bytes.length-1){
				continue;
			}
			a += bytes[i];
		}
		a = (byte)(a%256);
//		System.out.println(byteToBinaryString(new byte[]{a}));
		return a;
	}
	
    public static void arrayCopy(byte[] header,byte[] data){
    	System.arraycopy(header, 0, data, 0, HEADERS.length);
    }
    
    /**
     * 获取注册信息的头信息
     * @return
     */
    public static byte[] getRegisterHeaderBytes(){
    	byte[] bytes = HEADERS;
    	bytes[1] = CMD_R;
    	return HEADERS;
    }
    /**
     * 获取噪声信息的头信息
     * @return
     */
    public static byte[] getVoiceHeaderBytes(){
    	byte[] bytes = HEADERS;
    	bytes[1] = CMD_V;
    	return bytes;
    }
    /**
     * 获取扬尘信息的头信息
     * @return
     */
    public static byte[] getPmHeaderBytes(){
    	byte[] bytes = HEADERS;
    	bytes[1] = CMD_P;
    	return bytes;
    }
    
}
