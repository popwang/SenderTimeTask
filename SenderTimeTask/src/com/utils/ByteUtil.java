package com.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * �������ݶԽӹ����࣬����һЩͨ�õ��ֽ�ת���������뿪��Э����Ͻ϶ࡣ
 * @author pactera
 *
 */
public class ByteUtil {
	
	public static Log log = LogFactory.getLog(ByteUtil.class);
	/**
	 * ͷ�ֽ�
	 */
	public static byte HEAD = 0x15;
	/**
	 * ������-ע��
	 */
	public static byte CMD_R = 0x01;
	/**
	 * ������-��������
	 */
	public static byte CMD_V = 0x03;
	/**
	 * ������-�ﳾ����
	 */
	public static byte CMD_P = 0x05;
	/**
	 * ֡��ˮ��
	 */
	public static byte ID = 0;
	/**
	 * ��ǰ֡���
	 */
	public static byte CID = 0;
	/**
	 * ��֡��
	 */
	public static byte TID = 0;
	/**
	 * β�ֽ�
	 */
	public static byte TAIL = 0x02;
	/**
	 * ÿ֡�ĸ����ֽ���
	 */
	public static int OTHERS = 11;
	
	public static byte[] HEADERS = new byte[]{HEAD,CMD_R,ID,CID,CID,TID,TID};
	
	public static void main(String[] args) {
		System.out.println(getEpcCode("720"));
		System.out.println(getEpcCode("760"));
	}
	
	/**
	 * ��������EPC�豸���
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
	 * ��short����תΪ����Ϊ2��byte����
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
	 * intתbyte���飬�����
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
	 * ��byte����ת��ʮ�����Ƶ��ַ���
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
     * ��byte����ת�ɶ����Ƶ��ַ���
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
     * ���ݲ������ͣ���װ���෢����Ϣbyte����
     * @return
     */
    public static byte[] getSendDataBytes(String einfo,int operator){
    	//��������Ϣ
		byte[] ebyte = einfo.getBytes();
		int esize = ebyte.length;
		//�����򳤶���Ϣ
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
	 * ��ȡУ����,У����Ϊ�����ֶθ��ֽ���ӣ���256ȡģ
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
     * ��ȡע����Ϣ��ͷ��Ϣ
     * @return
     */
    public static byte[] getRegisterHeaderBytes(){
    	byte[] bytes = HEADERS;
    	bytes[1] = CMD_R;
    	return HEADERS;
    }
    /**
     * ��ȡ������Ϣ��ͷ��Ϣ
     * @return
     */
    public static byte[] getVoiceHeaderBytes(){
    	byte[] bytes = HEADERS;
    	bytes[1] = CMD_V;
    	return bytes;
    }
    /**
     * ��ȡ�ﳾ��Ϣ��ͷ��Ϣ
     * @return
     */
    public static byte[] getPmHeaderBytes(){
    	byte[] bytes = HEADERS;
    	bytes[1] = CMD_P;
    	return bytes;
    }
    
}
