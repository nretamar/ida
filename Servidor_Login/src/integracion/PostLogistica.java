package integracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
//.jar   httpclient-4.5.6 los dos de abajo
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
//.jar java.json
import org.json.JSONException;
import org.json.JSONObject;

import exceptions.PedidoException;
import model.Pedido;
import model.PedidoItem;

public class PostLogistica {
	
	//Quizas borrar esto de abajo
	private final String  NROALMACEN = "1";
	
	
	public PostLogistica(Pedido pedido) throws  PedidoException {
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
		
		JSONObject json = new JSONObject();
		
		//Como lo es en pedido model de nuestro tp
		try {
			json.accumulate("idPedido", pedido.getIdPedido());
			json.accumulate("idAlmacen", NROALMACEN);// NroEstablecimiento
			
			
			for(PedidoItem item : pedido.getItems()) {
				//TODO
				JSONObject jsonItem = new JSONObject();
				jsonItem.put("descripcion", item.getProducto().getDescripcion());
				jsonItem.put("cantidad", item.getCantidad());
				json.put("itemsPedido", jsonItem);
				
				//TODO
			}
			
			json.accumulate("estadoPedido", pedido.getEstadoPedido().toString());
			//json.accumulate("requiereLogistica", pedido.getTPersonaYfLogistica());
			
			//Cliente
			JSONObject jsonCliente = new JSONObject();
			jsonCliente.put("idCliente" , pedido.getCliente().getIdClienteTienda());
			jsonCliente.put("cuil_cuit_dni", pedido.getCliente().getCuil_cuit_dni());
			jsonCliente.put("nombreYApellido_RazonSocial", pedido.getCliente().getNombreYApellido_RazonSocial());
			jsonCliente.put("email", pedido.getCliente().getEmail());
			json.put("Cliente", jsonCliente);
			
			//Direccion
			JSONObject jsonDireccion = new JSONObject();
			jsonDireccion.put("calle" , pedido.getDireccion().getCalle());
			jsonDireccion.put("numero" , pedido.getDireccion().getNumero());
			jsonDireccion.put("piso" , pedido.getDireccion().getPiso());
			jsonDireccion.put("unidad" , pedido.getDireccion().getUnidad());
			jsonDireccion.put("entreCalles" , pedido.getDireccion().getEntreCalles());
			jsonDireccion.put("provincia" , pedido.getDireccion().getProvincia());
			jsonDireccion.put("localidad" , pedido.getDireccion().getLocalidad());
			jsonDireccion.put("codigoPostal" , pedido.getDireccion().getCodigoPostal());
			json.put("Direccion", jsonDireccion);
			
			json.accumulate("fragil", pedido.getFragil());
			

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//Como me lo pide logistica
		/*
		try {
			json.accumulate("idPedido", pedido.getIdPedido());
			json.accumulate("idAlmacen", NROALMACEN);// NroEstablecimiento
			
			for(PedidoItem item : pedido.getItems()) {
				//TODO
				JSONObject jsonItem = new JSONObject();
				jsonItem.put("descripcion", item.getProducto().getDescripcion());
				jsonItem.put("cantidad", item.getCantidad());
				json.put("itemsPedido", jsonItem);
				
				//TODO
			}
			
			json.accumulate("estadoPedido", pedido.getEstadoPedido());
			json.accumulate("requiereLogistica", pedido.getTPersonaYfLogistica());
			
			//Cliente
			JSONObject jsonCliente = new JSONObject();
			jsonCliente.put("idCliente" , pedido.getCliente().getIdClienteTienda());
			jsonCliente.put("cuil_cuit_dni", pedido.getCliente().getCuil_cuit_dni());
			jsonCliente.put("nombreYApellido_RazonSocial", pedido.getCliente().getNombreYApellido_RazonSocial());
			jsonCliente.put("email", pedido.getCliente().getEmail());
			json.put("Cliente", jsonCliente);
			
			//Direccion
			JSONObject jsonDireccion = new JSONObject();
			jsonDireccion.put("calle" , pedido.getDireccion().getCalle());
			jsonDireccion.put("numero" , pedido.getDireccion().getNumero());
			jsonDireccion.put("piso" , pedido.getDireccion().getPiso());
			jsonDireccion.put("unidad" , pedido.getDireccion().getUnidad());
			jsonDireccion.put("entreCalles" , pedido.getDireccion().getEntreCalles());
			jsonDireccion.put("provincia" , pedido.getDireccion().getProvincia());
			jsonDireccion.put("localidad" , pedido.getDireccion().getLocalidad());
			jsonDireccion.put("codigoPostal" , pedido.getDireccion().getCodigoPostal());
			json.put("Direccion", jsonDireccion);
			
			json.accumulate("fragil", pedido.getFragil());
			json.accumulate("estadoPedido", pedido.getEstadoPedido().toString());// NroEstablecimiento
			

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		
		
		
		System.out.println(json.toString());
		String link = IP+"/testt";
		
		StringEntity entity;
		try {
			entity = new StringEntity(json.toString(), "UTF-8");
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(link);

			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");
			request.setEntity(entity);

			HttpResponse response = httpClient.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			
		} catch (IOException e) {
			throw new PedidoException("");
		}
		
		
		
		
	}
	
}