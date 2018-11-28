package dto;

import java.io.Serializable;
import java.util.List;

public class TestDTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1475189535620676007L;
	
	private Integer id;
	private String nombre;
	private String apellido;
	private List<TestItemDTO> materias;
	
	public TestDTO() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	
	public List<TestItemDTO> getMaterias() {
		return materias;
	}

	public void setMaterias(List<TestItemDTO> materias) {
		this.materias = materias;
	}
	
	

}
