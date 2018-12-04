package integracionDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IntegracionProductoListaDistribuidorDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4883939875010147439L;
	
	private List<IntegracionProductoDistribuidoraDTO> products;

	
	//Constructor
	public IntegracionProductoListaDistribuidorDTO() {
		products = new ArrayList<IntegracionProductoDistribuidoraDTO>();
	}

	
	
	
	//Getters y setters
	public List<IntegracionProductoDistribuidoraDTO> getProducts() {
		return products;
	}

	public void setProducts(List<IntegracionProductoDistribuidoraDTO> products) {
		this.products = products;
	}
	
	
	
	
}
