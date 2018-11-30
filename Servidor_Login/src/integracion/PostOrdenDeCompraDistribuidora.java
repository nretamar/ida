package integracion;

import java.io.IOException;

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
		//String IP = orden.getProducto().getProveedor().getUrl();
		String IP = "http://localhost:8080";
		
		IntegracionOrdenDeCompraDistribuidorDTO enviar = IntegracionConversionTienda.getInstancia().ordenAlmacenToTienda(orden.toDTO());
		
		JSONObject jsonDto = new JSONObject(enviar);
		
		System.out.println("PostOrdenDeCompra: " + jsonDto.toString());
		
		
		StringEntity entity;
		try {
			entity = new StringEntity(jsonDto.toString(), "UTF-8");
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(IP+"/ordenTest");
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json"); 
			request.setEntity(entity);
			
			//Api-Key
			request.addHeader("Authorization", "Bearer " + orden.getProducto().getProveedor().getApiKey());//Agrega token al http request

			HttpResponse response = httpClient.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
