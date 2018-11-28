package com.integracion.controlador;

import businessDelegate.ProductoDelegate;
import dto.ProductoDTO;
import excepciones.GenericRemoteException;

public class TestConexion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ProductoDTO producto = null;
		System.out.println("Entre a buscar");
		try {
			producto = ProductoDelegate.getInstancia().buscarProductoById(1);
			System.out.println("Encontre producto nombre: " + producto.getDescripcion());
		} catch (GenericRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
