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
import javax.swing.JComboBox;
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
import businessDelegate.ProveedorDelegate;
import dto.ProductoDTO;
import dto.ProveedorDTO;
import excepciones.GenericRemoteException;

public class pnlAdminProductos extends JPanel implements ActionListener {

	private static final long serialVersionUID = 8124324534423655238L;

	ImageIcon fondo;
	JFrame principal;
	JTable tblProductos;
	JComboBox<String> combo;
	JButton btnBaja, btnAlta, btnModificar, btnAtras, btnBuscar, btnVerFotoAnterior;
	//TODO
	JLabel lblLimpiar, lblCodBarras, lblDescripcion, lblPrecioV, lblCantFCompra, lblCantMinimaStock,
			lblStockActual, lblIdProducto, lblIdProductoSelec, lblFotoUrl, lblProveedor, lblIdProductoDelProveedor, 
			lblStockEnEspera, lblStockEnEsperaRta;
	JTextField txtCodBarras, txtDescripcion, txtPrecioV, txtCantFCompra, txtCantMinimaStock,
			txtStockActual, txtFotoUrl, txtIdProductoDelProveedor;
	DefaultTableModel model, modeloBusqueda;//Model es la tabla y si tocas te carga automaticamente a los textbox
											//modeloBusqueda ayuda al boton limpiar 
	List<ProductoDTO> productos;
	List<ProveedorDTO> proveedores;
	ProductoDTO prodTabla, prodAlta, prodBaja, prodModif;
	String[][] datos, datos2, csReturn;		//datos es datos txt, datos2 es para boton limpiar
	String[] columnas = { "# Producto", "Cod. de Barras", "Descripci�n", "Precio venta" };
	static DateTimeFormatter formatF = DateTimeFormatter.ofPattern("yyyy-MM-dd");	//Esta de adorno
	int cantC, i = 0, idP, row = -1, idItem;
	JScrollPane scrollBar;

	public pnlAdminProductos(JFrame frm) {
		principal = frm;
		principal.setTitle("Almac�n - Productos");
		Inicializar();
		AsignarEventos();
		this.setVisible(true);
	}

