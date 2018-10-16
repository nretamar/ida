package vistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
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

import businessDelegate.ComprasDelegate;
import businessDelegate.ProductoDelegate;
import dto.ProductoDTO;
import dto.RecibidoDTO;
import dto.RemitoDTO;
import dto.RemitoItemDTO;
import excepciones.GenericRemoteException;

public class pnlAdminCompras extends JPanel implements ActionListener {

	private static final long serialVersionUID = 8124324534423655238L;

	ImageIcon fondo;
	JFrame principal;
	JTable tblRecibidos;
	JButton btnBaja, btnAlta, btnModificar, btnAtras, btnBuscar;
	//TODO
	JLabel lblLimpiar, lblIdProducto, lblIdProductoBusqueda, lblCodBarras, lblCodBarrasBusqueda, lblDescripcion,
		lblDescripcionBusqueda, lblCantidad;
	JTextField txtCantidad;
	DefaultTableModel model, modeloBusqueda;//Model es la tabla y si tocas te carga automaticamente a los textbox
											//modeloBusqueda ayuda al boton limpiar 
	List<RecibidoDTO> productosRecibidos;
	RecibidoDTO recibidoTabla, recibidoAlta, recibidoBaja, recibidoModif;
	String[][] datos, datos2, csReturn;		//datos es datos txt, datos2 es para boton limpiar
	String[] columnas = { "# Producto", "Cod. de Barras", "Descripción", "Cantidad" };
	static DateTimeFormatter formatF = DateTimeFormatter.ofPattern("yyyy-MM-dd");	//Esta de adorno
	int cantC, i = 0, idP, row = -1;
	JScrollPane scrollBar;

	public pnlAdminCompras(JFrame frm) {
		principal = frm;
		principal.setTitle("Almacén - Compras");
		Inicializar();
		AsignarEventos();
		this.setVisible(true);
	}

