package test;

import java.io.File;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 本类不支持https
 * httpFluent支持https
 */
public class httpclientTest {


	//全局配置
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setConnectTimeout(10000)
			.setConnectionRequestTimeout(15000)
			.setMaxRedirects(5)
			.setProxy(new HttpHost("127.0.0.1",8888))
			.build();

	public static String sendHttpGet(String url) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		httpGet.addHeader("User-Agent", "Mozilla");

		CloseableHttpResponse response = httpClient.execute(httpGet);
		System.out.println(response.getStatusLine().getStatusCode() + "\n");

		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8"); // GBK
		System.out.println(responseContent);

		response.close();
		httpClient.close();
		return responseContent;
	}

	static CloseableHttpClient httpClient2 = HttpClients.createDefault();
	public static CloseableHttpResponse sendHttpGet2(String url) throws Exception{
		HttpGet httpGet = new HttpGet();
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = httpClient2.execute(httpGet);
		return response;
	}

	public static int getCode(CloseableHttpResponse response){
		return response.getStatusLine().getStatusCode();
	}

	public static String getContent(CloseableHttpResponse response) throws Exception{
		return getContent(response,"UTF-8");
	}

	public static String getContent(CloseableHttpResponse response,String charset) throws Exception{
		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, charset);
		return  responseContent;
	}

	public static String sendHttpPost(String url, String body) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.addHeader("User-Agent", "Posttest");

		//
		//httpPost.setEntity(new StringEntity(body));
		//json 只支持utf-8
		httpPost.setEntity(new StringEntity(body,"UTF-8"));

		CloseableHttpResponse response = httpClient.execute(httpPost);
		System.out.println(response.getStatusLine().getStatusCode() + "\n");

		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8"); // GBK
		System.out.println(responseContent);

		response.close();
		httpClient.close();
		return responseContent;
	}

	public static String sendHttpPost(String url, Map<String, String> params) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.addHeader("User-Agent", "Posttest");

		List<NameValuePair> plist = new ArrayList<NameValuePair>();
		for (String pKey : params.keySet()) {
			plist.add(new BasicNameValuePair(pKey, params.get(pKey)));
        }
		httpPost.setEntity(new UrlEncodedFormEntity(plist));
		
		CloseableHttpResponse response = httpClient.execute(httpPost);
		System.out.println(response.getStatusLine().getStatusCode() + "\n");

		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8"); // GBK
		System.out.println(responseContent);

		response.close();
		httpClient.close();
		return responseContent;
	}

	/**
	 * 发送Post请求，带文件
	 * @param httpUrl 地址
	 * @param maps 参数
	 * @param fileLists 附件
	 */
	public static String sendHttpPost(String httpUrl,Map<String,String> maps,List<File> fileLists) throws Exception{
		//可以把文件名和路径也放到maps中，在key（文件名）前加个符号来表明这是个文件。后面再判断key是否是文件，然后在处理
		HttpPost httpPost = new HttpPost(httpUrl);
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		if(maps!=null){
			for (String key :maps.keySet()){
				multipartEntityBuilder.addPart(key,new StringBody(maps.get(key),ContentType.TEXT_PLAIN));
			}
		}
		if (fileLists!=null){
			for (File file:fileLists){
				FileBody fileBody = new FileBody(file);
				//名字最好不同，因为如果有多个文件的话，就完啦。
				multipartEntityBuilder.addPart("files",fileBody);
			}
		}
		HttpEntity httpEntity = multipartEntityBuilder.build();
		httpPost.setEntity(httpEntity);
		return  sendHttpPost(httpPost);

	}

	public static String sendHttpPost(HttpPost httpPost) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		httpPost.setConfig(requestConfig);
		httpPost.addHeader("User-Agent", "Posttest");

		CloseableHttpResponse response = httpClient.execute(httpPost);

		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8"); // GBK

		response.close();
		httpClient.close();
		return responseContent;
	}
}