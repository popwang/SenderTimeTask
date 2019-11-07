package com.test.aop.utils;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;

/**
 * ���ӵĹ����࣬�����ڴ�����Դ�л�ȡһ�����ӣ�����ʵ�ֺ��̵߳İ�
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private static BasicDataSource dataSource = new BasicDataSource();

    //��̬�����,�����������ݿ�Ĳ���
    static{
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }


    /**
     * ��ȡ��ǰ�߳��ϵ�����
     * @return
     */
    public Connection getThreadConnection() {
        try{
            //1.�ȴ�ThreadLocal�ϻ�ȡ
            Connection conn = tl.get();
            //2.�жϵ�ǰ�߳����Ƿ�������
            if (conn == null) {
                //3.������Դ�л�ȡһ�����ӣ����Ҵ���ThreadLocal��
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            //4.���ص�ǰ�߳��ϵ�����
            return conn;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * �����Ӻ��߳̽��
     */
    public void removeConnection(){
        tl.remove();
    }
}
