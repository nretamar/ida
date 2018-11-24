package vistas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class pnlAdministracion extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 441688042005748431L;
	ImageIcon fondo;
	JFrame principal;
	JButton btnCompras, btnProductos, btnProveedor, btnPedidos, btnAtras;
	
	public pnlAdministracion(JFrame frm){
		principal=frm;
		principal.setTitle("Administración");
		Inicializar();
		asignarEventos();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		this.setLayout(null);
		this.setSize(1000, 600);
		btnCompras = new JButton("Compras");
		btnCompras.setOpaque(false);
		btnCompras.setContentAreaFilled(false);
		btnCompras.setBounds(137, 225, 131, 30);
		
		btnProductos = new JButton("Productos");
		btnProductos.setOpaque(false);
		btnProductos.setContentAreaFilled(false);
		btnProductos.setBounds(434, 225, 131, 30);
		
		{
			btnProveedor = new JButton("Distribuidores");
			btnProveedor.setOpaque(false);
			btnProveedor.setContentAreaFilled(false);
			btnProveedor.setBounds(434, 265, 131, 30);
		}
		
		btnPedidos = new JButton("Pedidos");
		btnPedidos.setOpaque(false);
		btnPedidos.setContentAreaFilled(false);
		btnPedidos.setBounds(725, 225, 131, 30);
		
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(60, 460, 131, 30);
		
		this.add(btnCompras);
		this.add(btnProductos);
		this.add(btnProveedor);
		this.add(btnPedidos);
		this.add(btnAtras);
	}
	
	private void asignarEventos(){
		btnAtras.addActionListener(this);
		btnCompras.addActionListener(this);
		btnProductos.addActionListener(this);
		btnProveedor.addActionListener(this);
		btnPedidos.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent click){
		if(click.getActionCommand().equals("Atras")){
			JPanel pnlM= new pnlMain(principal);
			pnlM.setBounds(0, 0, 1000, 600);
			principal.remove(this);
			principal.add(pnlM);
			principal.repaint();
		}
		else{
			if(click.getActionCommand().equals("Compras")){
				JPanel pnlAdmC= new pnlAdminCompras(principal);
				pnlAdmC.setBounds(0, 0, 1000, 600);
				principal.remove(this);
				principal.add(pnlAdmC);
				principal.repaint();
				}
			else{
				if(click.getActionCommand().equals("Productos")){
					JPanel pnlAdmA= new pnlAdminProductos(principal);
					pnlAdmA.setBounds(0, 0, 1000, 600);
					principal.remove(this);
					principal.add(pnlAdmA);
					principal.repaint();
				}
				else{
					if(click.getActionCommand().equals("Pedidos")){
						JPanel pnlAdmP= new pnlAdminPedidos(principal);
						pnlAdmP.setBounds(0, 0, 1000, 600);
						principal.remove(this);
						principal.add(pnlAdmP);
						principal.repaint();
					}
					else{
						if(click.getActionCommand().equals("Distribuidores")){
							JPanel pnlAdmD= new pnlAdminProveedores(principal);
							pnlAdmD.setBounds(0, 0, 1000, 600);
							principal.remove(this);
							principal.add(pnlAdmD);
							principal.repaint();
						}
					}
				}
			}
		}
	}
	
	public void paint(Graphics g){
		fondo=new ImageIcon(getClass().getResource("/vistas/admin.jpg"));
		g.drawImage(fondo.getImage(),0, 0,1000,600,null);
		setOpaque(false);
		super.paint(g);
	}
}

