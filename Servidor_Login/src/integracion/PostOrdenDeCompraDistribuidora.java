package integracion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import conversiones.IntegracionConversionTienda;
import exceptions.OrdenDeCompraException;
import integracionDto.IntegracionOrdenDeCompraDistribuidorDTO;
import model.OrdenDeCompra;

public class PostOrdenDeCompraDistribuidora {
	
	
	public PostOrdenDeCompraDistribuidora(OrdenDeCompra orden) throws  OrdenDeCompraException {
		
		//Cambiar este comentario, el localhost es para pruebas
		String IP = orden.getProducto().getProveedor().getUrl();
		//String IP = "http://localhost:8080";
		
		IntegracionOrdenDeCompraDistribuidorDTO enviar = IntegracionConversionTienda.getInstancia().ordenAlmacenToTienda(orden.toDTO());
		
		JSONObject jsonDto = new JSONObject(enviar);
		
		System.out.println("\nINICIO del POST");
		System.out.println("PostOrdenDeCompra: " + jsonDto.toString() + " ---Fue enviado---->  (Esperando respuesta, si abajo resulta 201, es OK)...");
		
		
		StringEntity entity;
		try {
			entity = new StringEntity(jsonDto.toString(), "UTF-8");
			HttpClient httpClient = HttpClientBuilder.create().build();
			//HttpPost request = new HttpPost(IP+"/ordenTest");
			HttpPost request = new HttpPost(IP+"/purchaseOrdersOne");
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json"); 
			request.setEntity(entity);
			
			//Api-Key
			//request.addHeader("Authorization", "Bearer " + orden.getProducto().getProveedor().getApiKey());//Agrega token al http request
			request.addHeader("Authorization", orden.getProducto().getProveedor().getApiKey());//Agrega token al http request
						
			HttpResponse response = httpClient.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("\nOutput from Distribuidora ....");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			System.out.println("FIN del POST\n");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
