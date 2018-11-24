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

import businessDelegate.ProveedorDelegate;
import dto.ProveedorDTO;
import excepciones.GenericRemoteException;

public class pnlAdminProveedores extends JPanel implements ActionListener {

	private static final long serialVersionUID = 8124324534423655238L;

	ImageIcon fondo;
	JFrame principal;
	JTable tblProveedores;
	JButton btnBaja, btnAlta, btnModificar, btnAtras, btnBuscar;
	//TODO
	JLabel lblLimpiar, lblNombre, lblUrl, lblApiKey, lblIdProveedor, lblIdProveedorSelec;
	JTextField txtNombre, txtUrl, txtApiKey;
	DefaultTableModel model, modeloBusqueda;//Model es la tabla y si tocas te carga automaticamente a los textbox
											//modeloBusqueda ayuda al boton limpiar 
	List<ProveedorDTO> proveedores;
	ProveedorDTO proveedoresTabla, proveAlta, proveBaja, proveModif;
	String[][] datos, datos2, csReturn;		//datos es datos txt, datos2 es para boton limpiar
	String[] columnas = { "# Proveedor", "Nombre", "URL", "Api-Key" };
	static DateTimeFormatter formatF = DateTimeFormatter.ofPattern("yyyy-MM-dd");	//Esta de adorno
	int cantC, i = 0, idP, row = -1, idItem;
	JScrollPane scrollBar;

	public pnlAdminProveedores(JFrame frm) {
		principal = frm;
		principal.setTitle("Almacén - proveedores");
		Inicializar();
		AsignarEventos();
		this.setVisible(true);
	}

