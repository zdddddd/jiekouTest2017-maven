package test;


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
		// ��ָ��URL����GET����������
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		// ����ת����������Ҫ
		HttpURLConnection httpURLConnection = ((HttpURLConnection) conn);
		// ��������ͷ����
		httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		// ����ʵ�ʵ�����
		httpURLConnection.connect();
		// ��ȡ״̬�룬�˴�����ʵ��������
		int code = httpURLConnection.getResponseCode();
		System.out.println(code);
		// ��ȡ����������
		InputStream inputStream = httpURLConnection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader reader = new BufferedReader(inputStreamReader);
		// ���ж�ȡת��Ϊ�ַ�����ֱ��������Ϊֹ
		String tempLine = null;
		StringBuffer resultBuffer = new StringBuffer();
		while ((tempLine = reader.readLine()) != null) {
			resultBuffer.append(tempLine + '\n');
		}
		// �رն���
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
		// ��ȡ����������
		InputStream inputStream = httpURLConnection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader reader = new BufferedReader(inputStreamReader);
		// ���ж�ȡת��Ϊ�ַ�����ֱ��������Ϊֹ
		String tempLine = null;
		StringBuffer resultBuffer = new StringBuffer();
		while ((tempLine = reader.readLine()) != null) {
			resultBuffer.append(tempLine + '\n');
		}
		// �رն���
		reader.close();
		inputStreamReader.close();
		inputStream.close();

		return resultBuffer.toString();
	}

}
