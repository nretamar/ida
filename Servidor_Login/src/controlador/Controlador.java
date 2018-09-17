package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.ClienteDAO;
import dto.ClientesDTO;
import exceptions.ClienteException;
import negocio.Cliente;

public class Controlador {

	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	public int sumarNumeros(int a, int b) {
		return a + b;
	}
	
	

	/*********************************************************************************************************
	 * Clientes
	 *********************************************************************************************************/

	public int grabarCliente(ClientesDTO c, String ABM) {
		Integer ret = null;
		Cliente cli = new Cliente(c);
		ret = cli.grabar(ABM);
		return ret;
	} 
	
	public ClientesDTO buscarCliente(Integer idCliente) throws ClienteException {
		return ClienteDAO.getInstance().buscar(idCliente).toDTO();
	}
	
	public ClientesDTO buscarClienteporUsuario(Integer idUsuario) throws ClienteException {
		return ClienteDAO.getInstance().buscarPorUsuario(idUsuario).toDTO();
	}

	

	public int siguienteCodigoCliente() throws ClienteException {
		int codigocliente;
		codigocliente = 0;
		try {
			codigocliente = ClienteDAO.getInstance().ultimoCodigoCliente();
		} catch (ClienteException e) {
			e.printStackTrace();
		}

		return codigocliente;
	}

	public List<ClientesDTO> listarClientes() throws ClienteException {
		List<ClientesDTO> resultado = new ArrayList<ClientesDTO>();

		for (Cliente item : ClienteDAO.getInstance().listar()) {
			resultado.add(item.toDTO());
		}

		return resultado;
	}





}
