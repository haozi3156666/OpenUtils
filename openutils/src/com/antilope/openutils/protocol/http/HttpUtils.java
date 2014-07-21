package com.antilope.openutils.protocol.http;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.HttpURLConnection;

public class HttpUtils {
	
	private static int _timeout=7200000;  
	public static int get_timeout() {
		return _timeout;
	}

	public static void set_timeout(int timeout) {
		_timeout = timeout;
	}

	/**
	 * 所有get 请求底层调用方法
	 * 
	 * @param url
	 *            请求url
	 * @return byte[] response data
	 */
	public static String doGet(String url) {
		String response = "";
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectionTimeout(_timeout);  
        httpClient.setTimeout(_timeout);  
		// 创建GET方法的实例
		HttpMethod getMethod = new GetMethod(url);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 处理内容
			response = new String(responseBody,"UTF-8");
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return response;
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectionTimeout(_timeout);  
        httpClient.setTimeout(_timeout);  
		HttpMethod method = new PostMethod(url);
		// 设置Http Post数据
		if (params != null) {
			HttpMethodParams p = new HttpMethodParams();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				p.setParameter(entry.getKey(), entry.getValue());
			}
			method.setParams(p);
		}
		try {
			int statusCode = httpClient.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			}else{
				System.err.println("Method failed: " + method.getStatusLine());  
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}

		return response;
	}
	
	public static String doPut(String url, Map<String, String> params) throws IOException{
		return doPut(url,params,null);
	}
	
	public static String doPut(String url, Map<String, String> params,String localfile) throws IOException{  
        String response= "";  
        HttpClient httpClient= new HttpClient();  
        httpClient.setConnectionTimeout(_timeout);  
        httpClient.setTimeout(_timeout);  
        PutMethod method = null;  
        try{  
            method= new PutMethod(url);  
            if(localfile != null){
            	method.setRequestBody(new FileInputStream(localfile));
            }
            if (params != null) {
    			HttpMethodParams p = new HttpMethodParams();
    			for (Map.Entry<String, String> entry : params.entrySet()) {
    				p.setParameter(entry.getKey(), entry.getValue());
    			}
    			method.setParams(p);
    		}
  
            int statusCode = httpClient.executeMethod(method);  
            if (statusCode != HttpStatus.SC_OK) {  
            	System.err.println("Method failed: " + method.getStatusLine());  
            }
            response = method.getResponseBodyAsString();  
        }catch(HttpException e){  
            e.printStackTrace();
        }finally{  
            if(method!=null) method.releaseConnection();  
        }  
        return response;  
    }
	
	public static String doPutHeder(String url, Map<String, String> params,String header) throws IOException{  
		String response= "";  
        HttpClient httpClient= new HttpClient();  
        httpClient.setConnectionTimeout(_timeout);  
        httpClient.setTimeout(_timeout);  
        PutMethod method = null;  
        try{  
            method= new PutMethod(url);  
            if (params != null) {
    			HttpMethodParams p = new HttpMethodParams();
    			for (Map.Entry<String, String> entry : params.entrySet()) {
    				p.setParameter(entry.getKey(), entry.getValue());
    			}
    			method.setParams(p);
    		}
  
            int statusCode = httpClient.executeMethod(method);  
            if (statusCode != HttpStatus.SC_OK) {  
            	System.err.println("Method failed: " + method.getStatusLine());  
            }
            response = method.getResponseHeader(header).getValue();  
        }catch(HttpException e){  
            e.printStackTrace();
        }finally{  
            if(method!=null) method.releaseConnection();  
        }  
        return response;  
    }
	
	public static String doDelete(String uri) throws IOException{  
        String response= "";  
        HttpClient httpClient= new HttpClient();  
        httpClient.setConnectionTimeout(_timeout);  
        httpClient.setTimeout(_timeout);  
        DeleteMethod delMethod = null;  
        try{  
            delMethod= new DeleteMethod();  
            delMethod.setURI(new URI(uri,false));  
            delMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());  
            int statusCode = httpClient.executeMethod(delMethod);  
            if (statusCode != HttpStatus.SC_OK) {  
                  System.err.println("Method failed: " + delMethod.getStatusLine());  
            }
            response = delMethod.getResponseBodyAsString();
        }catch(HttpException e){  
            e.printStackTrace();
        }finally{  
            if(delMethod!=null) delMethod.releaseConnection();  
        }  
        return response;  
    }
	
	 public static String httpRest(String uri, Map<String,String> paramMap, String method) throws IOException{  
	        String threadName = Thread.currentThread().getName();  
	        String result= null;  
	        if(method.toUpperCase().equals("PUT"))  
	            result = doPut(uri, paramMap);  
	        if(method.toUpperCase().equals("GET"))  
	            result = doGet(uri);  
	        if(method.toUpperCase().equals("POST"))  
	            result = doPost(uri, paramMap);  
	        if(method.toUpperCase().equals("DELETE"))  
	            result = doDelete(uri);  
	        if(result==null)result = "";  
	        if(result.length()<10000){  
	            System.out.println("<"+threadName+">"+"result: "+result);  
	        }else{  
	        	System.out.println("<"+threadName+">"+"result:Large data... ");  
	        }  
	        return result;  
	    }  
	 
	  private static String doRest(String uri, String method) throws IOException{  
	        StringBuffer data= new StringBuffer();  
	          
	        if(uri==null || "".equals(uri))  
	            return "";  
	          
	        URL url = new URL(uri);  
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
	        conn.setDoOutput(true);  
	        conn.setDoOutput(true);  
	          
	        conn.setRequestMethod(method);  
	        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));     
	        String input= null;  
	        while((input = reader.readLine()) != null) {  
	            data.append(input);  
	        }  
	        conn.disconnect();  
	        return data.toString();  
	    } 

}