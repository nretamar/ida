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

import businessDelegate.ProductoDelegate;
import dto.ProductoDTO;
import excepciones.GenericRemoteException;

public class pnlAdminProductos extends JPanel implements ActionListener {

	private static final long serialVersionUID = 8124324534423655238L;

	ImageIcon fondo;
	JFrame principal;
	JTable tblProductos;
	JButton btnBaja, btnAlta, btnModificar, btnAtras, btnBuscar, btnVerFotoAnterior;
	//TODO
	JLabel lblLimpiar, lblCodBarras, lblDescripcion, lblPrecioV, lblCantFCompra, lblCantMinimaStock,
			lblStockActual, lblIdProducto, lblIdProductoSelec, lblFotoUrl;
	JTextField txtCodBarras, txtDescripcion, txtPrecioV, txtCantFCompra, txtCantMinimaStock,
			txtStockActual, txtFotoUrl;
	DefaultTableModel model, modeloBusqueda;//Model es la tabla y si tocas te carga automaticamente a los textbox
											//modeloBusqueda ayuda al boton limpiar 
	List<ProductoDTO> productos;
	ProductoDTO prodTabla, prodAlta, prodBaja, prodModif;
	String[][] datos, datos2, csReturn;		//datos es datos txt, datos2 es para boton limpiar
	String[] columnas = { "# Producto", "Cod. de Barras", "Descripción", "Precio venta" };
	static DateTimeFormatter formatF = DateTimeFormatter.ofPattern("yyyy-MM-dd");	//Esta de adorno
	int cantC, i = 0, idP, row = -1, idItem;
	JScrollPane scrollBar;

