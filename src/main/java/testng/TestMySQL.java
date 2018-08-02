package testng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestMySQL {
    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        //forName静态方法用来加载数据库驱动程序
        Class.forName(driver);

        String url = "jdbc:mysql://192.168.10.100:3306/test";
        String username = "test";
        String password = "123456";

        //getConnection()方法中的参数制定访问的数据库类型及名称
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println("数据库连接成功");

        Statement statement = conn.createStatement();
        //几个常用的
        //纯执行
//        statement.execute("insert into test values (1,2,3)");
        //执行后返回查询数据
//        ResultSet resultSet = statement.executeQuery("select * from test");
        //执行后返回影响条数
//        int i = statement.executeUpdate("insert into test values (3,2,3)");

        ResultSet resultSet = statement.executeQuery("select * from test");
        while (resultSet.next()){
            System.out.println(resultSet.getString("1"));
            System.out.println(resultSet.getInt("1"));
            System.out.println(resultSet.getString(3));//从1开始
        }

        conn.close();
    }
}
