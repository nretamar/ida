package controlador;

import java.util.ArrayList;
import java.util.List;

import dao.ProveedorDAO;
import dto.ProveedorDTO;
import exceptions.ProveedorException;
import model.Proveedor;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : @Almac�n
//  @ File Name : ProveedorControlador.java
//  @ Date : @Sept2018
//  @ Author : @Grupo 1
//		Boujon Di Maio, Antonella.
//		Gomila, Tomas Guido.		
//		Graue, Florencia.
//		Retamar, Nicolas.
//
//




public class ProveedorControlador {
	
	public static ProveedorControlador instancia;
	
	private  ProveedorControlador() {
	}
	
	public static ProveedorControlador getInstancia() {
		if (instancia == null) {
			instancia = new ProveedorControlador();
		}
		return instancia;
	}
	
		
	public List<ProveedorDTO> findAllProveedores() {
		List<ProveedorDTO> proveedores = new ArrayList<ProveedorDTO>();
		
		try {
			for (Proveedor proveedor : ProveedorDAO.getInstancia().getAll())
			{
				if(proveedor.getEstadoActivo() == true)
				{
					proveedores.add(proveedor.toDTO());
				}
			}
		} catch (ProveedorException e) {
			e.printStackTrace();
		}

		return proveedores;
	}
	
	public Integer altaProveedor(ProveedorDTO proveedor) {
		ProveedorDTO p = buscarProveedorByUrl(proveedor.getUrl());
		//Si existe un proveedor con misma URL, no lo doy de alta. Reglas de negocio.
		if(p != null)
		{
			return null;
		}
		
		p = null;
		p = buscarProveedorByNombre(proveedor.getNombre());
		//Si existe un proveedor con mismo nombre, no lo doy de alta. Reglas de negocio.
		if(p != null)
		{
			return null;
		}
		
		proveedor.setEstadoActivo(true);
		return new Proveedor(proveedor).save().toDTO().getIdProveedor();
	}
	
	//Se da de baja al proveedor y sus productos
	public void bajaProveedor(Integer idProveedor) {
		try {
			ProveedorDAO.getInstancia().buscar(idProveedor).darDeBaja();
			ProductoControlador.getInstancia().darDeBajaPorIdProveedor(idProveedor);
		} catch (ProveedorException e) {
			e.printStackTrace();
		}
	}
	
	public void modificarProveedor(ProveedorDTO proveedor) {
		new Proveedor(proveedor).save();
	}
	
	public ProveedorDTO buscarProveedorById(Integer idProveedor) {
		try {
			return ProveedorDAO.getInstancia().buscar(idProveedor).toDTO();
		} catch (ProveedorException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ProveedorDTO buscarProveedorByUrl(String url) {
		for (ProveedorDTO proveedor : findAllProveedores()) {
			if(proveedor.getUrl().equals(url))
				return proveedor;
		}
		return null;
	}
	
	public ProveedorDTO buscarProveedorByNombre(String nombre) {
		for (ProveedorDTO proveedor : findAllProveedores()) {
			if(proveedor.getNombre().equals(nombre))
				return proveedor;
		}
		return null;
	}
}
