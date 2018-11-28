package dto;

import java.io.Serializable;

public class TestItemDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1113848224165029402L;
	
	private Integer idMateria;
	private String descripcion;
	
	public TestItemDTO() {
	}

	public Integer getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	

}
