package mainPackage;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import mysql2websrvc.MD5;






import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

//import feedback.HttpOutput;

//public String urlscope = "https://mzone.paraguaylogisticssystems.com/v2/places/place.json";
//public String username = "PYLS":
//public String password = "PYLS";

public class HttpsClient {
	
	//private static String urlscope = "https://mzone.paraguaylogisticssystems.com/api/v2/places/place.json";
	private static String urlscope = "https://us.mzoneweb.net/api/v2/places/place.json";
	private static String urlget = "https://us.mzoneweb.net/api/v2/places.json";
	private static String urlapiscope = "https://us.mzoneweb.net/api/v2/placeGroups/";
	private static String username = "administradoraguassanas";
	private static String password = "admin2015";
	private static String idGroup = "0ff854de-ecae-4734-ac35-d15e22d81b5a/";
	private static String jsonpage = "places.json";
	
public static int httpsClientAddPlaces (String datoJson) throws Exception {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//HttpPost post = new HttpPost(urlapiscope + idGroup + jsonpage);
		HttpPost post = new HttpPost(urlscope);
		//HttpGet post = new HttpGet(urlget);
		String mydatetime, auheader;
		String serverOutputString = "";
		
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
		int httperr;
		
		
		CloseableHttpResponse response = httpclient.execute(post);
		
		try {
			System.out.println("Respuesta del servidor");
			System.out.println(response.getProtocolVersion());
			httperr = response.getStatusLine().getStatusCode();
			System.out.println(httperr);
			System.out.println(response.getStatusLine().getReasonPhrase());
			
			BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

				String output;
				
				System.out.println("Output from Server .... \n");
				
				while ((output = br.readLine()) != null){
					System.out.println(output);
					serverOutputString += output;
				}
			
		} finally {
			response.close();
		}
		
		return httperr;
	}
public static String HttpsClient (String url, String user, String password) throws Exception {
	
	String datoJson = "";
	CloseableHttpClient httpclient = HttpClients.createDefault();
	
	HttpGet get = new HttpGet(url);

	String mydatetime, auheader;
	String serverOutputString = "";
	
	mydatetime = MD5.getCurrentTime();
	get.setHeader("Accept", "application/json");
	get.setHeader(HTTP.CONTENT_TYPE, "application/json");
	
	auheader =  user + ":" + password;
	String encodedaut = Base64.encodeBase64String(auheader.getBytes());

	get.addHeader("Authorization", "Basic " + encodedaut);
	get.addHeader("date", mydatetime);
	
	
	int httperr;
	
	
	CloseableHttpResponse response = httpclient.execute(get);
	
	
	try {
		System.out.println("Respuesta del servidor");
		System.out.println(response.getProtocolVersion());
		httperr = response.getStatusLine().getStatusCode();
		System.out.println(httperr);
		System.out.println(response.getStatusLine().getReasonPhrase());
		
		BufferedReader br = new BufferedReader(
			new InputStreamReader((response.getEntity().getContent())));

			
			String output;
			System.out.println("Output from Server .... \n");
			
			while ((output = br.readLine()) != null){
				System.out.println(output);
				serverOutputString += output;
			}
		
	} finally {
		response.close();
	}
	
	return serverOutputString;
}
public static String HttpsClientDelete (String url, String user, String password) throws Exception {
	
	String datoJson = "";
	CloseableHttpClient httpclient = HttpClients.createDefault();
	
	HttpDelete delete = new HttpDelete(url);

	String mydatetime, auheader;
	String serverOutputString = "";
	
	mydatetime = MD5.getCurrentTime();
	delete.setHeader("Accept", "application/json");
	delete.setHeader(HTTP.CONTENT_TYPE, "application/json");
	
	auheader =  user + ":" + password;
	String encodedaut = Base64.encodeBase64String(auheader.getBytes());

	delete.addHeader("Authorization", "Basic " + encodedaut);
	delete.addHeader("date", mydatetime);
	
	
	int httperr;
	
	
	CloseableHttpResponse response = httpclient.execute(delete);
	
	
	try {
		System.out.println("Respuesta del servidor");
		System.out.println(response.getProtocolVersion());
		httperr = response.getStatusLine().getStatusCode();
		System.out.println(httperr);
		System.out.println(response.getStatusLine().getReasonPhrase());
		
		//BufferedReader br = new BufferedReader(
			//new InputStreamReader((response.getEntity().getContent())));

			
			//String output;
			//System.out.println("Output from Server .... \n");
			
			/*while ((output = br.readLine()) != null){
				System.out.println(output);
				serverOutputString += output;
			}*/
		
	} finally {
		response.close();
	}
	
	return serverOutputString;
}
}
