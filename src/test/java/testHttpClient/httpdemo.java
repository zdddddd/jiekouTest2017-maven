package testHttpClient;


import java.io.*;
import java.net.*;

public class httpdemo {

	public static void onlyGet(String url) throws Exception {
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		HttpURLConnection httpURLConnection = ((HttpURLConnection) conn);
		//httpURLConnection.connect();
		httpURLConnection.getResponseCode();
	}

	public static String httpGet(String url) throws Exception {
		// 向指定URL发送GET方法的请求
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		// 对象转换，后续需要
		HttpURLConnection httpURLConnection = ((HttpURLConnection) conn);
		// 设置请求头属性
		httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		// 建立实际的连接
		httpURLConnection.connect();
		// 获取状态码，此处会真实发送数据
		int code = httpURLConnection.getResponseCode();
		System.out.println(code);
		//从header中获取数据
		//httpURLConnection.getHeaderField("name");
		//获取
		//httpURLConnection.getHeaderFields().get("name");
		// 获取网络数据流
		InputStream inputStream = httpURLConnection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		//自动根据头信息中的编码方式修改，httpURLConnection.getContentEncoding()有可能取到空值，所以最好先判断。
		//InputStreamReader inputStreamReader = new InputStreamReader(inputStream, httpURLConnection.getContentEncoding());
		BufferedReader reader = new BufferedReader(inputStreamReader);
		// 逐行读取转换为字符串，直到读不到为止
		String tempLine = null;
		StringBuffer resultBuffer = new StringBuffer();
		while ((tempLine = reader.readLine()) != null) {
			resultBuffer.append(tempLine + '\n');
		}
		// 关闭对象
		reader.close();
		inputStreamReader.close();
		inputStream.close();

		return resultBuffer.toString();
	}

	public static String httpPost(String url, String parameterData) throws Exception {

		URL realUrl = new URL(url);
		URLConnection connection = realUrl.openConnection();
		HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		//httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		OutputStream outputStream = httpURLConnection.getOutputStream();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
		outputStreamWriter.write(parameterData.toString());
		outputStreamWriter.flush();

		int code = httpURLConnection.getResponseCode();
		System.out.println(code);
		// 获取网络数据流
		InputStream inputStream = httpURLConnection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader reader = new BufferedReader(inputStreamReader);
		// 逐行读取转换为字符串，直到读不到为止
		String tempLine = null;
		StringBuffer resultBuffer = new StringBuffer();
		while ((tempLine = reader.readLine()) != null) {
			resultBuffer.append(tempLine + '\n');
		}
		// 关闭对象
		reader.close();
		inputStreamReader.close();
		inputStream.close();

		return resultBuffer.toString();
	}

}
