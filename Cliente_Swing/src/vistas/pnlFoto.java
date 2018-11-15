package vistas;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dto.ProductoDTO;

public class pnlFoto extends JPanel{
	
	private static final long serialVersionUID = 8545256163337341361L;
	
	JFrame principal;
	ImageIcon fondo, foto;
	String fotoUrl;
	
	public pnlFoto(JFrame frm, ProductoDTO producto) {
		principal=frm;
		
		fotoUrl = producto.getFotoUrl();
		//foto = producto.getFoto();
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
		
		Image image = null;
		try {
			//URL url = new URL(fotoUrl);
			//TestUrl
			//URL url = new URL("https://i0.wp.com/www.southwestconferenceblog.org/wp-content/uploads/2018/05/this-is-a-test-wp.png?fit=825%2C510");
			URL url = new URL("https://lh5.googleusercontent.com/Xst_gkMySOI7UYd5inDdkFZ5ZhFoZSNln98fUOZsZdU5Ndk5eWHZaaHSJpSjF-UtJjrIi4vfTId_vg=w1366-h608");
			
			image = ImageIO.read(url);
			foto = new ImageIcon(image);
			
			if(foto!=null) {
				g.drawImage(foto.getImage(), 20, 15, 360, 350, null);
			}
			
		} catch (IOException e) {
		}
		
		setOpaque(false);
		super.paint(g);
	}

}
