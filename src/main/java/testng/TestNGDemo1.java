package testng;

import org.testng.annotations.Test;

public class TestNGDemo1 {

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
    }

    @Test
    public void testMath2(){
        int i = TestMath.add(1, 2);
        if (i==5){
            System.out.println("计算正确");
        }else{
            System.out.println("计算错误");
        }
    }
}
