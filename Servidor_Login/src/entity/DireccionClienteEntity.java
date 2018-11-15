package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="direccionCliente")
public class DireccionClienteEntity {
	
	@Id
	private Integer idDireccionCliente;
	private String calle;
	private String numero;
	private String piso;
	private String unidad;
	private String entreCalles;
	private String provincia;
	private String localidad;
	private String codigoPostal;
	
	
	
	public DireccionClienteEntity() {}
	
	
	
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
