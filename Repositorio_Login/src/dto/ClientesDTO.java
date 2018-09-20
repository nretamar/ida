package dto;
// Generated 13/05/2018 19:11:50 by Hibernate Tools 5.2.3.Final

import java.io.Serializable;

public class ClientesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3450674929170243378L;
	private Integer codigocliente;

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

	// private Set ordenespedidoses = new HashSet(0);

	public ClientesDTO() {
	}

	public ClientesDTO(int codigoCliente, String nombre, String apellido, String cuitCuil, boolean responsableInscripto,
			String direccion, boolean activo) {
		this.codigocliente = codigoCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuit_cuil = cuitCuil;
		this.responsableinscripto = responsableInscripto;
		this.direccion = direccion;
		this.activo = activo;
	}

	public ClientesDTO(int codigoCliente, String nombre,
			String apellido, String cuitCuil, boolean responsableInscripto, String condicionesPago, String advertencias,
			String razonSocial, String direccion, String email, String descuentos, boolean activo) {
		this.codigocliente = codigoCliente;

		this.nombre = nombre;
		this.apellido = apellido;
		this.cuit_cuil = cuitCuil;
		this.responsableinscripto = responsableInscripto;
		this.condicionespago = condicionesPago;
		this.advertencias = advertencias;
		this.razonsocial = razonSocial;
		this.direccion = direccion;
		this.email = email;
		this.descuentos = descuentos;
		this.activo = activo;
	}

	public Integer getCodigocliente() {
		return codigocliente;
	}

	public void setCodigocliente(Integer codigocliente) {
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


}
