package com.example.demo.util;

import jdk.nashorn.internal.parser.Token;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * 利用HttpClient进行http请求的工具类 
 * </p>使用httpClient最新版本，消除已过期的属性和方法
 * </p>by haakon 2017-07-04
 * 
 */
public class HttpClient {

	// utf-8字符编码
	public static final String CHARSET_UTF_8 = "utf-8";

	// HTTP内容类型。
	public static final String CONTENT_TYPE_TEXT_HTML = "text/xml;charset=utf-8";

	// HTTP内容类型。
	public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

	// HTTP内容类型。相当于form表单的形式，提交数据
	public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded;charset=utf-8";

	// HTTP内容类型。相当于json的形式，提交数据
	public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";

	// 连接管理器
	private static PoolingHttpClientConnectionManager pool;
	
	// 请求配置
	private static RequestConfig requestConfig;


	static {

		try {
			// System.out.println("初始化HttpClient~~~开始");
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
			// 配置同时支持 HTTP 和 HTPPS
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
			// 初始化连接管理器
			pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			// 将最大连接数增加到200，后续应从配置文件中读取
			pool.setMaxTotal(200);
			// 设置最大路由
			pool.setDefaultMaxPerRoute(20);
			// 根据默认超时限制初始化requestConfig
			int socketTimeout = 90000;
			int connectTimeout = 60000;
			int connectionRequestTimeout = 20000;
			requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout)
					.setConnectTimeout(connectTimeout).build();
			// System.out.println("初始化HttpClient~~~结束");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} 
		
		// 设置请求超时时间
//		requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).setConnectionRequestTimeout(50000).build();
	}


	public static CloseableHttpClient getHttpClient() {

		CloseableHttpClient httpClient = HttpClients.custom()
				// 设置连接池管理
				.setConnectionManager(pool)
				// 设置请求配置
				.setDefaultRequestConfig(requestConfig)
				.setMaxConnTotal(200).setMaxConnPerRoute(100)
				// 设置重试次数为0次
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();

		return httpClient;
	}