	public pnlAdminProductos(JFrame frm) {
		principal = frm;
		principal.setTitle("Almacén - Productos");
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
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		/*System.out.println("FOORRRRR");
		for (ProductoDTO item : productos) {
			System.out.println("Id: " + item.getIdProducto());
			System.out.println("Descripcion: " + item.getDescripcion());
			System.out.println();
		}*/
		
		datos = new String[productos.size()][8];
		
		for (ProductoDTO productoList : productos) {
			datos[i][0] = String.valueOf(productoList.getIdProducto());
			datos[i][1] = productoList.getCodigoBarras();
			datos[i][2] = productoList.getDescripcion();
			datos[i][3] = String.valueOf(productoList.getPrecioVenta());
			datos[i][4] = String.valueOf(productoList.getCantFijaCompra());
			datos[i][5] = String.valueOf(productoList.getCantMinimaStock());
			datos[i][6] = String.valueOf(productoList.getStockActual());
			datos[i][7] = String.valueOf(productoList.getFotoUrl());

			i++;
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
				
				
				lblIdProductoSelec.setText("");
				txtCodBarras.setText(datos[row][1]);
				txtDescripcion.setText(datos[row][2]);
				txtPrecioV.setText(datos[row][3]);
				txtCantFCompra.setText(datos[row][4]);
				txtCantMinimaStock.setText(datos[row][5]);
				txtStockActual.setText(datos[row][6]);
				txtFotoUrl.setText(datos[row][7]);
				
				
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
		btnVerFotoAnterior = new JButton("Ver Foto anterior");
		btnVerFotoAnterior.setOpaque(false);
		btnVerFotoAnterior.setContentAreaFilled(false);
		btnVerFotoAnterior.setBounds(769, 425, 130, 30);
		
		
		
		lblLimpiar = new JLabel("Limpiar");
		lblCodBarras = new JLabel("Código de Barras: ");
		lblDescripcion = new JLabel("Descripción: ");
		lblPrecioV = new JLabel("Precio venta: ");
		lblCantFCompra = new JLabel("Cantidad fija compra: ");
		lblCantMinimaStock = new JLabel("Cantidad mínima Stock: ");
		lblStockActual = new JLabel("Stock Actual: ");
		lblIdProducto = new JLabel("Id Producto: ");
		lblIdProductoSelec = new JLabel("");
		lblFotoUrl = new JLabel("Foto URL del producto");
		
		
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
				
				datos2 = new String[productos.size()][9];
				int y = 0;
				
								
				for (ProductoDTO productoList : productos) {
					datos2[y][0] = String.valueOf(productoList.getIdProducto());
					datos2[y][1] = productoList.getCodigoBarras();
					datos2[y][2] = productoList.getDescripcion();
					datos2[y][3] = String.valueOf(productoList.getPrecioVenta());
					datos2[y][4] = String.valueOf(productoList.getCantFijaCompra());
					datos2[y][5] = String.valueOf(productoList.getCantMinimaStock());
					datos2[y][6] = String.valueOf(productoList.getStockActual());

					y++;
				}
				datos = datos2;
				model.setDataVector(datos, columnas);
				tblProductos.setModel(model);
				principal.repaint();
			}
		});
		
		
		lblCodBarras.setBounds(529, 105, 130, 30);
		lblDescripcion.setBounds(529, 145, 130, 30);
		lblPrecioV.setBounds(529, 185, 130, 30);
		lblCantFCompra.setBounds(529, 225, 130, 30);
		lblCantMinimaStock.setBounds(529, 265, 150, 30);
		lblStockActual.setBounds(529, 305, 130, 30);
		lblIdProducto.setBounds(529, 345, 130, 30);
		lblFotoUrl.setBounds(529, 385, 150, 30);
		
		
		txtCodBarras.setBounds(769, 105, 150, 30);
		txtDescripcion.setBounds(769, 145, 150, 30);
		txtPrecioV.setBounds(769, 185, 150, 30);
		txtCantFCompra.setBounds(769, 225, 150, 30);
		txtCantMinimaStock.setBounds(769, 265, 150, 30);
		txtStockActual.setBounds(769, 305, 150, 30);
		lblIdProductoSelec.setBounds(769, 345, 130, 30);
		txtFotoUrl.setBounds(769, 385, 130, 30);
		
		lblLimpiar.setBounds(816, 51, 80, 30);
		lblLimpiar.setForeground(Color.BLUE);
		
		this.add(btnAlta);
		this.add(btnBaja);
		this.add(btnAtras);
		this.add(btnModificar);
		this.add(btnBuscar);
		this.add(btnVerFotoAnterior);
		this.add(scrollBar);
		
		
		this.add(lblLimpiar);
		this.add(lblCodBarras);
		this.add(lblDescripcion);
		this.add(lblPrecioV);
		this.add(lblCantFCompra);
		this.add(lblCantMinimaStock);
		this.add(lblStockActual);
		this.add(lblIdProducto);
		this.add(lblIdProductoSelec);
		this.add(lblFotoUrl);
				
		this.add(txtCodBarras);
		this.add(txtDescripcion);
		this.add(txtPrecioV);
		this.add(txtCantFCompra);
		this.add(txtCantMinimaStock);
		this.add(txtStockActual);
		this.add(txtFotoUrl);
	}
	
	//TODO Poner N a AsignarEvetos
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
								//prodAlta.setFoto(null);
							} else {
								JOptionPane.showMessageDialog(null, "El stock actual ingresado no es valido");
							}
						} else {
							JOptionPane.showMessageDialog(null, "La cantidad mínima stock ingresada no es valida");
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

							JOptionPane.showMessageDialog(null, "Producto dado de alta con éxito");
						} catch (excepciones.GenericRemoteException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "No se pudo dar de alta el producto");
						}
					} else {
						JOptionPane.showMessageDialog(null, "El Código de Barras ingresado ya existe");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Faltan completar campos");
				}
			} else {
				
				if (click.getActionCommand().equals("Modificar")) {
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Seleccione un artículo antes de modificar");
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

								ProductoDelegate.getInstancia().modificarProducto(prodModif);

								JOptionPane.showMessageDialog(null, "Producto modificado con éxito");
								JPanel pnlAdmP = new pnlAdminProductos(principal);
								pnlAdmP.setBounds(0, 0, 1000, 600);
								principal.remove(this);
								principal.getContentPane().add(pnlAdmP);
								principal.repaint();

							} catch (excepciones.GenericRemoteException e) {
								JOptionPane.showMessageDialog(null, "No se pudo modificar el artículo");
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

								JOptionPane.showMessageDialog(null, "Producto dado de baja con éxito");
								
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
							resp = JOptionPane.showInputDialog("Escriba el Código de Barras a buscar: ");
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
										"Debe ingresar un Código de Barras para poder buscar");
							}
						} else {
							if (click.getActionCommand().equals("Ver Foto anterio")) {
								String resp = "";
								resp = JOptionPane.showInputDialog("Escriba el Código de Barras a buscar: ");
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
											"Debe ingresar un Código de Barras para poder buscar");
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
