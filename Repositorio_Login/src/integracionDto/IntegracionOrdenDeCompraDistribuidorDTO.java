package integracionDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class IntegracionOrdenDeCompraDistribuidorDTO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4349241128514123832L;
	
	private Integer client;
	private List<IntegracionItemOrdenDeCompraDistribuidoraDTO> items;
	
	
	//Constructor
	public IntegracionOrdenDeCompraDistribuidorDTO() {
		this.items = new ArrayList<IntegracionItemOrdenDeCompraDistribuidoraDTO>();
	}
	
	
	
	//Getters y Setters
	public Integer getClient() {
		return client;
	}


	public void setClient(Integer client) {
		this.client = client;
	}


	public List<IntegracionItemOrdenDeCompraDistribuidoraDTO> getItems() {
		return items;
	}


	public void setItems(List<IntegracionItemOrdenDeCompraDistribuidoraDTO> items) {
		this.items = items;
	}
	
	
	
	
}
