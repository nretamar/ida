package vistas;

import javax.swing.JFrame;

import dto.ProductoDTO;


public class frmFoto extends JFrame{
	
	private static final long serialVersionUID = -3123875616886322925L;
	
	ProductoDTO producto;
	
	public frmFoto(ProductoDTO producto){
		this.producto=producto;
		Inicializar();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		pnlFoto pnl= new pnlFoto(this, producto);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Foto de producto (CodBarras): " + producto.getCodigoBarras());
		this.setBounds(250, 150, 400, 400);
		this.add(pnl);
		this.setResizable(false);
	}

}
