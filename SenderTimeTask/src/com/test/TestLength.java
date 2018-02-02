package com.test;

public class TestLength {

	public static void main(String[] args) {
		byte[] data = {0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37};
		int crc = 0xFFFF;
		int tmp;
		for(int i=0;i<8;i++){
			if(((crc & 0x0001)^(data[i]&0x01))!=0){
				tmp = 0x8408;
			}else{
				tmp = 0x0000;
			}
			crc = crc>>1;
			crc = crc^tmp;
		}
		
	}
	
	public static int GetCRC3(byte[] data) {  
        int high;  
        int flag;  
        int wcrc = 0xffff;  
        for (int i = 0; i < data.length; i++) {
            high = wcrc >> 1;  
            wcrc = high ^ data[i];
            for (int j = 0; j < 8; j++) { 
                flag = (wcrc & 0x0001);
                wcrc = wcrc >> 1;
            	
                if (flag == 1) 
                    wcrc ^= 0x8408;
                else 
                	wcrc ^= 0x0000;
            }  
        }  
        return wcrc;  
	} 

}