	/**
	 * AUTO_TEST0000000002        发送对应的4种请求方式POST,GET,DELETE,PUT请求完全封装起来请求格式
	 * 发送Post请求
	 * 
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpPost(HttpPost httpPost, String authorization) {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		// 响应内容
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = getHttpClient();
			// 配置请求信息
			if(!authorization.isEmpty()) {
				httpPost.addHeader("Authorization", authorization);
			}
			//要求返回json格式
//			httpPost.addHeader("Accept", "application/json;");
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			// 得到响应实例
			HttpEntity entity = response.getEntity();
			//System.out.println(entity);
			// 可以获得响应头
//			 Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
//			 for (Header header : headers) {
//			 System.out.println(header.getName());
//			 }

			// 得到响应类型
	//		 System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

			// 判断响应状态
			if (response.getStatusLine().getStatusCode() >= 300) {
				String actual=",\"statu\":";
				String xxx=",\"";
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8)+actual+response.getStatusLine().getStatusCode()+xxx;
				throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode()+responseContent);
			}
            //该处是经过改动的需要进行处理
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				String actual=",\"statu\":";
				String xxx=",\"";
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8)+actual+response.getStatusLine().getStatusCode()+xxx;
				EntityUtils.consume(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 发送Get请求
	 * 
	 * @param httpGet
	 * @return
	 */
	private static String sendHttpGet(HttpGet httpGet) {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		// 响应内容
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = getHttpClient();
			// 配置请求信息
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			// 得到响应实例
			HttpEntity entity = response.getEntity();
//			System.out.println(entity);
			// 可以获得响应头
			 Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
			 for (Header header : headers) {
			 System.out.println(header.getName());
		 }

			// 得到响应类型
			 System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

			// 判断响应状态
			if (response.getStatusLine().getStatusCode() >= 300) {
				String actual=",\"statu\":";
				String xxx=",\"";
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8)+actual+response.getStatusLine().getStatusCode()+xxx;
				throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
				EntityUtils.consume(entity);
				//得到相应状态
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
	/**
	 * 发送delete请求
	 *
	 * @param httpDelete
	 * @return
	 */
	private static String sendHttpDelete(HttpDelete httpDelete) {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		// 响应内容
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = getHttpClient();
			// 配置请求信息
			httpDelete.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpDelete);
			// 得到响应实例
			HttpEntity entity = response.getEntity();
//			System.out.println(entity);
			// 可以获得响应头
			Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
			for (Header header : headers) {
				System.out.println(header.getName());
			}

			// 得到响应类型
			System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

			// 判断响应状态
			if (response.getStatusLine().getStatusCode() >= 300) {
				String actual=",\"statu\":";
				String xxx=",\"";
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8)+actual+response.getStatusLine().getStatusCode()+xxx;
				throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
				EntityUtils.consume(entity);
				//得到相应状态

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
	/**
	 * 发送Put请求
	 *
	 * @param httpPut
	 * @return
	 */
	private static String sendHttpPut(HttpPut httpPut, String authorization) {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		// 响应内容
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = getHttpClient();
			// 配置请求信息
			if(!authorization.isEmpty()) {
				httpPut.addHeader("Authorization", authorization);
			}
			//要求返回json格式
		//	httpPut.addHeader("Accept", "application/json;");
			httpPut.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPut);
			System.out.println("返回结果的值为"+response);
			System.out.println("返回结果的值为"+responseContent);

			// 得到响应实例
			HttpEntity entity = response.getEntity();
            System.out.println(entity);
			// 可以获得响应头
			 Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
			 for (Header header : headers) {
			 System.out.println(header.getName());
			 }

			// 得到响应类型
		 System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());
			//得到相应状态
			System.out.println(response.getStatusLine().getStatusCode());
			// 判断响应状态
			System.out.println("返回结果的值为"+responseContent);
			if (response.getStatusLine().getStatusCode() >= 300) {
				String actual=",\"statu\":";
				String xxx=",\"";
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8)+actual+response.getStatusLine().getStatusCode()+xxx;
				System.out.println("返回结果的值为"+responseContent);
				//throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
				//throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());

			}

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
				EntityUtils.consume(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}


	/**
	 * 发送 post请求
	 *
	 * @param httpUrl
	 *            地址
	 */
	public static String sendHttpPost(String httpUrl, String authorization) {
		// 创建httpPost
		HttpPost httpPost = new HttpPost(httpUrl);
		return sendHttpPost(httpPost, authorization);
	}

	/**
	 * 发送 get请求
	 *
	 * @param httpUrl
	 */
	public static String sendHttpGet(String httpUrl,String token) {
		// 创建get请求
		HttpGet httpGet = new HttpGet(httpUrl);
		httpGet.setHeader("Authorization",token);
		return sendHttpGet(httpGet);
	}
	/**
	 * 发送 delete请求
	 *
	 * @param httpUrl
	 */
	public static String sendHttpDelete(String httpUrl,String token) {
		// 创建get请求
		HttpDelete httpDelete = new HttpDelete(httpUrl);
		httpDelete.setHeader("Authorization",token);
		return sendHttpDelete(httpDelete);
	}

	/**
	 * 发送 put请求
	 *
	 * @param httpUrl
	 *            地址
	 */
	public static String sendHttpPut(String httpUrl, String authorization) {
		// 创建httpPost
		HttpPost httpPost = new HttpPost(httpUrl);
		return sendHttpPost(httpPost, authorization);
	}


	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param params
	 *            参数(格式:map)
	 * 
	 */
	public static String sendHttpPost(String httpUrl, Map<String, Object> params) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
				valuePairs.add(nameValuePair);
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs, "utf-8");
			formEntity.setContentType(CONTENT_TYPE_JSON_URL);
			httpPost.setEntity(formEntity);
			HttpResponse resp = client.execute(httpPost);
			HttpEntity entity = resp.getEntity();
			String respContent = EntityUtils.toString(entity, "utf-8").trim();
			httpPost.abort();
			return respContent;
			// 设置参数
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 发送 get请求
	 *
	 * @param httpUrl
	 *            地址
	 * @param params
	 *            参数(格式:map)
	 *
	 */
	public static String sendHttpGetNotoken(String httpUrl, Map<String, Object> params) {
		String respContent = "";
		String paramStr = convertStringParamter(params);
		if(!paramStr.isEmpty()){
			httpUrl += "?"+ paramStr;
			HttpGet httpGet = new HttpGet(httpUrl);
			respContent = sendHttpGet(httpGet);
			httpGet.abort();
		}else{
			HttpGet httpGet = new HttpGet(httpUrl);
			respContent = sendHttpGet(httpGet);
			return respContent;
		}

		return respContent;
	}

	public static String sendHttpGetwithtoken(String httpUrl,String Token ) {
		String respContent = "";
			HttpGet httpGet = new HttpGet(httpUrl);
			httpGet.addHeader("authorization","Bearer"+ Token);
			respContent = sendHttpGet(httpGet);
			httpGet.abort();
		return respContent;
	}


	/**
	 * 发送 put请求
	 *
	 * @param httpUrl
	 *            地址
	 * @param params
	 *            参数(格式:map)
	 *
	 */
	public static String sendHttpPut(String httpUrl, Map<String, Object> params) {
		HttpPut httpPut = new HttpPut(httpUrl);// 创建httpPost
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("key1", "value1");
//		map.put("key2", "value2");
//		map.put("key3", "value3");
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
				valuePairs.add(nameValuePair);
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs, "utf-8");
			formEntity.setContentType(CONTENT_TYPE_JSON_URL);
			httpPut.setEntity(formEntity);
			HttpResponse resp = client.execute(httpPut);

			HttpEntity entity = resp.getEntity();
			String respContent = EntityUtils.toString(entity, "utf-8").trim();
			httpPut.abort();

			return respContent;
			// 设置参数
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