	private void Inicializar() {

		this.setLayout(null);
		this.setSize(1000, 600);
		
		productos = new ArrayList<ProductoDTO>();
		
		try {
			productos = new ArrayList<ProductoDTO>();
			productos = ProductoDelegate.getInstancia().findAllProductos();
			proveedores = new ArrayList<ProveedorDTO>();
			proveedores = ProveedorDelegate.getInstancia().findAllProveedores();
			
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		/*System.out.println("FOORRRRR");
		for (ProductoDTO item : productos) {
			System.out.println("Id: " + item.getIdProducto());
			System.out.println("Descripcion: " + item.getDescripcion());
			System.out.println();
		}*/
		
		datos = new String[productos.size()][11];
		
		for (ProductoDTO productoList : productos) {
			datos[i][0] = String.valueOf(productoList.getIdProducto());
			datos[i][1] = productoList.getCodigoBarras();
			datos[i][2] = productoList.getDescripcion();
			datos[i][3] = String.valueOf(productoList.getPrecioVenta());
			datos[i][4] = String.valueOf(productoList.getCantFijaCompra());
			datos[i][5] = String.valueOf(productoList.getCantMinimaStock());
			datos[i][6] = String.valueOf(productoList.getStockActual());
			datos[i][7] = String.valueOf(productoList.getFotoUrl());
			datos[i][8] = String.valueOf(productoList.getProveedor().getNombre());
			datos[i][9] = String.valueOf(productoList.getIdProductoDelProveedor());
			try {
				datos[i][10] = String.valueOf(ComprasDelegate.getInstancia()
						.buscarOrdenesActivasByProductoCantidad(productoList.getCodigoBarras()));
			} catch (GenericRemoteException e1) {
				e1.printStackTrace();
			}

			i++;
		}
		combo =new JComboBox<String>();
		for (ProveedorDTO item : proveedores) {
			combo.addItem(item.getNombre());
		}
		

		model = new DefaultTableModel(datos, columnas); //Columnas es nombre columnas
		
		tblProductos = new JTable(model);
		scrollBar = new JScrollPane();
		scrollBar.setViewportView(tblProductos);
		scrollBar.setBounds(35, 75, 425, 420);

		tblProductos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = tblProductos.getSelectedRow();
				idP = Integer.parseInt(datos[row][0]);
				
				
				lblIdProductoSelec.setText(datos[row][0]);
				txtCodBarras.setText(datos[row][1]);
				txtDescripcion.setText(datos[row][2]);
				txtPrecioV.setText(datos[row][3]);
				txtCantFCompra.setText(datos[row][4]);
				txtCantMinimaStock.setText(datos[row][5]);
				txtStockActual.setText(datos[row][6]);
				txtFotoUrl.setText(datos[row][7]);
				combo.setSelectedItem(datos[row][8]);
				txtIdProductoDelProveedor.setText(datos[row][9]);
				lblStockEnEsperaRta.setText(datos[row][10]);
				
			}
		});
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setOpaque(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBounds(529, 31, 75, 30);
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
		btnVerFotoAnterior = new JButton("Ver Foto");
		btnVerFotoAnterior.setOpaque(false);
		btnVerFotoAnterior.setContentAreaFilled(false);
		
		
		
		
		lblLimpiar = new JLabel("Limpiar");
		lblCodBarras = new JLabel("C�digo de Barras: ");
		lblDescripcion = new JLabel("Descripci�n: ");
		lblPrecioV = new JLabel("Precio venta: ");
		lblCantFCompra = new JLabel("Cantidad fija compra: ");
		lblCantMinimaStock = new JLabel("Cantidad m�nima Stock: ");
		lblStockActual = new JLabel("Stock Actual: ");
		lblIdProducto = new JLabel("Id Producto: ");
		lblIdProductoSelec = new JLabel("");
		lblFotoUrl = new JLabel("Foto URL del producto");
		lblProveedor = new JLabel("Proveedor: ");
		lblIdProductoDelProveedor = new JLabel("Id del Prov: ");
		lblStockEnEspera = new JLabel("Cantidad en espera de recepci�n: ");
		
		
		txtCodBarras = new JTextField();
		txtCodBarras.setOpaque(false);
		txtDescripcion = new JTextField();
		txtDescripcion.setOpaque(false);
		txtPrecioV = new JTextField();
		txtPrecioV.setOpaque(false);
		txtCantFCompra = new JTextField();
		txtCantFCompra.setOpaque(false);
		txtCantMinimaStock = new JTextField();
		txtCantMinimaStock.setOpaque(false);
		txtStockActual = new JTextField();
		txtStockActual.setOpaque(false);
		txtFotoUrl = new JTextField();
		txtFotoUrl.setOpaque(false);
		txtIdProductoDelProveedor = new JTextField();
		txtIdProductoDelProveedor.setOpaque(false);
		lblStockEnEsperaRta = new JLabel("");
		
		
		lblLimpiar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = -1;
				txtCodBarras.setText(null);
				txtDescripcion.setText(null);
				txtPrecioV.setText(null);
				txtCantFCompra.setText(null);
				txtCantMinimaStock.setText(null);
				txtStockActual.setText(null);
				lblIdProductoSelec.setText("");
				txtFotoUrl.setText(null);
				txtIdProductoDelProveedor.setText(null);
				lblStockEnEsperaRta.setText("");
				
				datos2 = new String[productos.size()][11];
				int y = 0;
				
								
				for (ProductoDTO productoList : productos) {
					datos2[y][0] = String.valueOf(productoList.getIdProducto());
					datos2[y][1] = productoList.getCodigoBarras();
					datos2[y][2] = productoList.getDescripcion();
					datos2[y][3] = String.valueOf(productoList.getPrecioVenta());
					datos2[y][4] = String.valueOf(productoList.getCantFijaCompra());
					datos2[y][5] = String.valueOf(productoList.getCantMinimaStock());
					datos2[y][6] = String.valueOf(productoList.getStockActual());
					datos2[y][7] = String.valueOf(productoList.getFotoUrl());
					datos2[y][8] = String.valueOf(productoList.getProveedor().getNombre());
					datos2[i][10] = String.valueOf(null);

					y++;
				}
				datos = datos2;
				model.setDataVector(datos, columnas);
				tblProductos.setModel(model);
				principal.repaint();
			}
		});
		
		
		lblProveedor.setBounds(529, 70, 130, 30);
		lblCodBarras.setBounds(529, 105, 130, 30);
		lblIdProductoDelProveedor.setBounds(529, 140, 150, 30);
		lblDescripcion.setBounds(529, 175, 130, 30);
		lblPrecioV.setBounds(529, 210, 130, 30);
		lblCantFCompra.setBounds(529, 245, 130, 30);
		lblCantMinimaStock.setBounds(529, 280, 150, 30);
		lblStockActual.setBounds(529, 315, 130, 30);		
		lblFotoUrl.setBounds(529, 350, 150, 30);
		lblIdProducto.setBounds(529, 385, 130, 30);
		lblStockEnEspera.setBounds(529, 420, 210, 30);
		
		
		combo.setBounds(769, 70, 150, 30);
		txtCodBarras.setBounds(769, 105, 150, 30);
		txtIdProductoDelProveedor.setBounds(769, 140, 150, 30);
		txtDescripcion.setBounds(769, 175, 150, 30);
		txtPrecioV.setBounds(769, 210, 150, 30);
		txtCantFCompra.setBounds(769, 245, 150, 30);
		txtCantMinimaStock.setBounds(769, 280, 150, 30);
		txtStockActual.setBounds(769, 315, 150, 30);
		txtFotoUrl.setBounds(769, 350, 130, 30);
		btnVerFotoAnterior.setBounds(904, 350, 85, 30);
		lblIdProductoSelec.setBounds(769, 385, 130, 30);		
		lblStockEnEsperaRta.setBounds(769, 420, 130, 30);
		
		
		lblLimpiar.setBounds(816, 25, 80, 30);
		lblLimpiar.setForeground(Color.BLUE);
		
		this.add(btnAlta);
		this.add(btnBaja);
		this.add(btnAtras);
		this.add(btnModificar);
		this.add(btnBuscar);
		this.add(btnVerFotoAnterior);
		this.add(scrollBar);
		
		
		this.add(lblLimpiar);
		this.add(lblProveedor);
		this.add(combo);
		this.add(lblCodBarras);
		this.add(lblDescripcion);
		this.add(lblPrecioV);
		this.add(lblCantFCompra);
		this.add(lblCantMinimaStock);
		this.add(lblStockActual);
		this.add(lblIdProducto);
		this.add(lblIdProductoSelec);
		this.add(lblFotoUrl);
		this.add(lblIdProductoDelProveedor);
		this.add(lblStockEnEspera);
				
		this.add(txtCodBarras);
		this.add(txtDescripcion);
		this.add(txtPrecioV);
		this.add(txtCantFCompra);
		this.add(txtCantMinimaStock);
		this.add(txtStockActual);
		this.add(txtFotoUrl);
		this.add(txtIdProductoDelProveedor);
		this.add(lblStockEnEsperaRta);
	}
	
	private void AsignarEventos() {
		btnAtras.addActionListener(this);
		btnAlta.addActionListener(this);
		btnModificar.addActionListener(this);
		btnBaja.addActionListener(this);
		btnBuscar.addActionListener(this);
		btnVerFotoAnterior.addActionListener(this);
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
				
				if (!txtCodBarras.getText().equals("") && !txtDescripcion.getText().equals("")
						&& !txtPrecioV.getText().equals("") && !txtCantFCompra.getText().equals("")
						&& !txtCantMinimaStock.getText().equals("") && !txtStockActual.getText().equals("")) {
					
					if (isNumeric(txtCantFCompra.getText())) {
						if (isNumeric(txtCantMinimaStock.getText())) {
							if (isNumeric(txtStockActual.getText())) {
								prodAlta = new ProductoDTO();
								prodAlta.setIdProducto(null);
								prodAlta.setCodigoBarras(txtCodBarras.getText());
								prodAlta.setDescripcion(txtDescripcion.getText());
								prodAlta.setPrecioVenta(new BigDecimal(txtPrecioV.getText()));
								prodAlta.setCantFijaCompra(Integer.parseInt(txtCantFCompra.getText()));
								prodAlta.setCantMinimaStock(Integer.parseInt(txtCantMinimaStock.getText()));
								prodAlta.setStockActual(Integer.parseInt(txtStockActual.getText()));
								prodAlta.setEstadoActivo(true);
								prodAlta.setIdProductoDelProveedor(Integer.parseInt(txtIdProductoDelProveedor.getText()));
								try {
									prodAlta.setProveedor(ProveedorDelegate.getInstancia().buscarProveedorByNombre(combo.getSelectedItem().toString()));
								} catch (GenericRemoteException e) {
									e.printStackTrace();
								}
								prodAlta.setFotoUrl(txtFotoUrl.getText());
							} else {
								JOptionPane.showMessageDialog(null, "El stock actual ingresado no es valido");
							}
						} else {
							JOptionPane.showMessageDialog(null, "La cantidad m�nima stock ingresada no es valida");
						}
					} else {
						JOptionPane.showMessageDialog(null, "La cantidad Fija Compra ingresada no es valida");
					}
										
					
					if (!existeCodB(txtCodBarras.getText()) && isNumeric(txtCantFCompra.getText()) && isNumeric(txtCantMinimaStock.getText()) 
							&& isNumeric(txtStockActual.getText())) {
						try {
							ProductoDelegate.getInstancia().altaProducto(prodAlta);

							JPanel pnlAdmP = new pnlAdminProductos(principal);
							pnlAdmP.setBounds(0, 0, 1000, 600);
							principal.remove(this);
							principal.getContentPane().add(pnlAdmP);
							principal.repaint();

							JOptionPane.showMessageDialog(null, "Producto dado de alta con �xito");
						} catch (excepciones.GenericRemoteException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "No se pudo dar de alta el producto");
						}
					} else {
						JOptionPane.showMessageDialog(null, "El C�digo de Barras ingresado ya existe");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Faltan completar campos");
				}
			} else {
				
				if (click.getActionCommand().equals("Modificar")) {
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Seleccione un art�culo antes de modificar");
					} else {
						if (!txtCodBarras.getText().equals("") && !txtDescripcion.getText().equals("")
								&& !txtPrecioV.getText().equals("") && !txtCantFCompra.getText().equals("")
								&& !txtCantMinimaStock.getText().equals("") && !txtStockActual.getText().equals("")) {

							idP = Integer.parseInt(datos[row][0]);
							try {
								prodModif = ProductoDelegate.getInstancia().buscarProductoById(idP);
								
								
								prodModif.setCodigoBarras(txtCodBarras.getText());
								prodModif.setDescripcion(txtDescripcion.getText());
								prodModif.setPrecioVenta(new BigDecimal(txtPrecioV.getText()));
								prodModif.setCantFijaCompra(Integer.parseInt(txtCantFCompra.getText()));
								prodModif.setCantMinimaStock(Integer.parseInt(txtCantMinimaStock.getText()));
								prodModif.setStockActual(Integer.parseInt(txtStockActual.getText()));
								try {
									prodModif.setProveedor(ProveedorDelegate.getInstancia().buscarProveedorByNombre(combo.getSelectedItem().toString()));
								} catch (GenericRemoteException e) {
									e.printStackTrace();
								}
								prodModif.setFotoUrl(txtFotoUrl.getText());
								prodModif.setIdProductoDelProveedor(Integer.parseInt(txtIdProductoDelProveedor.getText()));

								ProductoDelegate.getInstancia().modificarProducto(prodModif);

								JOptionPane.showMessageDialog(null, "Producto modificado con �xito");
								JPanel pnlAdmP = new pnlAdminProductos(principal);
								pnlAdmP.setBounds(0, 0, 1000, 600);
								principal.remove(this);
								principal.getContentPane().add(pnlAdmP);
								principal.repaint();

							} catch (excepciones.GenericRemoteException e) {
								JOptionPane.showMessageDialog(null, "No se pudo modificar el art�culo");
								e.printStackTrace();
							}
						}
					}
				} else {
					
					
					if (click.getActionCommand().equals("Baja")) {
						if (row >= 0) {
							idP = Integer.parseInt(datos[row][0]);

							try {

								ProductoDelegate.getInstancia().bajaProducto(idP);

								JOptionPane.showMessageDialog(null, "Producto dado de baja con �xito");
								
								JPanel pnlAdmP = new pnlAdminProductos(principal);
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
						}
					} else {
						if (click.getActionCommand().equals("Buscar")) {
							String resp = "";
							resp = JOptionPane.showInputDialog("Escriba el C�digo de Barras a buscar: ");
							if (resp != "") {
								String[][] resBusqueda = buscarProductos(datos, resp);
								if (resBusqueda != null) {
									modeloBusqueda = new DefaultTableModel(resBusqueda, columnas);
									tblProductos.setModel(modeloBusqueda);
									datos = resBusqueda;
									principal.repaint();
								} else {
									JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Debe ingresar un C�digo de Barras para poder buscar");
							}
						} else {
							if (click.getActionCommand().equals("Ver Foto")) {
								if(!lblIdProductoSelec.equals(null)) {
									
									if (row < 0) {
										JOptionPane.showMessageDialog(null, "Seleccione un art�culo antes de modificar");
									} else {
											idP = Integer.parseInt(datos[row][0]);
											try {
												ProductoDTO p = ProductoDelegate.getInstancia().buscarProductoById(idP);
												abrirFoto(p);
											} catch (GenericRemoteException e) {
												e.printStackTrace();
											}
									}
									
								}
							}
						}
						
						//
					}
				}
			}
		}
	}

	public void paint(Graphics g) {
		fondo = new ImageIcon(getClass().getResource("/vistas/adminProductos.jpg"));
		//fondo = new ImageIcon(getClass().getResource("/fotosProductos/" + producto.getid()+".jpg"));
		g.drawImage(fondo.getImage(), 0, 0, 994, 580, null);
		// g.drawImage(fondo.getImage(),0, 0,1000,600,null);
		setOpaque(false);
		super.paint(g);
	}

	public String[][] buscarProductos(String[][] datos, String nroCodBarras) {

		int filas = buscarCantP(datos, nroCodBarras);
		if (filas > 0) {
			csReturn = new String[filas][11];
			int tamD = datos.length;
			int k = 0;
			for (int o = 0; o < tamD; o++) {
				if ((datos[o][0]).trim().equals(nroCodBarras.trim())) {
					csReturn[k][0] = datos[o][0];
					csReturn[k][1] = datos[o][1];
					csReturn[k][2] = datos[o][2];
					csReturn[k][3] = datos[o][3];
					csReturn[k][4] = datos[o][4];
					csReturn[k][5] = datos[o][5];
					csReturn[k][6] = datos[o][6];
					csReturn[k][7] = datos[o][7];
					csReturn[k][8] = datos[o][8];
					csReturn[k][9] = datos[o][9];
					csReturn[k][10] = datos[o][10];

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
	
	public void abrirFoto(ProductoDTO produ) {
		frmFoto frmp= new frmFoto(produ);
		frmp.setVisible(true);
	}
}
