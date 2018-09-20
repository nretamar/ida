package BusinessDelegate;

import java.rmi.RemoteException;

import excepciones.Exceptions;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		try {
			System.out.println(BusinessDelegate.getInstancia().sumarNumeros(2, 4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
