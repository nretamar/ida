package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clienteTienda")
public class ClienteTiendaEntity {
	
	@Id
	private Integer idClienteTienda;
	
	private String cuil_cuit_dni;
	private String nombreYApellido_RazonSocial;
	private String email;
	
	public ClienteTiendaEntity() {}

	public Integer getIdClienteTienda() {
		return idClienteTienda;
	}

	public void setIdClienteTienda(Integer idClienteTienda) {
		this.idClienteTienda = idClienteTienda;
	}

	public String getCuil_cuit_dni() {
		return cuil_cuit_dni;
	}

	public void setCuil_cuit_dni(String cuil_cuit_dni) {
		this.cuil_cuit_dni = cuil_cuit_dni;
	}

	public String getNombreYApellido_RazonSocial() {
		return nombreYApellido_RazonSocial;
	}

	public void setNombreYApellido_RazonSocial(String nombreYApellido_RazonSocial) {
		this.nombreYApellido_RazonSocial = nombreYApellido_RazonSocial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
