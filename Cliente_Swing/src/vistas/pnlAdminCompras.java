package vistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	JFrame principal, frmRemito;
	JTable tblRecibidos;
	JButton btnQuitar, btnAgregar, btnAltaRecibo, btnAtras, btnBuscar;
	JLabel lblLimpiar, lblIdProducto, lblIdProductoBusqueda, lblCodBarras, lblCodBarrasBusqueda, lblDescripcion,
		lblDescripcionBusqueda, lblCantidad;
	JTextField txtCantidad;
	DefaultTableModel model, modeloBusqueda;//Model es la tabla y si tocas te carga automaticamente a los textbox
											//modeloBusqueda ayuda al boton limpiar 
	List<RecibidoDTO> productosRecibidos;
	RecibidoDTO recibidoTabla, recibidoAgregar, recibidoQuitar, recibidoModif;
	String[][] datos, datos2, csReturn;		//datos es datos txt, datos2 es para boton limpiar
	String[] columnas = { "# Producto", "Cod. de Barras", "Descripci�n", "Cantidad" };
	static DateTimeFormatter formatF = DateTimeFormatter.ofPattern("yyyy-MM-dd");	//Esta de adorno
	int cantC, i = 0, idP, row = -1;
	JScrollPane scrollBar;

	public pnlAdminCompras(JFrame frm) {
		principal = frm;
		principal.setTitle("Almac�n - Compras");
		Inicializar();
		AsignarEventos();
		this.setVisible(true);
	}

	private void Inicializar() {

		this.setLayout(null);
		this.setSize(1000, 600);
		
		productosRecibidos = new ArrayList<RecibidoDTO>();
				
		datos = new String[productosRecibidos.size()][5];
		
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
		btnAgregar = new JButton("Agregar");
		btnAgregar.setOpaque(false);
		btnAgregar.setContentAreaFilled(false);
		btnAgregar.setBounds(498, 461, 131, 30);
		btnQuitar = new JButton("Quitar");
		btnQuitar.setOpaque(false);
		btnQuitar.setContentAreaFilled(false);
		btnQuitar.setBounds(666, 461, 131, 30);
		btnAltaRecibo = new JButton("Alta Recepci�n");
		btnAltaRecibo.setOpaque(false);
		btnAltaRecibo.setContentAreaFilled(false);
		btnAltaRecibo.setBounds(829, 461, 131, 30);
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(20, 25, 80, 30);
		
		
		
		lblLimpiar = new JLabel("Limpiar");
		lblIdProducto = new JLabel("IdProducto: ");
		lblCodBarras = new JLabel("C�digo de Barras: ");
		lblDescripcion = new JLabel("Descripci�n: ");
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
		
		this.add(btnAgregar);
		this.add(btnQuitar);
		this.add(btnAtras);
		this.add(btnAltaRecibo);
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
	
	private void AsignarEventos() {
		btnAtras.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnAltaRecibo.addActionListener(this);
		btnQuitar.addActionListener(this);
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
			if (click.getActionCommand().equals("Agregar")) {
				
				if (!lblCodBarrasBusqueda.getText().equals("") && !lblDescripcionBusqueda.getText().equals("")) {
					
					if (isNumeric(txtCantidad.getText())) {
						
						
						try {
							ProductoDTO p = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras(lblCodBarrasBusqueda.getText());
							RecibidoDTO r = new RecibidoDTO();
							r.setIdProducto(p.getIdProducto());
							r.setCodigoBarras(p.getCodigoBarras());
							r.setDescripcion(p.getDescripcion());
							r.setCantidad(Integer.valueOf(txtCantidad.getText()));
							productosRecibidos.add(r);
							actualizarTabla();
							
							lblIdProductoBusqueda.setText("");
							lblCodBarrasBusqueda.setText("");
							lblDescripcionBusqueda.setText("");
							txtCantidad.setText("");
							
							
							
							
							List<RemitoItemDTO> items = new ArrayList<RemitoItemDTO>();
							RemitoItemDTO item = new RemitoItemDTO();
							item.setIdRemitoItem(null);
							item.setProducto(p);
							item.setCantidad(Integer.parseInt(txtCantidad.getText()));
							items.add(item);
															
							
						} catch (GenericRemoteException e) {
							e.printStackTrace();
						}
					
					} else {
						JOptionPane.showMessageDialog(null, "La cantidad ingresada no es valida");
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Debe buscar un producto anteriormente");
				}
			} else {
				
				if (click.getActionCommand().equals("Alta Recepci�n")) {
					
					if(!productosRecibidos.isEmpty()) {
						try {
							List<RemitoItemDTO> items = new ArrayList<RemitoItemDTO>();
							RemitoDTO remito = new RemitoDTO();
							//Transformo a RemitoDTO
							for(RecibidoDTO reci : productosRecibidos) {
								
								RemitoItemDTO item = new RemitoItemDTO();
								item.setIdRemitoItem(null);
								item.setProducto(ProductoDelegate.getInstancia()
										.buscarProductoByCodigoDeBarras(reci.getCodigoBarras()));
								item.setCantidad(reci.getCantidad());
								items.add(item);
							}
							
							remito = ComprasDelegate.getInstancia().recepcionarCompra(items);
														
							if(remito != null)
							{
								frmRemito = new frmRemitoItems(remito);
								frmRemito.setVisible(true);
								
								/*for(RemitoItemDTO lista : remito.getProductosRecibidos()) {
									JOptionPane.showMessageDialog(null,
											"Debera recibir " + String.valueOf(lista.getCantidad()) + 
											" unidades de (codigo de barras): " + lista.getProducto().getCodigoBarras());
								}*/
								if(remito.getProductosRecibidos().isEmpty()) {
									JOptionPane.showMessageDialog(null, "No existen ordenes activas sobre el producto, por lo tanto no recibir nada");
								}
							} else {
								JOptionPane.showMessageDialog(null, "No recibir el producto");
							}
							
						} catch (GenericRemoteException e) {
							e.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "No hay items recibidos cargados");
					}
					
					
					
					/*if (row < 0) {
						JOptionPane.showMessageDialog(null, "Seleccione un art�culo antes de Alta Recepci�n");
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
							
							recibidoAgregar = new RecibidoDTO();
							recibidoAgregar.setIdProducto(null);
							recibidoAgregar.setCodigoBarras(lblCodBarras.getText());
							recibidoAgregar.setDescripcion(lblDescripcion.getText());
							recibidoAgregar.setCantidad(Integer.parseInt(txtCantidad.getText()));
						}
					}*/
				} else {
					
					
					if (click.getActionCommand().equals("Quitar")) {
						if (row >= 0) {
							idP = Integer.parseInt(datos[row][0]);
							
							RecibidoDTO delete = null;
							
							i = 0;
							for(RecibidoDTO lista : productosRecibidos) {
								if(i == row && lista.getIdProducto() == idP) {
									delete = lista;
								}
								i++;
							}
							if(delete!=null) {
								productosRecibidos.remove(delete);
								JOptionPane.showMessageDialog(null, "Recibido dado de baja con �xito");
							}
							else {
								JOptionPane.showMessageDialog(null, "Recibido no dado de baja");
							}
							
							actualizarTabla();
							
						} else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para dar de baja");
						}
					} else {
						if (click.getActionCommand().equals("Buscar")) {
							String resp = "";
							resp = JOptionPane.showInputDialog("Escriba el C�digo de Barras a buscar: ");
							if (resp != "") {
								ProductoDTO p = new ProductoDTO();
								p.setIdProducto(null);
								try {
									p = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras(resp);
									if (p.getIdProducto() != null) {
										lblIdProductoBusqueda.setText(String.valueOf(p.getIdProducto()));
										lblDescripcionBusqueda.setText(p.getDescripcion());
										lblCodBarrasBusqueda.setText(p.getCodigoBarras());
										txtCantidad.setText("");
										
										
									} else {
										JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
									}
								} catch (GenericRemoteException e) {
									e.printStackTrace();
								}
								
							} else {
								JOptionPane.showMessageDialog(null,
										"Debe ingresar un C�digo de Barras para poder buscar");
							}
						}
					}
				}
			}
		}
	}

	public void paint(Graphics g) {
		fondo = new ImageIcon(getClass().getResource("/vistas/adminCompras.jpg"));
		//fondo = new ImageIcon(getClass().getResource("/fotosProductos/" + producto.getid()+".jpg"));
		g.drawImage(fondo.getImage(), 0, 0, 994, 580, null);
		// g.drawImage(fondo.getImage(),0, 0,1000,600,null);
		setOpaque(false);
		super.paint(g);
	}

	public String[][] buscarProductos(String[][] datos, String nroCodBarras) {

		int filas = buscarCantP(datos, nroCodBarras);
		if (filas > 0) {
			csReturn = new String[filas][5];
			int tamD = datos.length;
			int k = 0;
			for (int o = 0; o < tamD; o++) {
				if ((datos[o][0]).trim().equals(nroCodBarras.trim())) {
					csReturn[k][0] = datos[o][0];
					csReturn[k][1] = datos[o][1];
					csReturn[k][2] = datos[o][2];
					csReturn[k][3] = datos[o][3];

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
			if ((datos[o][0]).trim().equals(nroCodBarras.trim())) {
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
	
	public void actualizarTabla() {
		datos = new String[productosRecibidos.size()][7];
		i=0;
		for (RecibidoDTO recibidoList : productosRecibidos) {
			datos[i][0] = String.valueOf(recibidoList.getIdProducto());
			datos[i][1] = recibidoList.getCodigoBarras();
			datos[i][2] = recibidoList.getDescripcion();
			datos[i][3] = String.valueOf(recibidoList.getCantidad());

			i++;
		}

		model.setDataVector(datos, columnas);
		tblRecibidos.setModel(model);
		principal.repaint();
	}
}