	private void Inicializar() {

		this.setLayout(null);
		this.setSize(1000, 600);
		
		productosRecibidos = new ArrayList<RecibidoDTO>();	
				
		datos = new String[productosRecibidos.size()][7];
		
		for (RecibidoDTO recibidoList : productosRecibidos) {
			datos[i][0] = String.valueOf(recibidoList.getIdProducto());
			datos[i][1] = recibidoList.getCodigoBarras();
			datos[i][2] = recibidoList.getDescripcion();
			datos[i][3] = String.valueOf(recibidoList.getCantidad());

			i++;
		}

		model = new DefaultTableModel(datos, columnas); //Columnas es nombre columnas
		tblRecibidos = new JTable(model);
		scrollBar = new JScrollPane();
		scrollBar.setViewportView(tblRecibidos);
		scrollBar.setBounds(35, 75, 425, 420);

		tblRecibidos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = tblRecibidos.getSelectedRow();
				idP = Integer.parseInt(datos[row][0]);
				
				lblIdProductoBusqueda.setText(datos[row][0]);				
				lblCodBarrasBusqueda.setText(datos[row][1]);
				lblDescripcionBusqueda.setText(datos[row][2]);
				txtCantidad.setText(datos[row][3]);
			}
		});
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setOpaque(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBounds(529, 51, 75, 30);
		btnBuscar.setForeground(Color.LIGHT_GRAY);
		btnAlta = new JButton("Alta");
		btnAlta.setOpaque(false);
		btnAlta.setContentAreaFilled(false);
		btnAlta.setBounds(498, 461, 131, 30);
		btnBaja = new JButton("Baja");
		btnBaja.setOpaque(false);
		btnBaja.setContentAreaFilled(false);
		btnBaja.setBounds(666, 461, 131, 30);
		btnModificar = new JButton("Modificar");
		btnModificar.setOpaque(false);
		btnModificar.setContentAreaFilled(false);
		btnModificar.setBounds(829, 461, 131, 30);
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(20, 25, 80, 30);
		
		
		
		lblLimpiar = new JLabel("Limpiar");
		lblIdProducto = new JLabel("IdProducto: ");
		lblCodBarras = new JLabel("Código de Barras: ");
		lblDescripcion = new JLabel("Descripción: ");
		lblCantidad = new JLabel("Cantidad: ");
		
		lblIdProductoBusqueda = new JLabel("");
		lblCodBarrasBusqueda = new JLabel("");
		lblDescripcionBusqueda = new JLabel("");
		txtCantidad = new JTextField("");
		
		
		txtCantidad = new JTextField();
		txtCantidad.setOpaque(false);
		
		
		lblLimpiar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = -1;
				lblIdProductoBusqueda.setText("");
				lblCodBarrasBusqueda.setText("");
				lblDescripcionBusqueda.setText("");
				txtCantidad.setText(null);
				
				datos2 = new String[productosRecibidos.size()][4];
				int y = 0;
				
								
				for (RecibidoDTO recibidoList : productosRecibidos) {
					datos2[y][0] = String.valueOf(recibidoList.getIdProducto());
					datos2[y][1] = recibidoList.getCodigoBarras();
					datos2[y][2] = recibidoList.getDescripcion();
					datos2[y][3] = String.valueOf(recibidoList.getCantidad());

					y++;
				}
				datos = datos2;
				model.setDataVector(datos, columnas);
				tblRecibidos.setModel(model);
				principal.repaint();
			}
		});
		
		
		lblIdProducto.setBounds(529, 105, 130, 30);
		lblCodBarras.setBounds(529, 145, 130, 30);
		lblDescripcion.setBounds(529, 185, 130, 30);
		lblCantidad.setBounds(529, 225, 130, 30);
		//lblCantMinimaStock.setBounds(529, 265, 150, 30);
		//lblStockActual.setBounds(529, 305, 130, 30);
		//lblIdProducto.setBounds(529, 345, 130, 30);
		
		
		lblIdProductoBusqueda.setBounds(769, 105, 150, 30);
		lblCodBarrasBusqueda.setBounds(769, 145, 150, 30);
		lblDescripcionBusqueda.setBounds(769, 185, 150, 30);
		txtCantidad.setBounds(769, 225, 150, 30);
		//txtCantMinimaStock.setBounds(769, 265, 150, 30);
		//txtStockActual.setBounds(769, 305, 150, 30);
		//lblIdProductoSelec.setBounds(769, 345, 130, 30);
		
		lblLimpiar.setBounds(816, 51, 80, 30);
		lblLimpiar.setForeground(Color.BLUE);
		
		this.add(btnAlta);
		this.add(btnBaja);
		this.add(btnAtras);
		this.add(btnModificar);
		this.add(btnBuscar);
		this.add(scrollBar);
		
		
		this.add(lblLimpiar);
		this.add(lblIdProducto);
		this.add(lblCodBarras);
		this.add(lblDescripcion);
		this.add(lblCantidad);
		/*this.add(lblCantMinimaStock);
		this.add(lblStockActual);
		this.add(lblIdProducto);
		this.add(lblIdProductoSelec);*/
				
		this.add(lblIdProductoBusqueda);
		this.add(lblCodBarrasBusqueda);
		this.add(lblDescripcionBusqueda);
		this.add(txtCantidad);
		/*this.add(txtCantMinimaStock);
		this.add(txtStockActual);*/
	}
	
	//TODO Poner N a AsignarEvetos
	private void AsignarEventos() {
		btnAtras.addActionListener(this);
		btnAlta.addActionListener(this);
		btnModificar.addActionListener(this);
		btnBaja.addActionListener(this);
		btnBuscar.addActionListener(this);
	}

	public void actionPerformed(ActionEvent click) {
		if (click.getActionCommand().equals("Atras")) {
			JPanel pnlP = new pnlAdministracion(principal);
			pnlP.setBounds(0, 0, 1000, 600);
			principal.remove(this);
			principal.getContentPane().add(pnlP);
			principal.repaint();
		} else {
			if (click.getActionCommand().equals("Alta")) {
				
				if (!lblCodBarrasBusqueda.getText().equals("") && !lblDescripcionBusqueda.getText().equals("")) {
					
					if (isNumeric(txtCantidad.getText())) {
						
						
						try {
							//TODO esto es temporal
							ProductoDTO p = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras(lblCodBarrasBusqueda.getText());
							
							List<RemitoItemDTO> items = new ArrayList<RemitoItemDTO>();
							RemitoItemDTO item = new RemitoItemDTO();
							item.setIdRemitoItem(null);
							item.setProducto(p);
							item.setCantidad(Integer.parseInt(txtCantidad.getText()));
							items.add(item);
							
							RemitoDTO remito = ComprasDelegate.getInstancia().recepcionarCompra(items);
							
							if(remito != null)
							{
								for(RemitoItemDTO lista : remito.getProductosRecibidos()) {
									JOptionPane.showMessageDialog(null,
											"Debera recibir " + String.valueOf(lista.getCantidad()) + 
											" unidades de (codigo de barras): " + lista.getProducto().getCodigoBarras());
								}
								if(remito.getProductosRecibidos().isEmpty()) {
									JOptionPane.showMessageDialog(null, "No existen ordenes activas sobre el producto, por lo tanto no recibir nada");
								}
							} else {
								JOptionPane.showMessageDialog(null, "No recibir el producto");
							}
								
							
						} catch (GenericRemoteException e) {
							e.printStackTrace();
						}
						
						/*recibidoAlta = new RecibidoDTO();
						recibidoAlta.setIdProducto(null);
						recibidoAlta.setCodigoBarras(lblCodBarras.getText());
						recibidoAlta.setDescripcion(lblDescripcion.getText());
						recibidoAlta.setCantidad(Integer.parseInt(txtCantidad.getText()));*/
						//TODO agregar a tabla
					
					} else {
						JOptionPane.showMessageDialog(null, "La cantidad ingresada no es valida");
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Debe buscar un producto anteriormente");
				}
			} else {
				
				if (click.getActionCommand().equals("Modificar")) {
					/*if (row < 0) {
						JOptionPane.showMessageDialog(null, "Seleccione un artículo antes de modificar");
					} else {
						if (!lblIdProductoBusqueda.getText().equals("") && !lblDescripcionBusqueda.getText().equals("")
								&& !lblCodBarrasBusqueda.getText().equals("") && !txtCantidad.getText().equals("")) {

							idP = Integer.parseInt(datos[row][0]);
							for (i=0; recibidoList : productosRecibidos) {
								if(datos[i][0].equals(lblIdProductoBusqueda.getText()))	{
									datos[i][3] = String.valueOf(recibidoList.getCantidad());
								}
								
								i++;
							}
							
							recibidoAlta = new RecibidoDTO();
							recibidoAlta.setIdProducto(null);
							recibidoAlta.setCodigoBarras(lblCodBarras.getText());
							recibidoAlta.setDescripcion(lblDescripcion.getText());
							recibidoAlta.setCantidad(Integer.parseInt(txtCantidad.getText()));
						}
					}*/
				} else {
					
					
					if (click.getActionCommand().equals("Baja")) {
						/*if (row >= 0) {
							idP = Integer.parseInt(datos[row][0]);

							try {

								ProductoDelegate.getInstancia().bajaProducto(idP);

								JOptionPane.showMessageDialog(null, "Producto dado de baja con éxito");
								
								JPanel pnlAdmP = new pnlAdminCompras(principal);
								pnlAdmP.setBounds(0, 0, 1000, 600);
								principal.remove(this);
								principal.getContentPane().add(pnlAdmP);
								principal.repaint();

							} catch (excepciones.GenericRemoteException e) {
								JOptionPane.showMessageDialog(null, "No se pudo dar de baja el producto");
								e.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para dar de baja");
						}*/
					} else {
						if (click.getActionCommand().equals("Buscar")) {
							String resp = "";
							resp = JOptionPane.showInputDialog("Escriba el Código de Barras a buscar: ");
							if (resp != "") {
								ProductoDTO p = new ProductoDTO();
								p.setIdProducto(null);
								try {
									p = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras(resp);
									if (p.getIdProducto() != null) {
										lblIdProductoBusqueda.setText(String.valueOf(p.getIdProducto()));
										lblDescripcionBusqueda.setText(p.getDescripcion());
										lblCodBarrasBusqueda.setText(p.getCodigoBarras());
										
										
									} else {
										JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
									}
								} catch (GenericRemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							} else {
								JOptionPane.showMessageDialog(null,
										"Debe ingresar un Código de Barras para poder buscar");
							}
						}
					}
				}
			}
		}
	}

	public void paint(Graphics g) {
		fondo = new ImageIcon(getClass().getResource("/vistas/adminA.jpg"));
		//fondo = new ImageIcon(getClass().getResource("/fotosProductos/" + producto.getid()+".jpg"));
		g.drawImage(fondo.getImage(), 0, 0, 994, 580, null);
		// g.drawImage(fondo.getImage(),0, 0,1000,600,null);
		setOpaque(false);
		super.paint(g);
	}

	public String[][] buscarProductos(String[][] datos, String nroCodBarras) {

		int filas = buscarCantP(datos, nroCodBarras);
		if (filas > 0) {
			csReturn = new String[filas][7];
			int tamD = datos.length;
			int k = 0;
			for (int o = 0; o < tamD; o++) {
				if ((datos[o][1]).trim().equals(nroCodBarras.trim())) {
					csReturn[k][0] = datos[o][0];
					csReturn[k][1] = datos[o][1];
					csReturn[k][2] = datos[o][2];
					csReturn[k][3] = datos[o][3];
					csReturn[k][4] = datos[o][4];
					csReturn[k][5] = datos[o][5];
					csReturn[k][6] = datos[o][6];

					k++;
				}
			}
		} else {
			csReturn = null;
		}
		return csReturn;
	}

	public int buscarCantP(String[][] datos, String nroCodBarras) {

		int cant = 0;
		int filas = datos.length;
		for (int o = 0; o < filas; o++) {
			if ((datos[o][1]).trim().equals(nroCodBarras.trim())) {
				cant++;
			}
		}
		return cant;
	}

	public boolean existeCodB(String e) {
		boolean existe = false;
		int q, tamD;
		tamD = datos.length;
		for (q = 0; q < tamD; q++) {
			if ((datos[q][1]).trim().equals(e.trim())) {
				existe = true;
			}
		}
		return existe;
	}
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  
	}
}
