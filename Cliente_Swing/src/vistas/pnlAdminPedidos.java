package vistas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessDelegate.ExpedicionDelegate;
import dto.PedidoDTO;
import dto.PedidoItemDTO;
import excepciones.GenericRemoteException;
import java.awt.Color;

public class pnlAdminPedidos extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1862041798671383746L;
	
	ImageIcon fondo;
	JFrame principal;
	JButton btnAtras,btnDespachar, btnCancelar;
	JLabel lblIdPedido, lblID,lblnombreC,lblPedidoItems,lblN, lblFechaC,lblF,lblMontoP,lblM, lblDireccion, lblD, lblNombreC, lblNcl, lblAclaracion;
	JTextField txtAclaracion;
	JTable tblPedidos;
	List<PedidoDTO> pedidos = new ArrayList<PedidoDTO>();
	String[][] datos;
	String[] columnas = {"# Pedido", "Tipo","Fecha Creación", "Monto Total"};
	int row=-1, i=0,idP, idC;
	DefaultTableModel model;
	JScrollPane scrollBar;
	frmPedidoItems frmPedi;
	PedidoDTO pediAux;
	
	public pnlAdminPedidos(JFrame frm){
		principal= frm;
		principal.setTitle("Administración - Pedidos");
		Inicializar();
		asignarEventos();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		this.setLayout(null);
		this.setSize(1000,600);
				
		try {
			pedidos= ExpedicionDelegate.getInstancia().buscarPedidosPendientesDespacho();
		} 
		catch (excepciones.GenericRemoteException e1) {
			e1.printStackTrace();
		}
			
		datos= new String[pedidos.size()][6];
		
		for(PedidoDTO pedidoList : pedidos) {
			datos[i][0]=String.valueOf(pedidoList.getIdPedido());
			datos[i][1]=pedidoList.getEstadoPedido();
			datos[i][2]=String.valueOf(LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(pedidoList.getFecha()) ));
			datos[i][3]=String.valueOf(getTotalPedido(pedidoList));
			datos[i][4]=String.valueOf(pedidoList.get);
			//datos[i][5]=String.valueOf(pedidoList.getNombreCliente);

			i++;
		}

		model= new DefaultTableModel(datos,columnas);
		tblPedidos= new JTable(model);		
		scrollBar = new JScrollPane();
		scrollBar.setViewportView(tblPedidos);
		scrollBar.setBounds(35, 75, 425, 420);
		
		tblPedidos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = tblPedidos.getSelectedRow();
				idP=Integer.parseInt(datos[row][0]);
				
				lblID.setText(datos[row][0]);
				lblN.setText(datos[row][1]);
				lblF.setText(datos[row][2]);
				lblM.setText(datos[row][3]);
				if(datos[row][4] == null)
					lblD.setText("RETIRO_EN_PERSONA");
				else
					lblD.setText(datos[row][4]);
				lblNcl.setText(datos[row][5]);
			}
		});
		lblIdPedido= new JLabel("# Pedido: ");
		lblIdPedido.setBounds(527, 110, 150, 30);
		lblID= new JLabel("");
		lblID.setBounds(731, 110, 150, 30);
		lblnombreC= new JLabel("Tipo:");
		lblnombreC.setBounds(527, 150, 150, 30);
		lblN= new JLabel("");
		lblN.setBounds(731, 150, 190, 30);
		lblFechaC= new JLabel("Fecha Pedido: ");
		lblFechaC.setBounds(527, 190, 150, 30);
		lblF= new JLabel("");
		lblF.setBounds(731, 190, 150, 30);
		lblMontoP= new JLabel("Monto Pedido: ");
		lblMontoP.setBounds(527, 230, 150, 30);
		lblM= new JLabel("");
		lblM.setBounds(731, 230, 150, 30);
		lblDireccion= new JLabel("Dirección envío coordinado: ");
		lblDireccion.setBounds(527, 310, 170, 30);
		lblD= new JLabel("");
		lblD.setBounds(731, 310, 200, 30);
		//Quizas en otro release
		lblNombreC= new JLabel("Nombre Cliente");
		lblNombreC.setBounds(527, 270, 150, 30);
		lblNcl= new JLabel("");
		lblNcl.setBounds(731, 270, 150, 30);
		lblAclaracion= new JLabel("Aclaración: ");
		lblAclaracion.setBounds(527, 350, 150, 30);
		
		txtAclaracion= new JTextField();
		txtAclaracion.setBounds(706, 350, 217, 71);
		
		btnDespachar= new JButton("Despachar");
		btnDespachar.setBounds(753, 446, 150, 30);
		btnCancelar= new JButton("Cancelar");
		btnCancelar.setBounds(552, 446, 150, 30);
		btnAtras= new JButton("Atras");
		btnAtras.setBounds(20, 25, 80, 30);
		
		this.add(btnDespachar);
		this.add(btnCancelar);
		this.add(lblIdPedido);
		this.add(lblID);
		this.add(lblnombreC);
		this.add(lblN);
		this.add(lblFechaC);
		this.add(lblF);
		this.add(lblMontoP);
		this.add(lblM);
		this.add(lblDireccion);
		this.add(lblD);
		//this.add(lblNombreC);
		//this.add(lblNcl);
		//this.add(lblAclaracion);
		//this.add(txtAclaracion);
		this.add(btnAtras);
		this.add(scrollBar);
		
		lblPedidoItems = new JLabel("Ver Productos del Pedido");
		lblPedidoItems.setForeground(Color.BLUE);
		lblPedidoItems.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (row >= 0) {
					pediAux = buscarItem(Integer.parseInt(datos[row][0]));
					frmPedi = new frmPedidoItems(pediAux);
					frmPedi.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un pedido para poder ver los productos del mismo");
				}
			}
		});
		lblPedidoItems.setBounds(740, 68, 150, 30);
		add(lblPedidoItems);
		
	}
	private void asignarEventos(){
		btnAtras.addActionListener(this);
		btnDespachar.addActionListener(this);
		btnCancelar.addActionListener(this);
	}
	public void actionPerformed(ActionEvent click){
		if(click.getActionCommand().equals("Atras")){
			JPanel pnlA= new pnlAdministracion(principal);
			pnlA.setBounds(0, 0, 1000, 600);
			principal.remove(this);
			principal.getContentPane().add(pnlA);
			principal.repaint();
		}
		else {
			if(click.getActionCommand().equals("Despachar")){
				if (row >= 0) {
					try {
						ExpedicionDelegate.getInstancia().despachar(idP);
						JOptionPane.showMessageDialog(null, "Pedido despachado con éxito");
						lblID.setText("");
						lblN.setText("");
						lblF.setText("");
						lblM.setText("");
						lblD.setText("");
						lblNcl.setText("");
						
						
						JPanel pnlAdmP = new pnlAdminPedidos(principal);
						pnlAdmP.setBounds(0, 0, 1000, 600);
						principal.remove(this);
						principal.getContentPane().add(pnlAdmP);
						principal.repaint();

					} catch (GenericRemoteException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "No se pudo despachar el pedido");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un pedido antes de despachar");
				}
			}
			else {
				if(click.getActionCommand().equals("Cancelar")){
					if (row >= 0) {
						try {
							ExpedicionDelegate.getInstancia().cancelar(idP);
							JOptionPane.showMessageDialog(null, "Pedido cancelado con éxito");

							JPanel pnlAdmP = new pnlAdminPedidos(principal);
							pnlAdmP.setBounds(0, 0, 1000, 600);
							principal.remove(this);
							principal.getContentPane().add(pnlAdmP);
							principal.repaint();
						} catch (GenericRemoteException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "No se pudo cancelar el pedido");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un pedido antes de cancelar");
					}
				}
			}
		}
	}
	
	public void paint(Graphics g){
		fondo=new ImageIcon(getClass().getResource("/vistas/adminExpedicion.jpg"));
		g.drawImage(fondo.getImage(),0, 0,994,580,null);
		//g.drawImage(fondo.getImage(),0, 0,1000,600,null);
		setOpaque(false);
		super.paint(g);
	}
	
	public BigDecimal getTotalPedido(PedidoDTO pedido) {
		BigDecimal total = BigDecimal.ZERO;
		for(PedidoItemDTO item : pedido.getItems()) {
			total = total.add(new BigDecimal(item.getCantidad()).multiply(item.getProducto().getPrecioVenta()));
		}
		return total;
	}
	
	public PedidoDTO buscarItem(int id) {
		
		PedidoDTO aux= null;;
		for(PedidoDTO item: pedidos) {
			if(item.getIdPedido()==id) {
				aux=item;
			}
		}
		return aux;
	}
}