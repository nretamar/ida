package test;


import java.rmi.RemoteException;



public class LevantarServer {

	public static void main(String[] args) {
		
			try {
				new servidor.Server();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
}
