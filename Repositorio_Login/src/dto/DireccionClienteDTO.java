package dto;

import java.io.Serializable;

public class DireccionClienteDTO implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4931152374823321731L;
	
	private Integer idDireccionCliente;
	private String calle;
	private String numero;
	private String piso;
	private String unidad;
	private String entreCalles;
	private String provincia;
	private String localidad;
	private String codigoPostal;
	
	//Constructor
	public DireccionClienteDTO(Integer idDireccionCliente, String calle, String numero, String piso, String unidad,
			String entreCalles, String provincia, String localidad, String codigoPostal) {
		super();
		this.idDireccionCliente = idDireccionCliente;
		this.calle = calle;
		this.numero = numero;
		this.piso = piso;
		this.unidad = unidad;
		this.entreCalles = entreCalles;
		this.provincia = provincia;
		this.localidad = localidad;
		this.codigoPostal = codigoPostal;
	}
	
	
	public DireccionClienteDTO() {
		super();
	}


	public Integer getIdDireccionCliente() {
		return idDireccionCliente;
	}


	public void setIdDireccionCliente(Integer idDireccionCliente) {
		this.idDireccionCliente = idDireccionCliente;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getPiso() {
		return piso;
	}


	public void setPiso(String piso) {
		this.piso = piso;
	}


	public String getUnidad() {
		return unidad;
	}


	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}


	public String getEntreCalles() {
		return entreCalles;
	}


	public void setEntreCalles(String entreCalles) {
		this.entreCalles = entreCalles;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public String getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	
	
	
}
