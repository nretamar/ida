package integracionDto;

import java.io.Serializable;

public class IntegracionItemOrdenDeCompraDistribuidoraDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8194328749739013372L;
	
	private Integer productId;
	private Integer amount;
	
	
	
	
	
	
	//Constructor
	public IntegracionItemOrdenDeCompraDistribuidoraDTO() {
		super();
	}
	
	
	
	
	

	//Getters y Setters
	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
	
}
