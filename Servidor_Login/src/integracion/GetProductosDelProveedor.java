package integracion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import exceptions.ProductoException;
import integracionDto.IntegracionProductoDistribuidoraDTO;
import integracionDto.IntegracionProductoListaDistribuidorDTO;
import jdk.nashorn.internal.parser.JSONParser;

public class GetProductosDelProveedor {

	public static void main(String[] args) {
		
		/*
		 * NO FUNCIONAAAAA :(
		 * 
		 */
		
		
		String IP = "http://iap-2018.herokuapp.com";
		List<IntegracionProductoDistribuidoraDTO> articulos = new ArrayList<IntegracionProductoDistribuidoraDTO>();
		
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(IP + "/products?limit=&offset=");
			getRequest.addHeader("accept", "application/json");
			getRequest.addHeader("Authorization", "a9fd27d1-5918-4dfa-8239-18ff706055a2");//Agrega token al http request
			HttpResponse response = httpClient.execute(getRequest);
			
			
			
			JSONObject jsonResponse = new JSONObject(response);
			Gson gson=new GsonBuilder().create();
			IntegracionProductoListaDistribuidorDTO listaItem = gson.fromJson(jsonResponse.getJSONObject("products").toString(), IntegracionProductoListaDistribuidorDTO.class);
			System.out.println("Response json: " + response.getEntity());
			
			

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			/*while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			br.reset();
			*/
			/*
			 * 
			 */
			StringBuilder result = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
				System.out.println(line);
			}
			br.close();
			System.out.println(result);
			
			 System.out.println("MI result: " + result);
			 
			 //
			 JSONParser parser = new JSONParser(result.toString(), null, false);
		     JSONObject json = (JSONObject) parser.parse();
		     JSONArray jsonArray = (JSONArray) json.get("list");

		     for (int i = 0; i < jsonArray.length(); i++) {
		    	 JSONObject jsonobject = (JSONObject) jsonArray.get(i);
		     }
			 //
			 System.out.println("Llegue aca");
			 JSONArray items = new JSONArray(result.toString());
			
			 
			 
			 for (int i = 0; i < items.length(); i++)
		      {
				 IntegracionProductoDistribuidoraDTO art = new IntegracionProductoDistribuidoraDTO();
				 art.setID(Integer.parseInt(items.getJSONObject(i).getString("ID")));
				 art.setDescription(items.getJSONObject(i).getString("Description"));
				 art.setTitle(items.getJSONObject(i).getString("Title"));				 
				 art.setForSale(Boolean.parseBoolean(items.getJSONObject(i).getString("ForSale")));
				 art.setPrice(new BigDecimal(items.getJSONObject(i).getString("Price")));
				 
		          articulos.add(art);
		      }
			 
			 for(IntegracionProductoDistribuidoraDTO art : articulos){
				 System.out.println("Descripcion: " + art.getDescription());
			 }
			 
			/*
			 * 
			 */
			
			
			
			
			
		} catch (IOException e) {
			try {
				throw new ProductoException("");
			} catch (ProductoException e1) {
				e1.printStackTrace();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	
	/*
	public List<IntegracionProductoDistribuidora> listarArticulos(String IP) throws RemoteException, ProductoException{
		List<IntegracionProductoDistribuidora> articulos = new ArrayList<IntegracionProductoDistribuidora>();
		String urlString = IP + "/products?limit=&offset=";	
		StringBuilder result = new StringBuilder();
	    URL url;
		try {
			url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		      conn.setRequestMethod("GET");
		      
		      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      String line;
		      while ((line = rd.readLine()) != null) {
		         result.append(line);
		      }
		      rd.close();
		      System.out.println(result);
		      
		      //JSONObject obj = new JSONObject(result.toString());

		      JSONArray items = new JSONArray(result.toString());
		      //JSONArray items = obj.getJSONArray("items");

		      for (int i = 0; i < items.length(); i++)
		      {
		    	  ArticuloDTO art = new ArticuloDTO();
		    	  art.setDescripcion(items.getJSONObject(i).getString("descripcion"));
		    	  art.setFoto(items.getJSONObject(i).getString("fotoUrl"));
		    	  art.setIdArticulo(items.getJSONObject(i).getInt("idProducto"));
		    	  art.setPrecioUnitario(items.getJSONObject(i).getDouble("precioVenta"));
		    	  art.setStock(items.getJSONObject(i).getInt("stockActual"));
		    	  art.setCodigoBarras(items.getJSONObject(i).getString("codigoBarras"));
		          articulos.add(art);
		      }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		return articulos;
		//return ModuloVentas.getInstance().listarArticulos();
	}*/
	
	
	
	
	

}
