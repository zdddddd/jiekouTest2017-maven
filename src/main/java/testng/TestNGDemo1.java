package testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.*;
import java.sql.*;

public class TestNGDemo1 {

    Statement statement;

    @BeforeClass
    public void creatDBConn() throws Exception{
        String driver = "com.mysql.jdbc.Driver";
        //forName静态方法用来加载数据库驱动程序
        Class.forName(driver);

        String url = "jdbc:mysql://192.168.10.100:3306/test";
        String username = "test";
        String password = "123456";

        //getConnection()方法中的参数制定访问的数据库类型及名称
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println("数据库连接成功");

        statement = conn.createStatement();
    }

    @AfterClass
    public void closeDBConn() throws Exception{
        statement.close();
    }

    public Object[][] formatData(ResultSet resultSet) throws Exception{
        resultSet.last();
        int row = resultSet.getRow();
        Object[][] datas = new Object[row][];
        resultSet.first();
        for (int i = 0; i < row; i++) {
            datas[i] = new Object[] {resultSet.getInt(1),
                    resultSet.getInt(2),resultSet.getInt(3)};
            resultSet.next();
        }

        return datas;
    }

    @Test
    public void demo1(){
        System.out.println("TestNG哈哈哈哈");
    }

    @Test
    public void testMath1(){
        int i = TestMath.add(1, 3);
        if (i==4){
            System.out.println("计算正确");
        }else{
            System.out.println("计算错误");
        }
        Assert.assertEquals(i,4);
    }

    @Test
    public void testMath2(){
        int i = TestMath.add(1, 2);
        if (i==5){
            System.out.println("计算正确");
        }else{
            System.out.println("计算错误");
        }
        Assert.assertEquals(i,5);
    }

    @DataProvider
    public Object[][] data1(){
        return new Object[][] {
                new Object[] {3,5},
                new Object[] {2,3},
        };
    }

    @Test(dataProvider = "data1")
    public void testMath3(int a,int b){
        Assert.assertEquals(TestMath.add(a,b),5);
    }

    @DataProvider
    public Object[][] data2() throws Exception {
        ResultSet resultSet = statement.executeQuery("select * from test");
        return formatData(resultSet);
    }

    @Test(dataProvider = "data2")
    public void testMath4(int a,int b,int c){
        Assert.assertEquals(TestMath.add(a,b,c),5);
    }

}
