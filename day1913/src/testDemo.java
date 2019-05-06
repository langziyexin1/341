import com.itheima.util.JDBCUtils;
import com.itheima.util.MyCompoolDataSource;
import org.junit.Test;

import javax.naming.Name;
import java.awt.*;
import java.sql.*;

public class testDemo {
    @Test
    public void addone(){
        Connection conn=null;
        Statement statement=null;
        PreparedStatement preparedStatement=null;
        int rows = 0;

        try {
// 创建连接
            conn = JDBCUtils.getConnection();
            //创建sql语句执行对象
            statement = conn.createStatement();
            //执行插入操作
            String sql="insert into demo(id,name,demo_param) values(4,'新建的','bh')";
            rows = statement.executeUpdate(sql);
        System.out.println("成功插入影响了"+rows+"行");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        JDBCUtils.release(conn,statement);
        }
    }
    @Test
    public void delete(){
        Connection conn=null;
        PreparedStatement preparedStatement=null;
        int[] str={4};
        try {
            conn= JDBCUtils.getConnection();
//            String sql="DELETE FROM demo where id=?";
            String sql="DELETE FROM demo where name=?";
            preparedStatement = conn.prepareStatement(sql);
//           preparedStatement.setInt(1,5);
//            第一个参数是代表第几个问好
           preparedStatement.setString(1,"lili");
            int i = preparedStatement.executeUpdate();

            System.out.println("删除成功");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        JDBCUtils.release(conn,preparedStatement);
        }

    }
    @Test
    public void select(){
        Connection conn=null;
        Statement statement=null;
        ResultSet resultSet=null;
        int rows = 0;

        try {
// 创建连接
            conn = JDBCUtils.getConnection();
            //创建sql语句执行对象
            statement = conn.createStatement();
            //执行插入操作
            String sql="SELECT * from demo";
          resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Object id = resultSet.getObject("id");
                Object name = resultSet.getObject("name");
                Object demo_param = resultSet.getObject("demo_param");
               System.out.println("id="+id+",demo_param="+demo_param+",name="+ name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        JDBCUtils.release(conn,statement,resultSet);
        }
    }
//    线程池添加一条数据
    @Test
    public void addComOne(){
        Connection conn=null;
        PreparedStatement pps=null;


        try {
            conn = MyCompoolDataSource.getConnection();
            String sql="insert into demo VALUES (NULL,?,?);";
            pps= conn.prepareStatement(sql);
            pps.setString(1,"萧炎");
            pps.setString(2,"谣言");
           int rs = pps.executeUpdate();
        if (rs>0) {

        System.out.println("成功添加"+rs);
        pps.close();
        conn.close();
        }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
