package integracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import negocio.Credito;
import negocio.Factura;



public class PostLogistica {
	
	//Quizas borrar esto de abajo
	private final String  NROALMACEN = "1";
	
	
	public PostExpedicion(Factura factura) throws  SistemaTarjetaException {
	
}



public class PostTarjeta {

	private final String  NROESCUELA = "11";

	public PostTarjeta(Factura factura) throws  SistemaTarjetaException {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("direcciones.properties");

			// load a properties file
			prop.load(input);
		}
		catch(Exception e){
			
		}
			
		String IP = prop.getProperty("ipTarjeta");
		
		
		JSONObject json = new JSONObject();
		try {
			json.accumulate("fecha", factura.getFechaEmision().toLocalDate());
			json.accumulate("idEstablecimiento", NROESCUELA);// NroEstablecimiento
			json.accumulate("codigoSeguridad", ((Credito)factura.getTitular().getTipoDePago()).getCodSeg());
			json.accumulate("descripcion", "Escuela cuota: " 
                    +"Nro de Factura: "
                    +factura.getNumero()
                    +" - Periodo: "+factura.getPeriodo()+" - "+factura.getAnio());
			json.accumulate("monto", new BigDecimal(factura.getCostoTotal()));

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(json.toString());
		String link = IP+"/tarjetas/"+((Credito)factura.getTitular().getTipoDePago()).getNumeroTarjeta()+"/consumosEnteros";


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
				throw new SistemaTarjetaException();
			}
		} catch (IOException e) {
			throw new SistemaTarjetaException();
		}

		

	}
}