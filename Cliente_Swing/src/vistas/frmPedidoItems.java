package vistas;

import javax.swing.JFrame;

import dto.PedidoDTO;

public class frmPedidoItems extends JFrame{

	private static final long serialVersionUID = 7510362094763743231L;
	PedidoDTO pedido;
	
	public frmPedidoItems(PedidoDTO pedido){
		this.pedido = pedido;
		Inicializar();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		pnlPedidoItems pnl= new pnlPedidoItems(this, pedido);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Items del Pedido");
		this.setBounds(250, 150, 600, 450);
		this.add(pnl);
		this.setResizable(false);
	}

}
