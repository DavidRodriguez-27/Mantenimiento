package guis_1;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class frmAlumno extends JInternalFrame {
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JLabel lblNewLabel_2;
	private JButton btnConsultar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAlumno frame = new frmAlumno();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmAlumno() {
		setTitle("Consultar Alumno");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(46, 47, 46, 14);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(46, 89, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(130, 44, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(130, 86, 86, 20);
		getContentPane().add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(130, 137, 86, 20);
		getContentPane().add(txtApellido);
		
		lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setBounds(46, 140, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(287, 43, 89, 23);
		getContentPane().add(btnConsultar);

	}
}
