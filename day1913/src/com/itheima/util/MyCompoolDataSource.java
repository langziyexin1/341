package com.itheima.util;



import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MyCompoolDataSource {
    static DataSource comboPooledDataSource=new ComboPooledDataSource();


    private MyCompoolDataSource() {
    }
  public static Connection getConnection() throws SQLException {
      Connection connection = comboPooledDataSource.getConnection();
      return connection;
  }
    //    释放资源
    public static  void close(AutoCloseable ...c){
        for (AutoCloseable closeable : c) {

            if (closeable != null) {
                try {
                    closeable.close();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

}
