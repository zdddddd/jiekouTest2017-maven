package jsonToEntity;

import com.alibaba.fastjson.JSON;
import org.testng.annotations.Test;

public class EntityToJson {
    @Test
    public void test1(){
        Student student = new Student();
        student.setId(1111);
        student.setName("张三");
        student.setAddress("北京");

        String s = JSON.toJSONString(student);
        System.out.println(s);
    }
}
