package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteBat {

	public static void main(String[] args) {
		
		try {
			String file = "c:\\install.bat";
			BufferedReader br = new BufferedReader(new FileReader(new File(file)));
			String tmp = "";
			int i = 0;
			while((tmp=br.readLine())!=null){
				i++;
				System.out.println("��ǰִ�����"+tmp);
				Runtime.getRuntime().exec(tmp);
			}
			System.out.println("��������ִ����ɣ���ִ�У�"+i+"����");
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
