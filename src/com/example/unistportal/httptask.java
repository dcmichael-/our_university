package com.example.unistportal;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class httptask extends AsyncTask<String, String, String>{
	ArrayList<String> para;
	ArrayList<String> val;
	public String result;
	HttpClient httpclient;
	Handler handle;
	public void makehandler(Handler h)
	{
		handle = h;
	}
	public httptask()
	{
		httpclient = getNewHttpClient();
		para = new ArrayList<String>();
		val = new ArrayList<String>();
	}
	public void makeHttpPost(String param, String value)
	{
		para.add(param);
		val.add(value);
	}
	public void clearPOST()
	{
		para.clear();
		val.clear();
	}
	public String sendGETString(String url, ArrayList<String> params, ArrayList<String> values, boolean isParam) throws SocketTimeoutException, IOException
			{
		String u = url;
		int len = 0;

		if (params != null)
			len = params.size();
		else
			len = 0;
		if (len > 0)
		{
			u += "?";
			for (int i = 0; i < len; i++)
			{
				if (params.get(i) != null && !params.get(i).equals(""))
				{
					if (values.get(i) != null && isParam)
					{
						u +=params.get(i) + "=" + values.get(i) + "&";
					}
				}
			}
			//httpPost.setParams(new HttpParams());
			//httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
		}
		Log.e("get", u);
		HttpGet httpPost = new HttpGet(u);
		/**
		 * Https SSL 보안 통신 방식 처리를 위한 인증 처리 부분
		 */

		// 속도가 빨라진다고 함..ㅋㅋ
		//httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		//httpclient.getParams().setParameter("http.connection.timeout", 60000);
		//httpclient.getParams().setParameter("http.socket.timeout", 60000);

		HttpResponse httpResponse = httpclient.execute(httpPost);

		String response = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);

		//Log.e("HYY", "response:" + u);
		//Log.e("HYY", "response:" + response);

		return response;
			}
	public String sendPOSTString(String url, ArrayList<String> params, ArrayList<String> values, boolean isParam) throws SocketTimeoutException, IOException
			{

		HttpPost httpPost = new HttpPost(url);
		int len = 0;

		if (params != null)
			len = params.size();
		else
			len = 0;

		if (len > 0)
		{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			for (int i = 0; i < len; i++)
			{
				if (params.get(i) != null && params.get(i).equals(""))
				{
					if (values.get(i) != null && isParam)
					{
						nameValuePairs.add(new BasicNameValuePair(params.get(i), values.get(i)));
					}
				}
			}
			//httpPost.setParams(new HttpParams());
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
		}

		/**
		 * Https SSL 보안 통신 방식 처리를 위한 인증 처리 부분
		 */

		// 속도가 빨라진다고 함..ㅋㅋ
		//httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		//httpclient.getParams().setParameter("http.connection.timeout", 60000);
		//httpclient.getParams().setParameter("http.socket.timeout", 60000);

		HttpResponse httpResponse = httpclient.execute(httpPost);

		String response = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);

		//Log.e("HYY", "response:" + response);

		return response;
		}

	public HttpClient getNewHttpClient()
	{
		try
		{
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);
			SSLSocketFactory sf = new MySSLSocketFactory(trustStore);


			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			params.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, 30);
			params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, new ConnPerRouteBean(30));
			params.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, false);


			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
			return new DefaultHttpClient(ccm, params);
		}
		catch (Exception e)
		{
			return new DefaultHttpClient();
		}
	}
	public String send(String url)
	{

		try {
			result = sendGETString(url, para, val, true);
		} catch (SocketTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	protected String doInBackground(String... params) {

		try {
			result = sendGETString(params[0], para, val, true);
			//this.clearPOST();
			result = result.substring(result.indexOf("name='")+6);
			while(result.indexOf("name='")!=-1)
			{	
				result = result.substring(result.indexOf("name='")+6);
				String a = result.substring(0, result.indexOf("'"));
				result = result.substring(result.indexOf("value='")+7);
				String v = result.substring(0, result.indexOf("'"));
				this.makeHttpPost(a, v);
				//Log.d("dc"+c, a + " " + v );
			}
			result = sendGETString("http://eam.unist.ac.kr/__tmax_eam_server__",para, val, true);
			//this.clearPOST();
			sendGETString("http://portal.unist.ac.kr/EP/web/portal/jsp/EP_Default1.jsp", para, val, true);
			for(int i=1; i<params.length; i++)
			{
				result = sendGETString(params[i], para, val, true);
			}

		} catch (SocketTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Log.e("http", result);
		Message msg = handle.obtainMessage();

		handle.sendMessage(msg);
		return result;
	}
	protected String onPostExecute(){
		Message msg = handle.obtainMessage();
		Log.e("dc", "post!!!!");
		handle.sendMessage(msg);
		return result;
	}
	public class MySSLSocketFactory extends SSLSocketFactory {
		SSLContext sslContext = SSLContext.getInstance("TLS");

		public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
			super(truststore);

			TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

			};

			sslContext.init(null, new TrustManager[] { tm }, null);
		}

		public MySSLSocketFactory(SSLContext context) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {
			super(null);
			sslContext = context;
		}

		@Override
		public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
			return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
		}

		@Override
		public Socket createSocket() throws IOException {
			return sslContext.getSocketFactory().createSocket();
		}
	}
}
