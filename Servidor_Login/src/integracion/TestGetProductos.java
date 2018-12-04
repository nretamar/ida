package integracion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
//import org.json.JSONException;
//import org.json.JSONObject;

import exceptions.ProductoException;

public class TestGetProductos {

	public static void main(String[] args) {
		
		String IP = "http://192.168.1.10:8080";
		
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(IP + "/productos");
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
		} catch (IOException e) {
			try {
				throw new ProductoException("");
			} catch (ProductoException e1) {
				e1.printStackTrace();
			}
		}
		
		
		
		/*JSONObject json = new JSONObject();
		try {
			json.accumulate("cliente", "AlbertoTest");
			json.accumulate("Fragil", "true");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		
		
		System.out.println(json.toString());
		//String link = IP+"/pedidosIn/";
		String link = "http//192.168.214.111\\:8080/productos/";
		
		StringEntity entity;
		try {
			entity = new StringEntity(json.toString(), "UTF-8");
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(link);

			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");
			request.setEntity(entity);

			HttpResponse response = httpClient.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(response);
			if(response.getStatusLine().getStatusCode() != 201 && response.getStatusLine().getStatusCode() != 200 ) {
				System.out.println(response.getStatusLine().getStatusCode());
				try {
					throw new PedidoException("");
				} catch (PedidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			try {
				throw new PedidoException("");
			} catch (PedidoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}*/
		
		
		
	}

}
