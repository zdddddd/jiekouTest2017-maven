package jsonToEntity;

import com.alibaba.fastjson.JSON;
import org.testng.annotations.Test;

public class JsonToEntity {

    @Test
    public void test1(){

        String s = "{\"address\":\"北京\",\"id\":1111,\"name\":\"张三\"}";
        Student student = JSON.parseObject(s, Student.class);
        System.out.println(student);
    }
}
