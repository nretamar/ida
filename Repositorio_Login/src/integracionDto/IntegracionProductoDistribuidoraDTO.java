package integracionDto;

import java.io.Serializable;
import java.math.BigDecimal;

public class IntegracionProductoDistribuidoraDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3561877460342581369L;
	
	private Integer ID;
	private String Description;
	private boolean ForSale;
	private BigDecimal Price;
	private String Title;
	
	//Constructor
	public IntegracionProductoDistribuidoraDTO() {
		super();
	}

	//Getters y setters
	public Integer getID() {
		return ID;
	}
	
	public void setID(Integer iD) {
		ID = iD;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String description) {
		Description = description;
	}
	
	public boolean isForSale() {
		return ForSale;
	}
	
	public void setForSale(boolean forSale) {
		ForSale = forSale;
	}
	
	public BigDecimal getPrice() {
		return Price;
	}
	
	public void setPrice(BigDecimal price) {
		Price = price;
	}
	
	public String getTitle() {
		return Title;
	}
	
	public void setTitle(String title) {
		Title = title;
	}
	
	

}
