package test;

import java.util.*;


public class helloworld {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// hello world
		System.out.println("Hello world!");

		// ������������
		int i = 0;
		String string = "test";
		byte b = 35;
		byte b2 = 0x23;
		byte[] bs = new byte[] { 36, 37 };

		// ��������
		// List����������Ԫ���ظ�
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		list.add("test4");
		list.remove("test2");

		// Set������Ԫ���ظ�
		Set set = new HashSet();
		set.add("test1");
		set.add("test2");
		set.remove("test2");

		// Mapʹ��key-valueģʽ,key�����ظ�
		Map<String, String> map = new HashMap<String, String>();
		map.put("test", "value");
		map.remove("test");

		// �����жϡ�ѭ��
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

		
		// ���÷�������
		print("hello test");
		// �����ⲿ�������ķ���
		test2.t();

		
		// ��̬ static �� new��������
		

		String url = "http://127.0.0.1:9999/";
		String body = "test=1234";

		Map<String, String> maptest = new HashMap<String, String>();
		maptest.put("test1", "1234");
		maptest.put("test2", "5678");

		// httpdemo
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
	
	
}
