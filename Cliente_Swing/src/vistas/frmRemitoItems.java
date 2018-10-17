package vistas;

import javax.swing.JFrame;

import dto.RemitoDTO;

public class frmRemitoItems extends JFrame{

	private static final long serialVersionUID = 1964051971521879933L;
	RemitoDTO remito;
	
	public frmRemitoItems(RemitoDTO remito){
		this.remito = remito;
		Inicializar();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		pnlRemitoItems pnl= new pnlRemitoItems(this, remito);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Items del Remito");
		this.setBounds(250, 150, 600, 450);
		this.add(pnl);
		this.setResizable(false);
	}

}
