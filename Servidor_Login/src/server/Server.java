package server;


//import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import remoteImplementation.ComprasRemoteImpl;
import remoteImplementation.ExpedicionRemoteImpl;
import remoteImplementation.ProductoRemoteImpl;
import remoteImplementation.ProveedorRemoteImpl;
import remoteInterfaz.ComprasRemote;
import remoteInterfaz.ExpedicionRemote;
import remoteInterfaz.ProductoRemote;
import remoteInterfaz.ProveedorRemote;

public class Server {

	public Server() throws RemoteException{
		
		try{
			LocateRegistry.createRegistry(1099);
			
			ProductoRemote productoRemote = new ProductoRemoteImpl();
			ExpedicionRemote expedicionRemote = new ExpedicionRemoteImpl();
			ComprasRemote comprasRemote = new ComprasRemoteImpl();
			ProveedorRemote proveedorRemote = new ProveedorRemoteImpl();
			
			Naming.bind("//localhost:1099/ProductoRemote", productoRemote);
			Naming.bind("//localhost:1099/ExpedicionRemote", expedicionRemote);
			Naming.bind("//localhost:1099/ComprasRemote", comprasRemote);
			Naming.bind("//localhost:1099/ProveedorRemote", proveedorRemote);
			
			//Naming.rebind("//localhost/TP2018", or);
			System.out.println("Servidor Levantado");
		//} catch (MalformedURLException e) {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
