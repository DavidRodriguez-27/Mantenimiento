package guis_1;

import arreglos.ArregloPersonas;
import clases.Persona;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPersona extends JDialog implements ActionListener {
	
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
	private JComboBox <String> cboOperacion;
	private JButton btnOK;
	private JScrollPane scrollPane;
	private JTable tblPersona;
	private DefaultTableModel modelo;

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
		setResizable(false);
		setTitle("Mantenimiento | Persona");
		setBounds(100, 100, 810, 610);
		getContentPane().setLayout(null);
		
		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(530, 10, 50, 23);
		getContentPane().add(lblCodigo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 10, 70, 23);
		getContentPane().add(lblNombre);
	
		lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 35, 70, 23);
		getContentPane().add(lblDni);
		
		lblPeso = new JLabel("Peso");
		lblPeso.setBounds(10, 60, 70, 23);
		getContentPane().add(lblPeso);
		
		lblEstatura = new JLabel("Estatura");
		lblEstatura.setBounds(10, 85, 70, 23);
		getContentPane().add(lblEstatura);
		
		lblEstadoCivil = new JLabel("Estado civil");
		lblEstadoCivil.setBounds(10, 110, 86, 23);
		getContentPane().add(lblEstadoCivil);
		
		txtCodigo = new JTextField("" + ap.codigoCorrelativo());
		txtCodigo.setBounds(590, 10, 86, 23);
		getContentPane().add(txtCodigo);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(90, 10, 251, 23);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtDni = new JTextField();
		txtDni.setBounds(90, 35, 86, 23);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(90, 60, 50, 23);
		getContentPane().add(txtPeso);
		txtPeso.setColumns(10);

		txtEstatura = new JTextField();
		txtEstatura.setBounds(90, 85, 50, 23);
		getContentPane().add(txtEstatura);
		txtEstatura.setColumns(10);

		cboEstadoCivil = new JComboBox <String> ();
		cboEstadoCivil.setModel(new DefaultComboBoxModel <String> 
		                       (new String[] {"Soltero", "Casado", "Viudo", "Divorciado"}));
		cboEstadoCivil.setBounds(90, 110, 86, 23);
		getContentPane().add(cboEstadoCivil);

		cboOperacion = new JComboBox <String> ();
		cboOperacion.addActionListener(this);
		cboOperacion.setModel(new DefaultComboBoxModel <String>
		                     (new String[] {"Adicionar", "Consultar", "Modificar", "Eliminar"}));
		cboOperacion.setBounds(685, 10, 99, 23);
		getContentPane().add(cboOperacion);
	
		btnOK = new JButton("OK");
		btnOK.addActionListener(this);
		btnOK.setBounds(240, 110, 100, 23);
		getContentPane().add(btnOK);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 775, 415);
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
	}
	
	//  Declaración global
	ArregloPersonas ap = new ArregloPersonas();
	int codigoConsultado = ap.obtener(0).getCodigo();
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnOK) {
			actionPerformedBtnOK(arg0);
		}
		if (arg0.getSource() == cboOperacion) {
			actionPerformedCboOperacion(arg0);
		}
	}
	protected void actionPerformedCboOperacion(ActionEvent arg0) {
		int posOperacion = leerPosOperacion();
		switch (posOperacion) {
			case 0:
				if (ap.tamaño() == 0)
					btnOK.setEnabled(true);
				txtCodigo.setEditable(false);
				txtCodigo.setText("" + ap.codigoCorrelativo());
				txtNombre.setText("");
				txtDni.setText("");
				txtPeso.setText("");
				txtEstatura.setText("");
				txtNombre.requestFocus();
				habilitarEntradas(true);
				txtNombre.requestFocus();
				break;
			case 1:
				if (ap.tamaño() > 0)
					txtCodigo.setEditable(true);
				txtCodigo.setText("" + codigoConsultado);
				habilitarEntradas(false);
				consultarPersona();
				txtCodigo.requestFocus();
				break;
			case 2:
				txtCodigo.setEditable(false);
				txtCodigo.setText("" + codigoConsultado);
				habilitarEntradas(true);
				consultarPersona();
				txtNombre.requestFocus();
				break;
			case 3:
				txtCodigo.setEditable(false);
				txtCodigo.setText("" + codigoConsultado);
				habilitarEntradas(false);
				consultarPersona();		
		}
	}
	protected void actionPerformedBtnOK(ActionEvent arg0) {
		int posOperacion = leerPosOperacion();
		switch (posOperacion) {
			case 0:
				adicionarPersona();
				break;
			case 1:
				consultarPersona();
				break;
			case 2:
				modificarPersona();
				break;
			case 3:
				eliminarPersona();
				break;
		}
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
		if (ap.tamaño() == 0) {
			btnOK.setEnabled(false);
			habilitarEntradas(false);
			mensaje("No existen personas");
		}
		else
			try {
				Persona x = ap.buscar(leerCodigo());
				if (x != null) {
					codigoConsultado = x.getCodigo();
					txtNombre.setText(x.getNombre());
					txtDni.setText(x.getDni());
					txtPeso.setText("" + x.getPeso());
					txtEstatura.setText("" + x.getEstatura());
					cboEstadoCivil.setSelectedIndex(x.getEstado());
					txtCodigo.requestFocus();
				}
				else
					error("El código " + leerCodigo() + " no existe", txtCodigo);
			}
			catch (Exception e) {
				error("Ingrese CÓDIGO a consultar", txtCodigo);
			}
	}
	void modificarPersona() {
		Persona x = ap.buscar(codigoConsultado);
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
	void eliminarPersona() {
		int ok = confirmar("¿ Desea eliminar el registro ?");
		if (ok == 0) {
			Persona x = ap.buscar(codigoConsultado);
			ap.eliminar(x);
			listar();
			if (ap.tamaño() > 0) {
				codigoConsultado = ap.obtener(0).getCodigo();
				txtCodigo.setText("" + codigoConsultado);
				consultarPersona();
			}
			else {
				codigoConsultado = ap.codigoCorrelativo();
				cboOperacion.setSelectedIndex(0);
			}
		}
	}
	//  Métodos tipo void (con parámetros)
	void habilitarEntradas(boolean sino) {
		txtNombre.setEditable(sino);
		if (leerPosOperacion() == 2)
			txtDni.setEditable(false);
		else
			txtDni.setEditable(sino);
		txtPeso.setEditable(sino);
		txtEstatura.setEditable(sino);
		cboEstadoCivil.setEnabled(sino);
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
	int leerPosOperacion() {
		return cboOperacion.getSelectedIndex();
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