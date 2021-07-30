package guis_5;

import clases.Persona;
import arreglos.ArregloPersonas;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPersona extends JInternalFrame implements ActionListener {
	
	private JScrollPane scrollPane;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnConsultar;
	private JButton btnEliminar;
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
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(false);
		setTitle("Mantenimiento | Persona");
		setBounds(100, 100, 810, 610);
		getContentPane().setLayout(null);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(10, 10, 192, 23);
		getContentPane().add(btnAdicionar);
		
		btnConsultar = new JButton("Consultar");		
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(204, 10, 192, 23);
		getContentPane().add(btnConsultar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(398, 10, 192, 23);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(592, 10, 192, 23);
		getContentPane().add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 775, 520);
		getContentPane().add(scrollPane);

		tblPersona = new JTable();
		tblPersona.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblPersona);

		modelo = new DefaultTableModel();
		modelo.addColumn("C�DIGO");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("DNI");
		modelo.addColumn("PESO (kg)");
		modelo.addColumn("ESTATURA (mts)");
		modelo.addColumn("ESTADO CIVIL");
		modelo.addColumn("IMC = peso/estatura�");
		modelo.addColumn("GRADO");
		tblPersona.setModel(modelo);
		
		ajustarAnchoColumnas();
		listar();
		
		tblPersona.getSelectionModel().setSelectionInterval(0, 0);
	}

	//  Declaraci�n global
	ArregloPersonas ap = new ArregloPersonas();
	
	int codigo, estado;
	String nombre, dni;
	double peso, estatura;
	
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
	}
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		codigo = ap.codigoCorrelativo();
		nombre = "NN";
		dni = "88888888";
		peso = 88.8;
		estatura = 8.8;
		estado = 0;
		String s = "C�DIGO :  " + codigo + "\n" + "NOMBRE";
		try {
			nombre = adicionarModificar(s, nombre);
			s += (" :  " + nombre + "\n" + "DNI");
			dni = adicionarModificar(s, dni);
			if (ap.buscar(dni) == null) {
				s += (" :  " + dni + "\n" + "PESO (kg)");
				peso = Double.parseDouble(adicionarModificar(s, "" + peso));
				s += (" :  " + peso + "\n" + "ESTATURA (mts)");
				estatura = Double.parseDouble(adicionarModificar(s, "" + estatura));
				s += (" :  " + estatura + "\n" + "ESTADO CIVIL :  ");
				estado = Integer.parseInt(adicionarModificar(s + " [0] Soltero [1] Casado [2] Viudo [3] Divorciado", "" + estado));
				s += (enTextoEstadoCivil(estado));
				int ok = verificar(s);
				if (ok == 0) {
					Persona nueva = new Persona(codigo, nombre, dni, peso, estatura, estado);
					ap.adicionar(nueva);
					listar();
					tblPersona.getSelectionModel().setSelectionInterval(ap.tama�o()-1, ap.tama�o()-1);
				}
			}
			else
				mensaje("El dni " + dni + " ya existe");
		}
		catch (Exception x) {	
		}
	}
	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		Persona x = ap.obtener(tblPersona.getSelectedRow());
		mensaje("C�DIGO :  " + x.getCodigo() + "\n" + 
		        "NOMBRE :  " + x.getNombre() + "\n" + 	
		        "DNI :  " + x.getDni() + "\n" +
		        "PESO :  " + x.getPeso() + " kg " + "\n" +
		        "ESTATURA :  " + x.getEstatura() + " mts" + "\n" +
		        "ESTADO CIVIL :  " + enTextoEstadoCivil(x.getEstado()) + "\n" +
		        "IMC :  " + ajustar(x.imc()) + "\n" +
		        "GRADO :  " + x.grado());
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		int posFila = tblPersona.getSelectedRow();
		Persona x = ap.obtener(posFila);
		String s = "C�DIGO :  " + x.getCodigo() + "\n" + "NOMBRE";
		try {
			nombre = adicionarModificar(s, x.getNombre());
			s += (" :  " + nombre + "\n" + "DNI :  " + x.getDni() + "\n" + "PESO (kg)");
			peso = Double.parseDouble(adicionarModificar(s, "" + x.getPeso()));
			s += (" :  " + peso + "\n" + "ESTATURA (mts)");
			estatura = Double.parseDouble(adicionarModificar(s, "" + x.getEstatura()));
			s += (" :  " + estatura + "\n" + "ESTADO CIVIL :  ");
			estado = Integer.parseInt(adicionarModificar(s + " [0] Soltero [1] Casado [2] Viudo [3] Divorciado", "" + x.getEstado()));
			s += (enTextoEstadoCivil(estado));
			int ok = verificar(s);
			if (ok == 0) {
				x.setNombre(nombre);
				x.setPeso(peso);
				x.setEstatura(estatura);
				x.setEstado(estado);
				ap.actualizarArchivo();
				listar();
				tblPersona.getSelectionModel().setSelectionInterval(posFila, posFila);
			}
		} 
		catch (Exception e) {	
		}	
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int posFila = tblPersona.getSelectedRow();
		Persona x = ap.obtener(posFila);
		int ok = confirmar("� Desea eliminar el registro ?");
		if (ok == 0) {
			ap.eliminar(x);
			listar();
			if (ap.tama�o() > 0) {
				if (posFila == tblPersona.getRowCount())
					posFila --;
				tblPersona.getSelectionModel().setSelectionInterval(posFila, posFila);
			}
		}
	}
	protected void actionPerformedBtnSalir(ActionEvent e) {
		dispose();
	}
	//  M�todos sin valor de retorno (sin par�metros)
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
		for (int i=0; i<ap.tama�o(); i++) {
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
	//  M�todos tipo void (con par�metros)
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Persona", 1);
	}	
	//  M�todos que retornan valor (con par�metros)
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	String enTextoEstadoCivil(int i) {
		switch (i) {
			case 0:
				return "Soltero";
			case 1:
				return "Casado";
			case 2:
				return "Viudo";
			default:
				return "Divorciado";
		}
	}
	double ajustar(double numero) {
		return (int)(numero * 10) / 10.0;
	}
	String adicionarModificar(String s, String texto) {
		return JOptionPane.showInputDialog(this, s, "Persona", -1, null, null, texto).toString();
	}
	int verificar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Persona", 0, 3, null);
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Persona", 0, 0, null);
	}
	
}