package com.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NioSocketProxy {
	public static Log log = LogFactory.getLog(NioSocketProxy.class);
	
	public static long TIMEOUT = 5*1000;
	public static void main(String[] args) {
		try {
			NioSocketProxy sp = new NioSocketProxy("127.0.0.1",8888);
			int i=0;
			while(i<5){
				sp.sendDataToRemote("##0114QN=20170831094525902;ST=22;CN=2011;PW=123456;MN=ZBLW00000197;CP=&&DataTime=20170831094525;40-Rtd=58.0,40-Flag=N;&&7340");
				i++;
			}
			sp.closeSocket();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private SocketChannel sc;

	public NioSocketProxy(String ip, int port) throws IOException {
		this.sc = SocketChannel.open();
		this.sc.configureBlocking(false);
		this.sc.connect(new InetSocketAddress(ip, port));
		while(!this.sc.finishConnect()){
			log.info("socket��ʼ����...");
		}
		log.info("socket��ʼ����ɣ�");
	}

	public void sendDataToRemote(String info) throws IOException {
		log.info("�������ݣ�"+info);
		ByteBuffer writeBuf = ByteBuffer.allocate(200);
		ByteBuffer readBuf = ByteBuffer.allocate(200);
		
		while (writeBuf.hasRemaining()) {
			this.sc.write(writeBuf);
		}
		StringBuffer stringBuffer = new StringBuffer();
		// ���read�������յ�-1����������˹رգ��׳��쳣
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis()-startTime<TIMEOUT ||
									(this.sc.read(readBuf)) > 0 ) {
			readBuf.flip();
			stringBuffer.append(new String(readBuf.array(), 0, readBuf.limit()));
			readBuf.clear();
		}
		// ��ӡ�����յ�������
		log.info("��������: " + stringBuffer.toString());
	}
	
	public void closeSocket(){
		try {
			this.sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
