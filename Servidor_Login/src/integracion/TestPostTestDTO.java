package integracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import dto.TestDTO;
import dto.TestItemDTO;


public class TestPostTestDTO {

	public static void main(String[] args) {
		
		JSONObject json = new JSONObject();
		
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
		//try {
			/*json.accumulate("id", "1060761");// CBU Escuela
			json.accumulate("nombre", "Tomas Gomila");
			
			JSONObject jsonMateria = new JSONObject();
			jsonMateria.put("idMateria", 1);
			jsonMateria.put("descripcion", "am1");
			json.put("materias", jsonMateria);
			
			JSONObject jsonMateria2 = new JSONObject();
			jsonMateria2.put("idMateria", 2);
			jsonMateria2.put("descripcion", "am2");
			json.put("materias", jsonMateria2);*/
			
			TestDTO t = new TestDTO();
			t.setId(1);
			t.setNombre("Tomas");
			t.setApellido("Gomila");
			
			TestItemDTO t1 = new TestItemDTO();
			t1.setIdMateria(1);
			t1.setDescripcion("am1");
			
			TestItemDTO t2 = new TestItemDTO();
			t2.setIdMateria(2);
			t2.setDescripcion("am2");
			
			List<TestItemDTO> lista = new ArrayList<TestItemDTO>();
			lista.add(t1);
			lista.add(t2);
			t.setMaterias(lista);
			
			
			JSONObject jsonDto = new JSONObject(t);
			
			
		/*} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
//		json.accumulate("fecha", factura.getFechaEmision());
		
		System.out.println(jsonDto.toString());


		StringEntity entity;
		try {
			entity = new StringEntity(jsonDto.toString(), "UTF-8");
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(IP+"/test");
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
