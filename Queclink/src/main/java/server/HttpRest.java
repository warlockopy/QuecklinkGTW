package server;

import java.io.*;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

public class HttpRest {
	public static String urlscope = "https://us.scopemp.net/Scope.MProfiler.ThirdPartyGateway.Api/api/v1/messages";
	public static String ScopeHost = "us.scopemp.net";
	public static String ScopePath = "/Scope.MProfiler.ThirdPartyGateway.Api/api/v1/messages";
	public static String username = "PYLS", password = "Dn1f8C5XeJj42AzG";
	
	
	public static HttpOutput httpsClientC(String datoJson) throws Exception {
		
		String output = "";
		int httpCode = 0;
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(urlscope);
		String mydatetime, auheader;
		
		mydatetime = MD5.getCurrentTime();
		post.setHeader("Accept", "application/json");
		post.setHeader(HTTP.CONTENT_TYPE, "application/json");
		
		auheader =  username + ":" + password;
		String encodedaut = Base64.encodeBase64String(auheader.getBytes());

		
		post.addHeader("Authorization", "Basic " + encodedaut);
		post.addHeader("date", mydatetime);
		
		StringEntity input = new StringEntity(datoJson);
		input.setContentType("application/json");
		post.setEntity(input);
		
		CloseableHttpResponse response = httpclient.execute(post);
		
		try {
			System.out.print(response.getProtocolVersion());
			httpCode = response.getStatusLine().getStatusCode();
			System.out.print(" " + httpCode);
			System.out.println(" " + response.getStatusLine().getReasonPhrase());
			
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			System.out.println("\nOutput from Server...\n");
			
			String line;
			
			while ((line = br.readLine()) != null){
				output = line;
				System.out.println(output);
			}
				
			System.out.println ();
			
		} finally {
			response.close();
		}
		
		return new HttpOutput (httpCode, output);
	}
	
	public static String makesignature(String apikey, String stringtosign) {
		
		return "";
	}
	
	public static String string2signCC(String content, String datetime){
		
		return "POST" + "|" + MD5.getMD5(content) + "|" + "application/json" + "|" + datetime + "|" + urlscope;
	} 
	
	public static void httpsApacheClientAuth(String dato) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpHost targethost = new HttpHost(ScopeHost, 80, "http");
		CloseableHttpResponse response = null;
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
				new AuthScope(targethost.getHostName(), targethost.getPort()),
				new UsernamePasswordCredentials(username, password));
		AuthCache authcache = new BasicAuthCache();
		BasicScheme basicAuth = new BasicScheme();
		authcache.put(targethost, basicAuth);
		//Agregar el cache al contexto de la ejecucion
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authcache);
		
		HttpPost post = new HttpPost(ScopePath);
		 
		for (int i = 0; i < 3; i++){
			try {
				response = httpclient.execute(targethost, post, context);
				System.out.print("Response: ");
				System.out.print(response.getProtocolVersion() + " ");
				System.out.print(response.getStatusLine().getStatusCode() + " ");
				System.out.println(response.getStatusLine().getReasonPhrase() + "\n");
				HttpEntity entity = response.getEntity();
			} finally {
				response.close();
			}
		}	
		
	}
}
