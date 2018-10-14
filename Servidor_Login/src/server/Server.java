package server;


//import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import remoteImplementation.ComprasRemoteImpl;
import remoteImplementation.ExpedicionRemoteImpl;
import remoteImplementation.ObjetoRemoto;
import remoteImplementation.ProductoRemoteImpl;
import remoteInterfaz.ClienteRemote;
import remoteInterfaz.ComprasRemote;
import remoteInterfaz.ExpedicionRemote;
import remoteInterfaz.ProductoRemote;

public class Server {

	public Server() throws RemoteException{
		ClienteRemote or = new ObjetoRemoto();
		
		try{
			LocateRegistry.createRegistry(1099);
			
			ProductoRemote productoRemote = new ProductoRemoteImpl();
			ExpedicionRemote expedicionRemote = new ExpedicionRemoteImpl();
			ComprasRemote comprasRemote = new ComprasRemoteImpl();
			
			Naming.bind("//localhost:1099/ProductoRemote", productoRemote);
			Naming.bind("//localhost:1099/ExpedicionRemote", expedicionRemote);
			Naming.bind("//localhost:1099/ComprasRemote", comprasRemote);
			
			Naming.rebind("//localhost/TP2018", or);
			System.out.println("Servidor Levantado");
		//} catch (MalformedURLException e) {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
