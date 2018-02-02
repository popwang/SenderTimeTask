package com.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES ��һ�ֿ�������㷨�����û���������Ϣ���ܴ��� ��ԭʼ���ݽ���AES���ܺ��ڽ���Base64����ת���� 
 * ��ʱû���õ�
 */
public class AesCBC {
	/*
	 * ��ȷ�� �����õ�Key ������26����ĸ��������� �˴�ʹ��AES-128-CBC����ģʽ��key��ҪΪ16λ��
	 */
	private static String sKey = "1234567890123456";
	private static String ivParameter = "1234567890123456";
	private static AesCBC instance = null;

	// private static
	private AesCBC() {

	}

	public static AesCBC getInstance() {
		if (instance == null)
			instance = new AesCBC();
		return instance;
	}

	// ����
	public String encrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// ʹ��CBCģʽ����Ҫһ������iv�������Ӽ����㷨��ǿ��
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
		return new BASE64Encoder().encode(encrypted);// �˴�ʹ��BASE64��ת�롣
	}

	// ����
	public String decrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// ����base64����
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, encodingFormat);
			return originalString;
		} catch (Exception ex) {
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		// ��Ҫ���ܵ��ִ�
		String cSrc = "123456";
		System.out.println("����ǰ���ִ��ǣ�" + cSrc);
		// ����
		String enString = AesCBC.getInstance().encrypt(cSrc, "utf-8", sKey, ivParameter);
		System.out.println("���ܺ���ִ��ǣ�" + enString);

		System.out.println("1jdzWuniG6UMtoa3T6uNLA==".equals(enString));

		// ����
		String DeString = AesCBC.getInstance().decrypt(enString, "utf-8", sKey, ivParameter);
		System.out.println("���ܺ���ִ��ǣ�" + DeString);
	}
}
