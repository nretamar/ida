package servicios;

import java.util.Timer;
import java.util.TimerTask;

import controlador.ExpedicionControlador;

public class Servicios {
	
	private static Timer timer;
	private static Servicios instancia;
	
	private Servicios() {
	}

	public static Servicios getInstancia() {
		if (instancia == null)
			instancia = new Servicios();
		return instancia;
	}
	
	/*public static void main(String[] args) {
		
		levantarServicios();

	}*/
	
	public void levantarServicios() {
		if(timer!=null)
			this.detenerServicios();
		timer = new Timer();
		timer.schedule(new pendiente(), 0, 15000);
		//timer.schedule(new Aviso(), 0, 10000);
		
		//Este print significa, que el timer corre para siempre y mi Syso de abajo se ejecuta
		//System.out.println("Llegue aca");
	}
	
	public void detenerServicios() {
		if(timer!=null) {
			timer.cancel();
			timer.purge();
			timer = new Timer();
		}
		
	}
	
	private class pendiente extends TimerTask {
	    public void run() {
	    	
	    	ExpedicionControlador.getInstancia().actualizarTodoFaltaDeStockAPendiente();
	    	System.out.println("Servicios.java: Pedidos con falta stock a pendientes, actualizado");
	       
	    }
	}
	
	private class Aviso extends TimerTask {
		 public void run() {
		       System.out.println("Pasaron 10seg");
		       
		    }
	}

}