	private void Inicializar() {

		this.setLayout(null);
		this.setSize(1000, 600);
		
		proveedores = new ArrayList<ProveedorDTO>();
		
		try {
			proveedores = new ArrayList<ProveedorDTO>();
			proveedores = ProveedorDelegate.getInstancia().findAllProveedores();
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		/*System.out.println("FOORRRRR");
		for (ProveedorDTO item : proveedores) {
			System.out.println("Id: " + item.getIdProducto());
			System.out.println("Descripcion: " + item.getDescripcion());
			System.out.println();
		}*/
		
		datos = new String[proveedores.size()][4];
		
		for (ProveedorDTO proveedorList : proveedores) {
			datos[i][0] = String.valueOf(proveedorList.getIdProveedor());
			datos[i][1] = proveedorList.getNombre();
			datos[i][2] = proveedorList.getUrl();
			datos[i][3] = proveedorList.getApiKey();

			i++;
		}

		model = new DefaultTableModel(datos, columnas); //Columnas es nombre columnas
		tblProveedores = new JTable(model);
		scrollBar = new JScrollPane();
		scrollBar.setViewportView(tblProveedores);
		scrollBar.setBounds(35, 75, 425, 420);

		tblProveedores.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = tblProveedores.getSelectedRow();
				idP = Integer.parseInt(datos[row][0]);				
				
				lblIdProveedorSelec.setText("");
				txtNombre.setText(datos[row][1]);
				txtUrl.setText(datos[row][2]);
				txtApiKey.setText(datos[row][3]);
				
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
		lblNombre = new JLabel("Nombre: ");
		lblUrl = new JLabel("URL: ");
		lblApiKey = new JLabel("Api-Key: ");
		lblIdProveedor = new JLabel("Id proveedor: ");
		lblIdProveedorSelec = new JLabel("");
		
		
		txtNombre = new JTextField();
		txtNombre.setOpaque(false);
		txtUrl = new JTextField();
		txtUrl.setOpaque(false);
		txtApiKey = new JTextField();
		txtApiKey.setOpaque(false);
		
		
		lblLimpiar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = -1;
				txtNombre.setText(null);
				txtUrl.setText(null);
				txtApiKey.setText(null);
				lblIdProveedorSelec.setText("");
				
				datos2 = new String[proveedores.size()][4];
				int y = 0;
				
								
				for (ProveedorDTO proveedorList : proveedores) {
					datos2[y][0] = String.valueOf(proveedorList.getIdProveedor());
					datos2[y][1] = proveedorList.getNombre();
					datos2[y][2] = proveedorList.getUrl();
					datos2[y][3] = proveedorList.getApiKey();

					y++;
				}
				datos = datos2;
				model.setDataVector(datos, columnas);
				tblProveedores.setModel(model);
				principal.repaint();
			}
		});
		
		
		lblNombre.setBounds(529, 105, 130, 30);
		lblUrl.setBounds(529, 145, 130, 30);
		lblApiKey.setBounds(529, 185, 130, 30);
		lblIdProveedor.setBounds(529, 225, 130, 30);
		
		
		txtNombre.setBounds(769, 105, 150, 30);
		txtUrl.setBounds(769, 145, 150, 30);
		txtApiKey.setBounds(769, 185, 150, 30);
		lblIdProveedorSelec.setBounds(769, 225, 130, 30);
		
		lblLimpiar.setBounds(816, 51, 80, 30);
		lblLimpiar.setForeground(Color.BLUE);
		
		this.add(btnAlta);
		this.add(btnBaja);
		this.add(btnAtras);
		this.add(btnModificar);
		this.add(btnBuscar);
		this.add(scrollBar);
		
		
		this.add(lblLimpiar);
		this.add(lblNombre);
		this.add(lblUrl);
		this.add(lblApiKey);
		this.add(lblIdProveedor);
		this.add(lblIdProveedorSelec);
				
		this.add(txtNombre);
		this.add(txtUrl);
		this.add(txtApiKey);
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
				
				if (!txtNombre.getText().equals("") && !txtUrl.getText().equals("")
						&& !txtApiKey.getText().equals("")) {
					
					proveAlta = new ProveedorDTO();
					proveAlta.setIdProveedor(null);
					proveAlta.setNombre(txtNombre.getText());
					proveAlta.setUrl(txtUrl.getText());
					proveAlta.setApiKey(txtApiKey.getText());
										
					
					if (!existeNom(txtNombre.getText()) ) {
						try {
							ProveedorDelegate.getInstancia().altaProveedor(proveAlta);

							JPanel pnlAdmP = new pnlAdminProveedores(principal);
							pnlAdmP.setBounds(0, 0, 1000, 600);
							principal.remove(this);
							principal.getContentPane().add(pnlAdmP);
							principal.repaint();

							JOptionPane.showMessageDialog(null, "Proveedor dado de alta con éxito");
						} catch (excepciones.GenericRemoteException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "No se pudo dar de alta el proveedor");
						}
					} else {
						JOptionPane.showMessageDialog(null, "El nombre ingresado ya existe");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Faltan completar campos");
				}
			} else {
				
				if (click.getActionCommand().equals("Modificar")) {
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Seleccione un proveedor antes de modificar");
					} else {
						if (!txtNombre.getText().equals("") && !txtUrl.getText().equals("")
								&& !txtApiKey.getText().equals("")) {

							idP = Integer.parseInt(datos[row][0]);
							try {
								proveModif = ProveedorDelegate.getInstancia().buscarProveedorById(idP);
								
								proveModif.setNombre(txtNombre.getText());
								proveModif.setUrl(txtUrl.getText());
								proveModif.setApiKey(txtApiKey.getText());

								ProveedorDelegate.getInstancia().modificarProveedor(proveModif);

								JOptionPane.showMessageDialog(null, "Proveedor modificado con éxito");
								JPanel pnlAdmP = new pnlAdminProveedores(principal);
								pnlAdmP.setBounds(0, 0, 1000, 600);
								principal.remove(this);
								principal.getContentPane().add(pnlAdmP);
								principal.repaint();

							} catch (excepciones.GenericRemoteException e) {
								JOptionPane.showMessageDialog(null, "No se pudo modificar el proveedor");
								e.printStackTrace();
							}
						}
					}
				} else {
					
					
					if (click.getActionCommand().equals("Baja")) {
						if (row >= 0) {
							idP = Integer.parseInt(datos[row][0]);

							try {

								ProveedorDelegate.getInstancia().bajaProveedor(idP);

								JOptionPane.showMessageDialog(null, "Proveedor dado de baja con éxito");
								
								JPanel pnlAdmP = new pnlAdminProveedores(principal);
								pnlAdmP.setBounds(0, 0, 1000, 600);
								principal.remove(this);
								principal.getContentPane().add(pnlAdmP);
								principal.repaint();

							} catch (excepciones.GenericRemoteException e) {
								JOptionPane.showMessageDialog(null, "No se pudo dar de baja el proveedor");
								e.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor para dar de baja");
						}
					} else {
						if (click.getActionCommand().equals("Buscar")) {
							String resp = "";
							resp = JOptionPane.showInputDialog("Escriba el Nombre a buscar: ");
							if (resp != "") {
								String[][] resBusqueda = buscarProveedores(datos, resp);
								if (resBusqueda != null) {
									modeloBusqueda = new DefaultTableModel(resBusqueda, columnas);
									tblProveedores.setModel(modeloBusqueda);
									datos = resBusqueda;
									principal.repaint();
								} else {
									JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Debe ingresar un Nombre para poder buscar");
							}
						}
					}
				}
			}
		}
	}

	public void paint(Graphics g) {
		fondo = new ImageIcon(getClass().getResource("/vistas/adminProveedores.jpg"));
		g.drawImage(fondo.getImage(), 0, 0, 994, 580, null);
		setOpaque(false);
		super.paint(g);
	}

	public String[][] buscarProveedores(String[][] datos, String nombre) {

		int filas = buscarCantP(datos, nombre);
		if (filas > 0) {
			csReturn = new String[filas][7];
			int tamD = datos.length;
			int k = 0;
			for (int o = 0; o < tamD; o++) {
				if ((datos[o][1]).trim().equals(nombre.trim())) {
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

	public boolean existeNom(String e) {
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
