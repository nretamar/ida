package vistas;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.PedidoDTO;
import dto.PedidoItemDTO;
import dto.ProductoDTO;

public class pnlPedidoItems extends JPanel{
	
	private static final long serialVersionUID = 7905900602253377276L;
	
	ImageIcon fondo;
	JFrame principal;
	PedidoDTO pedido;
	List<PedidoItemDTO> productosRecibidos;
	JTable tblPedidos;
	JScrollPane scrollBar;
	DefaultTableModel model;
	String[][] datos;
	PedidoItemDTO auxPnlFoto;
	String[] columnas = {"# Pedido","Fecha Pedido","# item","Codigo Barras", "Descripcion","Cantidad"};
	int i=0, row=-1, idItem;
	
	public pnlPedidoItems(JFrame frm, PedidoDTO pedido) {
				principal=frm;
		productosRecibidos = pedido.getItems();
		this.pedido = pedido;
		Inicializar();
		this.setVisible(true);
	}
	private void Inicializar(){
		this.setLayout(null);
		this.setSize(600, 450);
		
		datos= new String[productosRecibidos.size()][6];
		for(PedidoItemDTO item : productosRecibidos) {
			datos[i][0]=String.valueOf(pedido.getIdPedido());
			datos[i][1]=String.valueOf(LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(pedido.getFecha()) ));
			datos[i][2]=String.valueOf(item.getIdPedidoItem());
			datos[i][3]=item.getProducto().getCodigoBarras();
			datos[i][4]=item.getProducto().getDescripcion();
			datos[i][5]=String.valueOf(item.getCantidad());
			i++;
		}
		model= new DefaultTableModel(datos,columnas);
		tblPedidos= new JTable(model);
		tblPedidos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = tblPedidos.getSelectedRow();
				if (row >= 0) {
					idItem = Integer.parseInt(datos[row][2]);
					auxPnlFoto = buscarItem(idItem);
					abrirFoto(auxPnlFoto.getProducto());
				}
			}
		});
		scrollBar = new JScrollPane();
		scrollBar.setViewportView(tblPedidos);
		scrollBar.setBounds(20, 15, 550, 390);
		
		this.add(scrollBar);
	}
	
	public void paint(Graphics g){
	fondo=new ImageIcon(getClass().getResource("/vistas/main3.jpg"));
	g.drawImage(fondo.getImage(),0, 0,600, 450,null);
	setOpaque(false);
	super.paint(g);
	}
	
	public void abrirFoto(ProductoDTO produ) {
		frmFoto frmp= new frmFoto(produ);
		frmp.setVisible(true);
	}
	public PedidoItemDTO buscarItem(int id) {
		
		PedidoItemDTO aux= null;;
		for(PedidoItemDTO item: productosRecibidos) {
			if(item.getIdPedidoItem()==id) {
				aux=item;
			}
		}
		return aux;
	}
}
