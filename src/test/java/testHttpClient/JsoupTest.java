package testHttpClient;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

public class JsoupTest {

    public static Map<String,String> cookie;
    public static String Get(String url) throws Exception{
        //Jsoup.connect(url).get();
        Document document = Jsoup.connect(url)
               // .proxy("127.0.0.1", 8888)
                .get();
//        System.out.println(document.toString());
        return  document.toString();
    }

    public static String Get(String url,Map<String,String> data) throws Exception{
        Connection conn = Jsoup.connect(url);
       // conn.proxy("127.0.0.1",8888);
        conn.data(data);
        conn.method(Connection.Method.GET);
        Connection.Response response = conn.execute();
        cookie = response.cookies();
        return  response.body();
    }

    public static String Post(String url,Map<String,String> data) throws  Exception{
        Connection conn = Jsoup.connect(url);
        //conn.proxy("127.0.0.1",8888);
        conn.data(data);
        if(cookie!=null){
            conn.cookies(cookie);
        }
        Document document = conn.post();
        return document.toString();
    }
}
