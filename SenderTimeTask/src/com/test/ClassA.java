package com.test;


/**
 * 静态代码块-普通代码块-构造函数
 * 
 * 静态的是在类加载时执行，由于双亲委派，先加载父类后加载子类，因此静态的父类先执行，子类后执行；
 * 然后开始执行父类的构造函数，普通代码块先执行，再执行构造函数；
 * 再执行子类构造函数，同样的普通代码块先执行，再执行构造函数。
 * 输出顺序：
 * 	静态代码块B
	静态代码块A
	普通的代码块B
	构造器B
	普通的代码块A
	构造器A
 * */
public class ClassA extends B{
    public ClassA() {
        super();
        System.out.println("构造器A");
    }
    {
        System.out.println("普通的代码块A");
    }
    static{
        System.out.println("静态代码块A");
    }

    public static void main(String[] args) {
        new ClassA();
    }
}

class B{
    public B(){
        super();
        System.out.println("构造器B");
    }
    {
        System.out.println("普通的代码块B");
    }

    static{
        System.out.println("静态代码块B");
    }
}

