package com.test.code;

public class CharacterDemo {
	public static void main(String[] args) {
		char ctrlChar0 = '\u0000';
		char ctrlChar1 = '\u0001';
		char ctrlChar2 = '\u0002';
        char ctrlChar3 = '\u0003';
        char ctrlChar5 = '\u0005';
        char tab = '\u0009';
        char ni = '\u4f60';
        char hao = '\u597d';
        System.out.println(ctrlChar0);
        System.out.println(ctrlChar1);
        System.out.println(ctrlChar2);
        System.out.println(ctrlChar3);
        System.out.println('\u0005');
        System.out.println(ctrlChar1 + ctrlChar5); // 6, ???????char???, ??????code????
        System.out.println(ctrlChar1 + "" + ctrlChar5); // 
        System.out.println(tab+1); // 10
        System.out.println("ha"+tab+"ha"); // ha    ha
        System.out.println("ha"+"\t"+"ha");// ha    ha 
        System.out.println(ni + hao); //  43229
        System.out.println(ctrlChar5 + "" + ni + hao); // ???
    }
}
