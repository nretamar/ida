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
			
		String IP = prop.getProperty("ipLogistica");
		
		JSONObject json = new JSONObject();
		try {
			json.accumulate("idPedido", pedido.getIdPedido());
			json.accumulate("idAlmacen", NROALMACEN);// NroEstablecimiento
			
			for(PedidoItem item : pedido.getItems()) {
				//TODO
				
				
				
				
				//TODO
			}
			
			json.accumulate("estadoPedido", pedido.getEstadoPedido());
			json.accumulate("requiereLogistica", pedido.getTPersonaYfLogistica());
			
			json.accumulate("cliente",
					"idCliente: " + pedido.getCliente().getIdClienteTienda()
					+ " - cuil_cuit_dni: " + pedido.getCliente().getCuil_cuit_dni()
					+ " - nombreYApellido_RazonSocial: " + pedido.getCliente().getNombreYApellido_RazonSocial()
					+ " - email: " + pedido.getCliente().getEmail());
			json.accumulate("direccion",
					"calle: " + pedido.getDireccion().getCalle()
					+ " - numero: " + pedido.getDireccion().getNumero()
					+ " - piso: " + pedido.getDireccion().getPiso()
					+ " - unidad: " + pedido.getDireccion().getUnidad()
					+ " - entreCalles" + pedido.getDireccion().getEntreCalles()
					+ " - provincia: " + pedido.getDireccion().getProvincia()
					+ " - localidad: " + pedido.getDireccion().getLocalidad()
					+ " - codigoPostal: " + pedido.getDireccion().getCodigoPostal());
			json.accumulate("fragil", pedido.getFragil());
			json.accumulate("estadoPedido", pedido.getItems());// NroEstablecimiento
			

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		System.out.println(json.toString());
		String link = IP+"/pedidosIn/";


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
			System.out.println(response);
			if(response.getStatusLine().getStatusCode() != 201 && response.getStatusLine().getStatusCode() != 200 ) {
				System.out.println(response.getStatusLine().getStatusCode());
				throw new PedidoException("");
			}
		} catch (IOException e) {
			throw new PedidoException("");
		}
		
		
		
		
	}
	
}