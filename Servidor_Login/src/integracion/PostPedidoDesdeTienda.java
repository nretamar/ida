package integracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import exceptions.PedidoException;
import integracionDto.IntegracionPedidoTiendaDTO;

public class PostPedidoDesdeTienda {
	
	public PostPedidoDesdeTienda(IntegracionPedidoTiendaDTO pedido) throws  PedidoException {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("direcciones.properties");

			// load a properties file
			prop.load(input);
		}
		catch(Exception e){
			
		}
			
		String IP = prop.getProperty("ipAlmacen");
		
		//String IP = "http://localhost:8080";
		
		/*
		 * PARA PROBAR: Creo un Test, pero deberías utilizar el "pedido" que pide este metodo 'in' como constructor
		 */
		
		JSONObject jsonDto = new JSONObject(pedido);
		
		
		//Esto es para corroborar cómo será mandado
		System.out.println(jsonDto.toString());


		StringEntity entity;
		try {
			entity = new StringEntity(jsonDto.toString(), "UTF-8");
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(IP+"/pedido");
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");
			request.setEntity(entity);

			HttpResponse response = httpClient.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
