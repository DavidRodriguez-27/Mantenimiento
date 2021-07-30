package guis_2;

import arreglos.ArregloPersonas;
import clases.Persona;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPersona extends JInternalFrame implements ActionListener {
	
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblDni;
	private JLabel lblPeso;
	private JLabel lblEstatura;
	private JLabel lblEstadoCivil;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtPeso;
	private JTextField txtEstatura;
	private JComboBox <String> cboEstadoCivil;
	private JButton btnBuscar;
	private JButton btnOK;
	private JButton btnOpciones;
	private JButton btnAdicionar;
	private JButton btnConsultar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable tblPersona;
	private DefaultTableModel modelo;

	//  Tipo de operación a procesar: Adicionar, Consultar, Modificar o Eliminar
	private int tipoOperacion;
	
	//  Constantes para los tipos de operaciones
	public final static int ADICIONAR = 0;
	public final static int CONSULTAR = 1;
	public final static int MODIFICAR = 2;
	public final static int ELIMINAR  = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgPersona dialog = new DlgPersona();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public DlgPersona() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(false);
		setTitle("Mantenimiento | Persona");
		setBounds(100, 100, 810, 610);
		getContentPane().setLayout(null);
		
		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(10, 10, 110, 23);
		getContentPane().add(lblCodigo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 35, 70, 23);
		getContentPane().add(lblNombre);
	
		lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 60, 70, 23);
		getContentPane().add(lblDni);
		
		lblPeso = new JLabel("Peso");
		lblPeso.setBounds(10, 85, 70, 23);
		getContentPane().add(lblPeso);
		
		lblEstatura = new JLabel("Estatura");
		lblEstatura.setBounds(10, 110, 70, 23);
		getContentPane().add(lblEstatura);
		
		lblEstadoCivil = new JLabel("Estado civil");
		lblEstadoCivil.setBounds(10, 135, 86, 23);
		getContentPane().add(lblEstadoCivil);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(90, 10, 86, 23);
		getContentPane().add(txtCodigo);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(90, 35, 251, 23);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtDni = new JTextField();
		txtDni.setBounds(90, 60, 86, 23);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(90, 85, 50, 23);
		getContentPane().add(txtPeso);
		txtPeso.setColumns(10);

		txtEstatura = new JTextField();
		txtEstatura.setBounds(90, 110, 50, 23);
		getContentPane().add(txtEstatura);
		txtEstatura.setColumns(10);

		cboEstadoCivil = new JComboBox <String> ();
		cboEstadoCivil.setModel(new DefaultComboBoxModel <String> 
		                       (new String[] {"Soltero", "Casado", "Viudo", "Divorciado"}));
		cboEstadoCivil.setBounds(90, 135, 86, 23);
		getContentPane().add(cboEstadoCivil);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(240, 10, 101, 23);
		getContentPane().add(btnBuscar);

		btnOK = new JButton("OK");
		btnOK.addActionListener(this);
		btnOK.setBounds(240, 135, 100, 23);
		getContentPane().add(btnOK);
		
		btnOpciones = new JButton("Opciones");
		btnOpciones.addActionListener(this);
		btnOpciones.setBounds(555, 10, 100, 98);
		getContentPane().add(btnOpciones);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(664, 10, 120, 23);
		getContentPane().add(btnAdicionar);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(664, 35, 120, 23);
		getContentPane().add(btnConsultar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(664, 60, 120, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(664, 85, 120, 23);
		getContentPane().add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 170, 775, 390);
		getContentPane().add(scrollPane);
		
		tblPersona = new JTable();
		tblPersona.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblPersona);

		modelo = new DefaultTableModel();
		modelo.addColumn("CÓDIGO");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("DNI");
		modelo.addColumn("PESO (kg)");
		modelo.addColumn("ESTATURA (mts)");
		modelo.addColumn("ESTADO CIVIL");
		modelo.addColumn("IMC = peso/estatura²");
		modelo.addColumn("GRADO");
		tblPersona.setModel(modelo);
		
		ajustarAnchoColumnas();
		listar();
		
		habilitarEntradas(false);
		habilitarBotones(true);
	}
	
	//  Declaración global
	ArregloPersonas ap = new ArregloPersonas();
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
		if (arg0.getSource() == btnOpciones) {
			actionPerformedBtnOpciones(arg0);
		}
		if (arg0.getSource() == btnOK) {
			actionPerformedBtnOK(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		consultarPersona();
	}
	protected void actionPerformedBtnOK(ActionEvent arg0) {
		switch (tipoOperacion) {
			case ADICIONAR:
				adicionarPersona();
				break;
			case CONSULTAR:
				consultarPersona();
				break;
			case MODIFICAR:
				modificarPersona();
				break;
			case ELIMINAR:
				eliminarPersona();
		}
	}
	protected void actionPerformedBtnOpciones(ActionEvent arg0) {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtDni.setText("");
		txtPeso.setText("");
		txtEstatura.setText("");
		txtCodigo.setEditable(false);
		habilitarEntradas(false);
		habilitarBotones(true);
	}
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		tipoOperacion = ADICIONAR;
		txtCodigo.setText("" + ap.codigoCorrelativo());
		habilitarEntradas(true);
		habilitarBotones(false);
		txtNombre.requestFocus();
	}
	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		tipoOperacion = CONSULTAR;
		txtCodigo.setEditable(true);
		habilitarBotones(false);
		txtCodigo.requestFocus();
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		tipoOperacion = MODIFICAR;
		txtCodigo.setEditable(true);
		habilitarBotones(false);
		txtCodigo.requestFocus();
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		tipoOperacion = ELIMINAR;
		txtCodigo.setEditable(true);
		habilitarBotones(false);
		txtCodigo.requestFocus();
	}
	//  Métodos tipo void (sin parámetros)
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblPersona.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna( 8));  // codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(18));  // nombre
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10));  // dni
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10));  // peso
		tcm.getColumn(4).setPreferredWidth(anchoColumna(15));  // estatura
		tcm.getColumn(5).setPreferredWidth(anchoColumna(12));  // estadoCivil
		tcm.getColumn(6).setPreferredWidth(anchoColumna(17));  // imc
		tcm.getColumn(7).setPreferredWidth(anchoColumna(10));  // grado
	}
	void listar() {
		Persona x;
		modelo.setRowCount(0);
		for (int i=0; i<ap.tamaño(); i++) {
			x = ap.obtener(i);
			Object[] fila = { x.getCodigo(),
					          x.getNombre(),
					          x.getDni(),
					          x.getPeso(),
					          x.getEstatura(),
					          enTextoEstadoCivil(x.getEstado()),
					          ajustar(x.imc()),
					          x.grado() };
			modelo.addRow(fila);
		}
	}
	void adicionarPersona() {
		int codigo = leerCodigo();
		String nombre = leerNombre();
		if (nombre.length() > 0) {
			String dni = leerDni();
			if (dni.length() > 0)
				if (ap.buscar(dni) == null) 
					try {
						double peso = leerPeso();
						try {
							double estatura = leerEstatura();
							int estado = leerPosEstado();
							Persona nueva = new Persona(codigo, nombre, dni, peso, estatura, estado);
							ap.adicionar(nueva);
							listar();
							txtCodigo.setText("" + ap.codigoCorrelativo());
							txtNombre.setText("");
							txtDni.setText("");
							txtPeso.setText("");
							txtEstatura.setText("");
							txtNombre.requestFocus();
						}
						catch (Exception e) {
							error("Ingrese ESTATURA correcta", txtEstatura);
						}
					}
					catch (Exception e) {
						error("Ingrese PESO correcto", txtPeso);
					}
				else
					error("El DNI " + dni + " ya existe", txtDni);
			else
				error("Ingrese DNI correcto", txtDni);
		}
		else
			error("Ingrese NOMBRE correcto", txtNombre);
	}
	void consultarPersona() {
		try {
			int codigo = leerCodigo();
			Persona x = ap.buscar(codigo);
			if (x != null) {
				txtNombre.setText(x.getNombre());
				txtDni.setText(x.getDni());
				txtPeso.setText("" + x.getPeso());
				txtEstatura.setText("" + x.getEstatura());
				cboEstadoCivil.setSelectedIndex(x.getEstado());
				if (tipoOperacion == MODIFICAR) {
					habilitarEntradas(true);
					txtCodigo.setEditable(false);
					btnBuscar.setEnabled(false);
					btnOK.setEnabled(true);
					txtNombre.requestFocus();
				}
				if (tipoOperacion == ELIMINAR) {
					txtCodigo.setEditable(false);
					btnBuscar.setEnabled(false);
					btnOK.setEnabled(true);
				}
			}
			else
				error("El código " + codigo + " no existe", txtCodigo);
		}
		catch (Exception e) {
			error("Ingrese CÓDIGO correcto", txtCodigo);
		}
	}
	void modificarPersona() {
		try {
			int codigo = leerCodigo();
			Persona x = ap.buscar(codigo);
			if (x != null) {
				String nombre = leerNombre();
				if (nombre.length() > 0)
					try {
						double peso = leerPeso();
						try {
							double estatura = leerEstatura();
							int estado = leerPosEstado();
							x.setNombre(nombre);
							x.setPeso(peso);
							x.setEstatura(estatura);
							x.setEstado(estado);
							ap.actualizarArchivo();
							listar();
							txtNombre.requestFocus();
						}
						catch (Exception e) {
							error("Ingrese ESTATURA correcta", txtEstatura);
						}	
					}
					catch (Exception e) {
						error("Ingrese PESO correcto", txtPeso);
					}
				else
					error("Ingrese NOMBRE correcto", txtNombre);
			}
			else
				error("El código " + codigo + " no existe", txtCodigo);
		}
		catch (Exception e) {
			error("Ingrese CÓDIGO correcto", txtCodigo);
		}
	}
	void eliminarPersona() {
		try {
			int codigo = leerCodigo();
			Persona x = ap.buscar(codigo);
			if (x != null) {
				int ok = confirmar("¿ Desea eliminar el registro ?");
				if (ok == 0) {
					ap.eliminar(x);
					listar();
					btnOK.setEnabled(false);
				}
			}
			else
				error("El código " + codigo + " no existe", txtCodigo);
		}
		catch (Exception e) {
			error("Ingrese CÓDIGO correcto", txtCodigo);
		}	
	}
	//  Métodos tipo void (con parámetros)
	void habilitarEntradas(boolean sino) {
		txtNombre.setEditable(sino);
		if (tipoOperacion == ADICIONAR)
			txtDni.setEditable(sino);
		txtPeso.setEditable(sino);
		txtEstatura.setEditable(sino);
		cboEstadoCivil.setEnabled(sino);
	}
	void habilitarBotones(boolean sino) {
		if (tipoOperacion == ADICIONAR)
			btnOK.setEnabled(!sino);
		else {
			btnBuscar.setEnabled(!sino);
			btnOK.setEnabled(false);
		}	
		btnAdicionar.setEnabled(sino);
		btnConsultar.setEnabled(sino);
		btnModificar.setEnabled(sino);
		btnEliminar.setEnabled(sino);
		btnOpciones.setEnabled(!sino);		
	}
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 0);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	//  Métodos que retornan valor (sin parámetros)
	int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText().trim());
	}
	String leerNombre() {
		return txtNombre.getText().trim();
	}
	String leerDni() {
		return txtDni.getText().trim();
	}
	double leerPeso() {
		return Double.parseDouble(txtPeso.getText().trim());
	}
	double leerEstatura() {
		return Double.parseDouble(txtEstatura.getText().trim());
	}
	int leerPosEstado() {
		return cboEstadoCivil.getSelectedIndex();
	}
	//  Métodos que retornan valor (con parámetros)
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	String enTextoEstadoCivil(int i) {
		return cboEstadoCivil.getItemAt(i);
	}
	double ajustar(double numero) {
		return (int)(numero * 10) / 10.0;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	
}