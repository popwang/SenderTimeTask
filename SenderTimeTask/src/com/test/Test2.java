package com.test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.utils.CommonUtil;
import com.utils.ConfigReader;


public class Test2 {
	public static Log logger = LogFactory.getLog(Test2.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String info = "{\"appId\":\"datacollect\",\"appKey\":\"123456789\",\"datatype\":\"RAISEDUST\",\"datakind\":\"MinData\",\"data\":[{\"SO2\":\"18\",\"NO2\":\"17\",\"PM10\":\"141\",\"PM2_5\":\"65\",\"CO\":\"1.0890\",\"O3\":\"97\",\"WS\":null,\"WD\":null,\"PRESSURE\":null,\"TEMP\":null,\"HUM\":null,\"SITECODE\":\"41010603\",\"SITENAME\":\"上街区政府\",\"MONITORTIME\":\"2018-05-07 10:20:00\",\"SITEID\":\"41010603\"},{\"SO2\":\"22\",\"NO2\":\"23\",\"PM10\":\"124\",\"PM2_5\":\"60\",\"CO\":\"0.9040\",\"O3\":\"96\",\"WS\":null,\"WD\":null,\"PRESSURE\":null,\"TEMP\":null,\"HUM\":null,\"SITECODE\":\"41011101\",\"SITENAME\":\"北区建设指挥部\",\"MONITORTIME\":\"2018-05-07 10:20:00\",\"SITEID\":\"41011101\"}]}";
		CommonUtil.sendDataToRemote("218.28.94.157",
				60009, info, logger);
		
	}
	
	public static byte[] getByteData(){
		byte head = 0x15;
		byte cmd = 0x01;
		byte id = 0;
		byte cid = 0;
		byte tid = 0;
		String date = "{\"DeviceId\":\"ZB00000299\"}";
		int datelength = date.getBytes().length;
		short dl = (short)datelength;
		byte[] dlb = shortToByteArray(dl);
		byte tail = 0x02;
		int length = 11+datelength;
		byte[] data = new byte[length];
		data[0]=head;
		data[1]=cmd;
		data[2]=id;
		data[3]=cid;
		data[4]=cid;
		data[5]=tid;
		data[6]=tid;
		data[7]=dlb[1];
		data[8]=dlb[0];
		System.arraycopy(date.getBytes(), 0, data, 9, datelength);
		data[length-2] = getCheckCode(data);
		data[length-1] = tail;
		System.out.println(bytesToHexString(data));
		return data;
	}
	/**
	 * 获取校验码
	 * @param bytes
	 * @return
	 */
	public static byte getCheckCode(byte[] bytes){
		int a = 0;
		for(int i=0;i<bytes.length;i++){
			if(i==0 || i==bytes.length-2 || i==bytes.length-1){
				continue;
			}
			a += bytes[i];
		}
		return (byte)(a%256);
	}
	
	public static byte[] shortToByteArray(int a) {  
	    return new byte[] {
	        (byte) ((a >> 8) & 0xFF),     
	        (byte) (a & 0xFF)  
	    };
	}
	
	public static void sendDataToRemote(String ip,int port,byte[] bytes,Log log) {
		try {
			Socket socket = new Socket(ip,port);
			log.info("connect success");
			OutputStream os = socket.getOutputStream();
			InputStream is = new DataInputStream(socket.getInputStream());
			os.write(bytes);
			log.info("send over");
			socket.shutdownOutput();
			byte[] b = new byte[100];
			while (-1 != is.read(b)) {
				System.out.println(byteToBinaryString(b));
            }
			is.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			log.info(e.getMessage(),e);
		} catch (IOException e) {
			log.info(e.getMessage(),e);
		} finally {
			
		}
	}
	
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

    public static String bytesHexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase()+"\n";
        }
        return ret;
    }
    
    public static String byteToBinaryString(byte[] bytes){
    	String str = "";
    	for(byte b : bytes){
    		str +=Integer.toBinaryString((b & 0xFF) + 0x100).substring(1)+"\n";
    	}
    	return str;
    }
}
