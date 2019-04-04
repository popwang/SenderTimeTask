package com.tcp.cd;

import com.utils.ByteUtil;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class CdUtil {
	/**
	  * 序列化
	 * @param obj
	 * @return
	 */
	public static <T> byte[] serializer(T obj) {
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = RuntimeSchema.getSchema(clazz);
            return ProtobufIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
        	e.printStackTrace();
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }
	
	/**
	 * 反序列化
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static <T> T deserializer(byte[] data, Class<T> clazz) {
        try {
            T obj = clazz.newInstance();
            Schema<T> schema = RuntimeSchema.getSchema(clazz);
            ProtobufIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
	
	public static byte[] getDataInfo(DevUpMsg dev) {
		byte[] data = CdUtil.serializer(dev);
		byte[] length = ByteUtil.shortToByteArray2((short)data.length);
		byte[] info = new byte[data.length+3];
		System.arraycopy(length, 0, info, 0, 2);
		info[2]=0b00000000;
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}
	
	public static DevUpMsg getDevUpMsg() {
		DevUpMsg dev = new DevUpMsg();
		dev.setDevsn("AZ00008888");
		dev.setAirpressure(0);
		dev.setFactcode("AZ");
		dev.setHumidity(100);
		dev.setLatitude(23.232323f);
		dev.setLongitude(118.232323f);
		dev.setNoise(45);
		dev.setPm10(233);
		dev.setPm25(333);
		dev.setStatus(true);
		dev.setTemp(20);
		dev.setTsp(45);
		dev.setWinddir(270);
		dev.setWindspeed(1.1f);
		return dev;
	}

	public static void main(String[] args) {
		DevUpMsg dev = new DevUpMsg();
		dev.setDevsn("");
		dev.setAirpressure(0);
		dev.setFactcode("");
		dev.setHumidity(100);
		dev.setLatitude(23.232323f);
		dev.setLongitude(118.232323f);
		dev.setNoise(45);
		dev.setPm10(233);
		dev.setPm25(333);
		dev.setStatus(true);
		dev.setTemp(20);
		dev.setTsp(45);
		dev.setWinddir(270);
		dev.setWindspeed(1.1f);
		byte[] data = CdUtil.serializer(dev);
		byte[] length = ByteUtil.shortToByteArray2((short)data.length);
		byte[] info = new byte[data.length+3];
		System.arraycopy(length, 0, info, 0, 2);
		info[2]=0b000000;	
		System.arraycopy(data, 0, info, 3, data.length);
		
		System.out.println(ByteUtil.bytesToHexString(info));
	}
}
