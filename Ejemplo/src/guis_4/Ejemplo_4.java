package guis_4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

public class Ejemplo_4 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuProyecto;
	private JMenu mnArchivo;
	private JMenuItem mntmSalir;
	private JMenu mnMantenimiento;
	private JMenuItem mntmPersona;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo_4 frame = new Ejemplo_4();
					frame.setExtendedState(MAXIMIZED_BOTH);
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
	public Ejemplo_4() {
		setResizable(false);
		setTitle("Ejemplo_4 - Semana 13");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		this.setLocationRelativeTo(null);
		
		menuProyecto = new JMenuBar();
		setJMenuBar(menuProyecto);
		
		mnArchivo = new JMenu("Archivo");
		menuProyecto.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		menuProyecto.add(mnMantenimiento);
		
		mntmPersona = new JMenuItem("Persona");
		mntmPersona.addActionListener(this);
		mnMantenimiento.add(mntmPersona);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Modificamos el desktopPane para incorporar imagen de fondo

        try {

              desktopPane = new JDesktopPane(){
               Image img = javax.imageio.ImageIO.read(
               new java.net.URL(getClass().getResource("/imagenes/fondoBibliotecaII.jpg"), "fondoBibliotecaII.jpg"));
               public void paintComponent(Graphics g){
                    super.paintComponent(g);
                    if(img != null) g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);
                    else g.drawString("Image not found", 100,100);
                    }

               };

        } catch (IOException e) { e.printStackTrace();}
		
		
		contentPane.add(desktopPane, BorderLayout.CENTER);
	 
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmPersona) {
			actionPerformedMntmPersona(e);
		}
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
	}
	protected void actionPerformedMntmSalir(ActionEvent e) {
		System.exit(0);
	}
	protected void actionPerformedMntmPersona(ActionEvent e) {
		DlgPersona frm = new DlgPersona(); 
        desktopPane.add(frm); // agregamos el formulario frm dentro del desktopPane
        

        // Dimensionar el frmLibro para que se muestre al centro del desktopPane del frmMain
        Dimension desktopSize = desktopPane.getSize();
        Dimension FrameSize = frm.getSize();
        frm.setLocation((desktopSize.width-FrameSize.width)/2,(desktopSize.height-FrameSize.height)/2);
        frm.setVisible(true); // lo hacemos visible
	}
	
}