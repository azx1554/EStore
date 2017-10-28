package com.yang.backgroud.common.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class HttpPostUtil {
	private static final Logger logger = LogManager.getLogger(HttpPostUtil.class);

	/**
	 * 对外发送http post请求 utf-8编码
	 * 
	 * @param urlString
	 * @param content
	 * @param conTimeout
	 *            连接超时时间
	 * @param readTimeout
	 *            交互超时时间
	 * @return
	 * @throws Exception
	 */
	public static String sendPostRequest(String urlString, String content, int conTimeout, int readTimeout) throws Exception {
		return sendPostRequest(urlString, content, "UTF-8", conTimeout, readTimeout);
	}

	public static String sendHttp(String url, String content) {

		URL u = null;
		HttpURLConnection con = null;
		// 构建请求参数
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
			// 设置请求方式为 post 而非 get
			con.setRequestProperty("Content-Length", String.valueOf(content.getBytes().length));
			// con.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(content);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				con.disconnect();

			}
		}
		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(con

			.getInputStream(), "UTF-8"));

			String temp;

			while ((temp = br.readLine()) != null) {

				buffer.append(temp);

				buffer.append("\n");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return buffer.toString();

	}

	/**
	 * 对外发送http post请求
	 * 
	 * @param urlString
	 * @param content
	 * @param contentCharset
	 *            如果给NULL则默认为UTF-8.
	 * @param conTimeout
	 *            连接超时时间
	 * @param readTimeout
	 *            交互超时时间
	 * @return
	 * @throws Exception
	 */
	private static String sendPostRequest(String urlString, String content, String contentCharset, int conTimeout, int readTimeout) throws Exception {

		String charset = StringUtils.defaultString(contentCharset, "UTF-8");
		byte[] contentBytes = content.getBytes(charset);

		// 创建URL对象
		URL url = new URL(urlString);
		HttpURLConnection conn = null;

		OutputStream output = null;

		// InputStream input = null;
		try {
			// 创建连接
			conn = (HttpURLConnection) url.openConnection();
			// 与支付通道子系统的交互 需要设置为以下属性
			conn.addRequestProperty("Content-Type", "application/json; charset=" + charset);

			// 设置请求方式为 post 而非 get
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", String.valueOf(contentBytes.length));
			conn.setDoOutput(true);

			conn.setDoInput(true);

			conn.setUseCaches(false);

			// 设置超时时间
			if (conTimeout >= 0) {
				conn.setConnectTimeout(conTimeout);
			} else {
				// Integer.parseInt(WebInteriorService.configHashtable.get(Constants.INTERIOR_CONNECT_TIMEOUT))
				conn.setConnectTimeout(6000);
			}

			if (readTimeout >= 0) {
				conn.setReadTimeout(readTimeout);
			} else {
				// Integer.parseInt(WebInteriorService.configHashtable.get(Constants.INTERIOR_READ_TIMEOUT))
				conn.setReadTimeout(6000);
			}

			// 设置可以使用url进行输入 与 输出，即发送 与 接收数据
			conn.setDoInput(true);
			conn.setDoOutput(true);

			// 得到outputStream
			output = conn.getOutputStream();
			// flush
			output.write(contentBytes);
			output.flush();
			output.close();
			/*
			 * String contentEncoding = conn.getContentEncoding(); if (null !=
			 * contentEncoding && contentEncoding.indexOf("gzip") >= 0) { input
			 * = new GZIPInputStream(input); } else if (null != contentEncoding
			 * && contentEncoding.indexOf("deflate") >= 0) { input = new
			 * InflaterInputStream(input); }
			 */

			// 通过连接获取 响应结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			// 将返回结果转换为 String字符串
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			System.out.println("buffer.toString()===========================" + buffer.toString());
			reader.close();
			return buffer.toString();
		} finally {
			if (null != conn)
				conn.disconnect();
		}
	}

	@SuppressWarnings("unused")
	private static String sendPostRequestWithHttpClient(String urlString, String content, String contentCharset, int conTimeout, int readTimeout) throws IOException {
		HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());

		String charset = StringUtils.defaultString(null, "UTF-8");

		// 设置连接池参数
		HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();

		managerParams.setDefaultMaxConnectionsPerHost(10);
		managerParams.setMaxTotalConnections(20);
		managerParams.setConnectionTimeout(conTimeout);
		managerParams.setSoTimeout(readTimeout);

		// 设置单个连接参数
		HttpClientParams clientParams = httpClient.getParams();
		clientParams.setContentCharset(charset);
		clientParams.setParameter(HttpMethodParams.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)");
		// post方式
		PostMethod postMethod = new PostMethod(urlString);
		// 传递的是xml报文
		postMethod.setRequestHeader("Content-Type", "application/json; charset=" + charset);
		// postMethod.setRequestHeader("Content-Type",
		// "application/xml; charset="
		// + charset);
		// 大数据量数据
		postMethod.setContentChunked(false);

		// 请求内容
		postMethod.setRequestEntity(new StringRequestEntity(content, "application/xml", charset));

		try {
			// 执行请求
			httpClient.executeMethod(postMethod);
			System.out.println(postMethod.getStatusLine());
			System.out.println(postMethod.getResponseBodyAsString());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}

		return postMethod.getResponseBodyAsString();
	}

	public static String sendPostRequest(String json) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try {
			properties.load(classLoader.getResourceAsStream("socket.properties"));
		} catch (IOException e) {
			logger.info("load socket.propertis fail when sendpostRequest to router", e);
		}
		String path = "http://" + properties.getProperty("routerIp") + ":" + properties.getProperty("routerPort") + "/" + properties.getProperty("routerAppName")
				+ "/services/router/dispath?_type=json";
		String responseString = "";
		try {
			responseString = sendPostRequestByXml(path, json, "UTF-8", 100000, 600000);
		} catch (Exception e) {
			logger.info("sendPostRequest to router exception", e);
		}
		return responseString;
	}

	public static String sendPostRequestByXml(String urlString, String content, String contentCharset, int conTimeout, int readTimeout) throws Exception {
		String line;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Pragma:", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			// 设置超时时间
			con.setRequestMethod("POST");
			con.setConnectTimeout(conTimeout);
			con.setReadTimeout(readTimeout);
			// 设置可以使用url进行输入 与 输出，即发送 与 接收数据
			con.setDoInput(true);
			con.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			out.write(content);
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			// 将返回结果转换为 String字符串
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} catch (Exception e) {
			logger.info("send pay request to router exception", e);
		}
		return buffer.toString();
	}
}
