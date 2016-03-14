/*
 * com.lenovo.cic.gd.http.HttpWrapper.java
 * 
 * author: hucj1
 * checker: hucj1
 * createTime: 2014-8-27
 * Copyright (c) 2012, Lenovo and/or its affiliates. All rights reserved.
 * LENOVO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.law.commons.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


/**
 * a http-client wrapper for conviently consume the webservice.
 *
 * @author hucj1
 * @version v0.1.0003
 * @since JDK1.6.0_27
 * @see
 */
public class MyHttpWrapper {
	private static Logger logger = Logger.getLogger(MyHttpWrapper.class);
    /**
     * method for the http-client
     */
    private HttpMethod method;
    /**
     * url for the web service
     */
    private String websrvurl;

    /**
     * params for the post method.
     */
    List<NameValuePair> params;
    
    List<Header> headers ;

    /**
     * constructor based on the url and method.
     *
     * @param websrvurl String url for the web service
     * @param method HttpMethod method for the future service consuming.
     */
    public MyHttpWrapper(String websrvurl, HttpMethod method) {
        this.method = method;
        this.websrvurl = websrvurl;

        this.params = new ArrayList<NameValuePair>();
        this.headers = new ArrayList<Header>();
    }

    /**
     * add name-value pair to the
     *
     * @param name String name for the pair
     * @param value String value for the pair.
     */
    public void addNameValuePair(String name, String value) {
        this.params.add(new BasicNameValuePair(name, value));
    }
    
    /**
     * add name-value pair to the
     *
     * @param name String name for the pair
     * @param value String value for the pair.
     */
    public void addHeader(String name, String value) {
    	Header header = new BasicHeader(name, value);
    	
        this.headers.add(header);
    }

    /**
     * execute the http request
     *
     * @return boolean sucess/fail
     */
    public boolean execHttpRequest() {

        if (this.method.equals(HttpMethod.POST)) {
            return doPost();
        } else {
            return doGet();
        }
    }

    /**
     * Apply post http request to the back server.
     *
     * @return boolean success/fail
     */
    protected boolean doPost() {
        boolean ret = false;
        try {
            BasicCookieStore cookieStore = new BasicCookieStore();
            CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore).setDefaultHeaders(this.headers)
                .build();
            
        
            HttpEntity entity = new UrlEncodedFormEntity(this.params, "UTF-8");
            HttpPost request = new HttpPost(this.websrvurl);
            request.setEntity(entity);
//            logger.info("发送前时间:"+DateUtil.getNowOfNODIV());
            CloseableHttpResponse response = httpclient.execute(request);
//            logger.info("发送后时间:"+DateUtil.getNowOfNODIV());
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                  HttpEntity rentity = response.getEntity();
                  String sEntity = EntityUtils.toString(rentity);
                  // parse json here.
                  JSONObject jsonObject = JSONObject.fromObject( sEntity ); 
                  String res = jsonObject.getString("response").trim();
                  if(res.equals("OK")){
                      ret = true;
                  }
            }
        } catch (Exception ex) {
        	logger.error( ex);
        }finally{
            
        }
        return ret; 
    }

    /**
     * apply get http request to the back server. not supported currently.
     *
     * @return boolean success/fail
     */
    protected boolean doGet() {
        return false;
    }
    
    
//    public static void main(String[] args){
//    	HttpMethod method = null;
//        String websrvurl = "https://a1.easemob.com/seegoo/appseegoo/users";
//
//        
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        
//        List<Header> headers   = new ArrayList<Header>();
//        Header header = new BasicHeader("Authorization", "Bearer YWMtyuqboLyiEeSDEM8oflNlogAAAUzz4eZB_Ti_08k2NGdCwL4Fy60ZW69NJi0");
//        headers.add(header) ;
//        boolean ret = false;
//        try {
//            BasicCookieStore cookieStore = new BasicCookieStore();
//            CloseableHttpClient httpclient = HttpClients.custom()
//                .setDefaultCookieStore(cookieStore).setDefaultHeaders( headers)
//                .build();
//            
//            params.add(new BasicNameValuePair("username", "caoyong"));//{"username":"jliu","password":"123456"}
//            params.add(new BasicNameValuePair("password", "cyxinda"));//{"username":"jliu","password":"123456"}
//            HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
//            HttpPost request = new HttpPost(websrvurl);
//            request.setEntity(entity);
////            logger.info("发送前时间:"+DateUtil.getNowOfNODIV());
//            CloseableHttpResponse response = httpclient.execute(request);
////            logger.info("发送后时间:"+DateUtil.getNowOfNODIV());
//            int status = response.getStatusLine().getStatusCode();
//            if (status >= 200 && status < 300) {
//                  HttpEntity rentity = response.getEntity();
//                  String sEntity = EntityUtils.toString(rentity);
//                  // parse json here.
//                  JSONObject jsonObject = JSONObject.fromObject( sEntity ); 
//                  String res = jsonObject.getString("response").trim();
//                  if(res.equals("OK")){
//                      ret = true;
//                  }
//            }
//        } catch (Exception ex) {
//        	logger.error( ex);
//        }finally{
//            
//        }
//    
//    }
}
