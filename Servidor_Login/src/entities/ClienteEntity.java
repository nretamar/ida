package entities;

import javax.persistence.*;

//import dto.Cuentascorrientes;

@Entity
@Table(name="clientes")
public class ClienteEntity {

	@Id
	private int codigocliente;
	
	private String nombre;
	private String apellido;
	private String cuit_cuil;
	private boolean responsableinscripto;
	private String condicionespago;
	private String advertencias;
	private String razonsocial;
	private String direccion;
	private String email;
	private String descuentos;
	private boolean activo;
	
	
	public ClienteEntity(){}

	public int getCodigocliente() {
		return codigocliente;
	}

	public void setCodigocliente(int codigocliente) {
		this.codigocliente = codigocliente;
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

	public String getCuit_cuil() {
		return cuit_cuil;
	}

	public void setCuit_cuil(String cuit_cuil) {
		this.cuit_cuil = cuit_cuil;
	}

	public boolean isResponsableinscripto() {
		return responsableinscripto;
	}

	public void setResponsableinscripto(boolean responsableinscripto) {
		this.responsableinscripto = responsableinscripto;
	}

	public String getCondicionespago() {
		return condicionespago;
	}

	public void setCondicionespago(String condicionespago) {
		this.condicionespago = condicionespago;
	}

	public String getAdvertencias() {
		return advertencias;
	}

	public void setAdvertencias(String advertencias) {
		this.advertencias = advertencias;
	}

	public String getRazonsocial() {
		return razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescuentos() {
		return descuentos;
	}

	public void setDescuentos(String descuentos) {
		this.descuentos = descuentos;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public ClienteEntity(String nombre, String apellido, String cuit_cuil,
			boolean responsableinscripto, String direccion, boolean activo){
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuit_cuil = cuit_cuil;
		this.responsableinscripto = responsableinscripto;
		this.direccion = direccion;
		this.activo = activo;
	}
	
	public ClienteEntity(int codigocliente, String nombre, String apellido, String cuit_cuil,
			boolean responsableinscripto, String direccion, boolean activo){
		this.codigocliente = codigocliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuit_cuil = cuit_cuil;
		this.responsableinscripto = responsableinscripto;
		this.direccion = direccion;
		this.activo = activo;
	}


	
}
