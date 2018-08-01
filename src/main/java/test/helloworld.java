package test;

import jsoup.JsoupTest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.util.*;


public class helloworld {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// hello world
		System.out.println("Hello world!");

		// 基础数据类型
		int i = 0;
		String string = "test";
		byte b = 35;
		byte b2 = 0x23;
		byte[] bs = new byte[] { 36, 37 };

		// 集合类型
		// List有序且允许元素重复
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		list.add("test4");
		list.remove("test2");

		// Set不允许元素重复
		Set set = new HashSet();
		set.add("test1");
		set.add("test2");
		set.remove("test2");

		// Map使用key-value模式,key不可重复
		Map<String, String> map = new HashMap<String, String>();
		map.put("test", "value");
		map.remove("test");

		// 条件判断、循环
		if (i > 0) {
			System.out.println("Hello if");
		} else {
			System.out.println("Hello else");
		}
		for (int n = 0; n < 10; n++) {
			System.out.println("n = " + n);
		}
		for (String tmp : list) {
			System.out.println(tmp);
		}

		
		// 调用方法基础
		print("hello test");
		// 调用外部其他包的方法
		//test2.t();

		
		// 静态 static 与 new创建对象
		

		String url = "http://127.0.0.1:9999/";
		String body = "test=1234";

		Map<String, String> maptest = new HashMap<String, String>();
		maptest.put("test1", "1234");
		maptest.put("test2", "5678");

		// httpdemo
		//设置代理（全局的）(HttpURLConnection里生效)
		//System.setProperty("http.proxyHost","127.0.0.1");
//		System.setProperty("http.proxyPort","8888");
	//设置cookie,自动添加cookie
		//CookieHandler.setDefault(new CookieManager(null,CookiePolicy.ACCEPT_ALL));
        //设置是否允许跳转
        //HttpURLConnection.setFollowRedirects(false);
		print("httpdemo");
		httpdemo.onlyGet(url);

		print(httpdemo.httpGet(url));

		print(httpdemo.httpPost(url, body));

		// httpclient
		print("httpclient");
		print(httpclientTest.sendHttpGet(url));

		print(httpclientTest.sendHttpPost(url, body));

		print(httpclientTest.sendHttpPost(url, maptest));

		// httpFluent
		print("httpFluent");
		print(httpFluent.Get(url));

		print(httpFluent.Post(url, body));

		print(httpFluent.Post(url, maptest));
	}

	public static void print(String str) {
		System.out.println(str);
	}

	@Test
	public void testJsoupTest1() throws Exception{
		print(JsoupTest.Get("http://www.baidu.com/"));
	}

	@Test
	public void testJsoupTest2() throws Exception{
		String string = JsoupTest.Get("http://www.baidu.com/");
		Document document = Jsoup.parse(string);
		System.out.println(document.getElementById("su").val());
	}

	@Test
	public void testJsoupTest3() throws Exception{
		print(JsoupTest.Get("http://www.yhd.com/"));
	}

	@Test
	public void testJsoupTest4() throws Exception{
		String url ="http://www.baidu.com/";
		CloseableHttpResponse closeableHttpResponse = httpclientTest.sendHttpGet2(url);
		int code = httpclientTest.getCode(closeableHttpResponse);
		String content = httpclientTest.getContent(closeableHttpResponse);
	}
}
