package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="proveedores")
public class ProveedorEntity {
	
	@Id
	@Column(name="id_Proveedor")
	private Integer idProveedor;
	
	private String nombre;
	private String url;
	private String apiKey;
	private boolean estadoActivo;
	private Integer idComoSuCliente;
	
	public ProveedorEntity() {}

	public ProveedorEntity(Integer idProveedor, String nombre, String url,
			String apiKey, boolean estadoActivo, Integer idComoSuCliente) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.url = url;
		this.apiKey = apiKey;
		this.estadoActivo = estadoActivo;
		this.idComoSuCliente = idComoSuCliente;
	}

	public ProveedorEntity(String nombre, String url, String apiKey,
			boolean estadoActivo, Integer idComoSuCliente ) {
		super();
		this.nombre = nombre;
		this.url = url;
		this.apiKey = apiKey;
		this.estadoActivo = estadoActivo;
		this.idComoSuCliente = idComoSuCliente;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public boolean getEstadoActivo() {
		return estadoActivo;
	}

	public void setEstadoActivo(boolean estadoActivo) {
		this.estadoActivo = estadoActivo;
	}

	public Integer getIdComoSuCliente() {
		return idComoSuCliente;
	}

	public void setIdComoSuCliente(Integer idComoSuCliente) {
		this.idComoSuCliente = idComoSuCliente;
	}
	
	
	
	
}
