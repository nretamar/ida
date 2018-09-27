package server;


import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import remoteImplementation.ObjetoRemoto;
import remoteInterfaz.ClienteRemote;

public class Server {

	public Server() throws RemoteException{
		ClienteRemote or = new ObjetoRemoto();
		
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//localhost/TP2018", or);
			System.out.println("Servidor Levantado");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
