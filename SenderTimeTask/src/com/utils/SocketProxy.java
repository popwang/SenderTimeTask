package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 测试类，尝试连接复用，已被SocketUtil代替
 * @author pactera
 *
 */
public class SocketProxy {
	public static Log log = LogFactory.getLog(SocketProxy.class);
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	public static long TIMEOUT = 1*1000;
	
	public SocketProxy(String ip,int port) throws UnsupportedEncodingException, IOException {
		this.socket = new Socket(ip,port);
		this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
		this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		log.info("发送代理初始化完毕！");
	}
	
	public static void main(String[] args){
		try {
			SocketProxy sp = new SocketProxy("123.15.58.210",9123);
			int i=0;
			while(i<5){
				sp.sendDataToRemote("STX06601|ZB|V1.0|ZB00000117|21.7|78.8|60.0|74.0|0|52.9|0.0|NE|ZB00000117ETX");
				i++;
			}
			sp.closeSocket();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * readLine方法会一直阻塞，OMG
	 * @param info
	 */
	public void sendDataToRemote(String info){
		try {
			log.info("发送内容:"+info);
			pw.write(info);
			pw.flush();
			String tmp = null;
			long minus = 0;
			long beginTime = System.currentTimeMillis();
			boolean flag = true;
			while(flag){
				log.info("step1");
				if((tmp=br.readLine())!=null){
					log.info("返回内容:"+tmp);
					flag = false;
				}
				log.info("step2");
				minus = System.currentTimeMillis()-beginTime;
				if(minus>TIMEOUT){
					log.info("超时返回!");
					flag = false;
				}
			}
		} catch (IOException e) {
			log.info(e.getMessage(),e);
		}
	}
	
	public void closeSocket(){
		try {
			this.br.close();
			this.pw.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			this.br = null;
			this.pw = null;
			this.socket = null;
		}
	}
}
