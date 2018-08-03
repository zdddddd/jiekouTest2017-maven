package testNopCommerce;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpHost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LoginTest {

    @Test
    public void loginWithValidUser() throws UnirestException {
        String username = "616199938@qq.com";
        String password = "123456";
        String indexUrl = "http://localhost:81/";
        String loginUrl = "http://localhost:81/login";


        //设置代理
        //Unirest.setProxy(new HttpHost("127.0.0.1",8888));

        //Set-Cookie: Nop.customer=3ff17133-06e1-447f-9233-4f5bd243e85f; expires=Sat, 03-Aug-2019 02:34:16 GMT; path=/; HttpOnly
        //get请求登录页面
        Unirest.get(loginUrl).asString();
        //获得响应中的cookie  Nop.customer=3ff17133-06e1-447f-9233-4f5bd243e85f
        //Unirest会自动处理cookie，所以不用写
        //HttpResponse<String> httpResponse = Unirest.get(loginUrl).asString();
//        String loginCookie = httpResponse.getHeaders().get("Set-Cookie").get(0)
//                .substring(0, httpResponse.getHeaders().get("Set-Cookie")
//                        .get(0).indexOf(";"));
        //登录，这里需要带上上一步获得的cookie和账号密码等Email=470015321%40qq.com&Password=123456&RememberMe=false
        Unirest.post(loginUrl)
                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36")
                .header("Content-Type","application/x-www-form-urlencoded")
                //Unirest会自动处理cookie，所以不用写
                //.header("Cookie",loginCookie)
                //connection可以不写
                //.header("Connection","keep-alive")
                .field("Email",username)
                .field("Password",password)
                .field("RememberMe","false")
                .asString();

        HashMap<String, String> indexHeaderMap = new HashMap<String, String>();
        indexHeaderMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        indexHeaderMap.put("Accept-Language","zh-CN,zh;q=0.9");
        indexHeaderMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36");

        //到主页
        String indexBody = Unirest.get(indexUrl).headers(indexHeaderMap).asString().getBody();
        //转成document
        Document indexDocument = Jsoup.parse(indexBody);
        //获取登录成功后页面中才会出现的内容
        String loginTag = indexDocument.getElementsByClass("ico-account").get(0).ownText();
        //验证页面有登录成功才会出现的内容
        System.out.println("实际值："+loginTag+"    预期值：My account");
        Assert.assertEquals(loginTag,"My account");


    }
}
