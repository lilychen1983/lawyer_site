package law;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;

import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;


public class Test3 {
	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

//在自己的程序中调用：
public static void main(String[] args) throws Exception {
		CloseableHttpClient client = Test3.createSSLClientDefault();
		HttpPost get = new HttpPost("https://a1.easemob.com/seegoo/appseegoo/users");
		  
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("username", "caoyong"));//{"username":"jliu","password":"123456"}
//        params.add(new BasicNameValuePair("password", "cyxinda"));//{"username":"jliu","password":"123456"}
//        HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		String requestBody = "{\"username\":\"caoyong3\",\"password\":\"123456\"}";
		HttpEntity entity = new ByteArrayEntity(requestBody.getBytes("UTF-8"));
//	        entity..setContentType("application/json");
        get.setEntity(entity);
//        List<Header> headers   = new ArrayList<Header>();
        Header header = new BasicHeader("Authorization", "Bearer YWMtyuqboLyiEeSDEM8oflNlogAAAUzz4eZB_Ti_08k2NGdCwL4Fy60ZW69NJi0");
//        headers.add(header) ;
		get.addHeader(header );
		CloseableHttpResponse response = client.execute(get);
		int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
              HttpEntity rentity = response.getEntity();
              String sEntity = EntityUtils.toString(rentity);
              // parse json here.
              JSONObject jsonObject = JSONObject.fromObject( sEntity ); 
              String res = jsonObject.getString("response").trim();
              if(res.equals("OK")){
//                  ret = true;
              }
        }
	}



//public static void request(String proposalId) {
//    //请求的body信息
//    String requestBody = "{proposalId:" + proposalId + "}";
//    HttpClient httpClient = new DefaultHttpClient();
//    HttpPost httpPost = new HttpPost("");
//    //添加header
//    httpPost.addHeader("X-Easymi-AppCode", "AppCode");
//    httpPost.addHeader("X-Easymi-UserName", "UserName");
//    //添加body
//    ByteArrayEntity entity = null;
//    try {
//        entity = new ByteArrayEntity(requestBody.getBytes("UTF-8"));
//        entity.setContentType("application/json");
//    } catch (UnsupportedEncodingException e) {
//        logger.error("向服务器承保接口发起http请求,封装请求body时出现异常", e);
//        throw new RuntimeException("向服务器承保接口发起http请求,封装请求body时出现异常", e);
//    }
//    httpPost.setEntity(entity);
//    //执行post请求
//    HttpResponse response = null;
//    try {
//        response = httpClient.execute(httpPost);
//    } catch (ClientProtocolException e) {
//        logger.error("提交给服务器的请求，不符合HTTP协议", e);
//        throw new RuntimeException("提交给服务器的请求，不符合HTTP协议", e);
//    } catch (IOException e) {
//        logger.error("向服务器承保接口发起http请求,执行post请求异常", e);
//        throw new RuntimeException("向服务器承保接口发起http请求,执行post请求异常", e);
//    }
//    logger.info("状态码：" + response.getStatusLine());
//}
}
