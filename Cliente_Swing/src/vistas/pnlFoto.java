package vistas;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dto.ProductoDTO;

public class pnlFoto extends JPanel{
	
	private static final long serialVersionUID = 8545256163337341361L;
	
	JFrame principal;
	ImageIcon fondo, foto;
	
	public pnlFoto(JFrame frm, ProductoDTO producto) {
		principal=frm;
		foto = producto.getFoto();
		Inicializar();
		this.setVisible(true);
		
	}
	private void Inicializar() {
		this.setLayout(null);
		this.setSize(600, 450);
		
		
	}
	
	public void paint(Graphics g) {
		fondo = new ImageIcon(getClass().getResource("/vistas/main3.jpg"));
		g.drawImage(fondo.getImage(), 0, 0, 400, 400, null);
		
		if(foto!=null)
			g.drawImage(foto.getImage(), 20, 15, 360, 350, null);
		setOpaque(false);
		super.paint(g);
	}

}