//AUTO_TEST0000000001        发送对应的POST以及PUT请求，请求方式为JSON格式的请求发送
	/**
	 * 发送 post请求 发送json数据
	 * 
	 * @param httpUrl
	 *            地址
	 * @param paramsJson
	 *            参数(格式 json)
	 * 
	 */
	public static String sendHttpPostJson(String httpUrl, String paramsJson) throws Exception{
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			if (paramsJson != null && paramsJson.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsJson, "utf-8");
				stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			throw e;
//			e.printStackTrace();
		}
		return sendHttpPost(httpPost,"");
	}

	/**
	 * 发送 put请求 发送json数据
	 *
	 * @param httpUrl
	 *            地址
	 * @param paramsJson
	 *            参数(格式 json)
	 *
	 */
	public static String sendHttpPutJson(String httpUrl, String paramsJson) throws Exception{
		HttpPut httpPut = new HttpPut(httpUrl);// 创建httpPost
		try {
			// 设置参数
			if (paramsJson != null && paramsJson.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsJson, "utf-8");
				stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
				httpPut.setEntity(stringEntity);
			}
		} catch (Exception e) {
			throw e;
//			e.printStackTrace();
		}
		return sendHttpPut(httpPut,"");
	}


	/**
	 * 发送 post请求
	 *
	 * @param httpUrl
	 *            地址
	 * @param paramsJson
	 *            参数(格式:map)
	 *
	 */
	public static String sendHttpPostForm(String httpUrl, String paramsJson) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			if (paramsJson != null && paramsJson.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsJson, "utf-8");
				stringEntity.setContentType(CONTENT_TYPE_FORM_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			throw e;
		}
		return sendHttpPost(httpPost,"");
	}


	public static String sendHttpPostJson(String httpUrl, String paramsJson, String authorization) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			if (paramsJson != null && paramsJson.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsJson, "utf-8");
				stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost,authorization);
	}


	/*PUT请求*/
	public static String sendHttpPutJson(String httpUrl, String paramsJson, String authorization) {
		HttpPut httpPut = new HttpPut(httpUrl);// 创建httpPut
		try {
			// 设置参数
			if (paramsJson != null && paramsJson.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsJson, "utf-8");
				stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
				httpPut.setEntity(stringEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPut(httpPut,authorization);
	}



	/**
	 * 发送 post请求 发送xml数据
	 * 
	 * @param httpUrl
	 *            地址
	 * @param paramsXml
	 *            参数(格式 Xml)
	 * 
	 */
	public static String sendHttpPostXml(String httpUrl, String paramsXml) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			if (paramsXml != null && paramsXml.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsXml, "UTF-8");
				stringEntity.setContentType(CONTENT_TYPE_TEXT_HTML);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost, "");
	}

	/**
	 * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
	 * 
	 * @param parameterMap
	 *            需要转化的键值对集合
	 * @return 字符串
	 */
	public static String convertStringParamter(Map<String, Object> parameterMap) {
		StringBuffer parameterBuffer = new StringBuffer();
		try {
			if (parameterMap != null) {
				Iterator<String> iterator = parameterMap.keySet().iterator();
				String key = null;
				String value = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					if (parameterMap.get(key) != null) {
						value = (String) parameterMap.get(key);
					} else {
						value = "";
					}
					parameterBuffer.append(key).append("=").append(value);
					if (iterator.hasNext()) {
						parameterBuffer.append("&");
					}
				}
			}
			return parameterBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}




	public static void main(String[] args) throws Exception {
		String jString = "{\"merchant_id\":\"2001000038\",\"sign\":\"wewe\",\"tf_timestamp\":\"20170704182546\",\"service_id\":\"payQuery\",\"terminal\":\"pc\",\"businessnumber\":\"35910111149\"}";
		String url = "http://61.175.198.100:18088/core/payQuery";
		String returnstr = sendHttpPostJson(url, jString);
		System.out.println("Result :->" + returnstr);
	}
}
