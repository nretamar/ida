package negocio;

import dao.ClienteDAO;
import dto.ClientesDTO;
import exceptions.ClienteException;

public class Cliente {

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

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(int codigoCliente, String nombre, String apellido, String cuitCuil, boolean responsableInscripto,
			String direccion, boolean activo) {
		this.codigocliente = codigoCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuit_cuil = cuitCuil;
		this.responsableinscripto = responsableInscripto;
		this.direccion = direccion;
		this.activo = activo;
	}

	public Cliente(String nombre, String apellido, String cuitCuil, boolean responsableInscripto, String direccion,
			boolean activo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuit_cuil = cuitCuil;
		this.responsableinscripto = responsableInscripto;
		this.direccion = direccion;
		this.activo = activo;
	}

	public Cliente(ClientesDTO cliente) {
		this.codigocliente = cliente.getCodigocliente();
		this.nombre = cliente.getNombre();
		this.apellido = cliente.getApellido();
		this.cuit_cuil = cliente.getCuit_cuil();
		this.responsableinscripto = cliente.isResponsableinscripto();
		this.condicionespago = cliente.getCondicionespago();
		this.advertencias = cliente.getAdvertencias();
		this.razonsocial = cliente.getRazonsocial();
		this.direccion = cliente.getDireccion();
		this.email = cliente.getEmail();
		this.descuentos = cliente.getDescuentos();
		this.activo = cliente.isActivo();
	
	}


	public ClientesDTO toDTO() {

		return new ClientesDTO(codigocliente, nombre, apellido, cuit_cuil, responsableinscripto, condicionespago,
				advertencias, razonsocial, direccion, email, descuentos, activo);
	}



	public Integer grabar(String ABM) {
		Integer respuesta;
		respuesta = null;
		System.out.println("entro al cliente");

		if (ABM.equalsIgnoreCase("i")){
			try {
				respuesta = ClienteDAO.getInstance().grabar(this);
			} catch (ClienteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		if (ABM.equalsIgnoreCase("u")){
			try {
				respuesta = ClienteDAO.getInstance().grabarConId(this);
			} catch (ClienteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ABM.equalsIgnoreCase("d")){
			this.activo= false;
			try {
				respuesta = ClienteDAO.getInstance().grabarConId(this);
			} catch (ClienteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		


		return respuesta;

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
