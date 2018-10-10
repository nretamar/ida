package test;


import java.rmi.RemoteException;

import servicios.Servicios;



public class LevantarServer {

	public static void main(String[] args) {
		
			try {
				new server.Server();
				Servicios.getInstancia().levantarServicios();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
}
