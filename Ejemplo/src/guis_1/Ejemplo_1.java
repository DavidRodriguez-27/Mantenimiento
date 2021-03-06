package guis_1;

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
import javax.swing.ImageIcon;

public class Ejemplo_1 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuProyecto;
	private JMenu mnArchivo;
	private JMenuItem mntmSalir;
	private JMenu mnMantenimiento;
	private JMenuItem mntmPersona;
	private JDesktopPane desktopPane;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo_1 frame = new Ejemplo_1();
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
	public Ejemplo_1() {
		setResizable(false);
		setTitle("Ejemplo_1 - Semana 13");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		this.setLocationRelativeTo(null);
		
		menuProyecto = new JMenuBar();
		setJMenuBar(menuProyecto);
		
		mnArchivo = new JMenu("Archivo");
		mnArchivo.setIcon(new ImageIcon(Ejemplo_1.class.getResource("/imagenes/apps.png")));
		menuProyecto.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(Ejemplo_1.class.getResource("/imagenes/undo.png")));
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setIcon(new ImageIcon(Ejemplo_1.class.getResource("/imagenes/proceso.png")));
		menuProyecto.add(mnMantenimiento);
		
		mntmPersona = new JMenuItem("Persona");
		mntmPersona.setIcon(new ImageIcon(Ejemplo_1.class.getResource("/imagenes/user.png")));
		mntmPersona.addActionListener(this);
		mnMantenimiento.add(mntmPersona);
		
		mnNewMenu = new JMenu("Consultar");
		mnNewMenu.setIcon(new ImageIcon(Ejemplo_1.class.getResource("/imagenes/consulta.png")));
		menuProyecto.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Alumnos");
		mntmNewMenuItem.setIcon(new ImageIcon(Ejemplo_1.class.getResource("/imagenes/users.png")));
		mntmNewMenuItem.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem);
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
		if (e.getSource() == mntmNewMenuItem) {
			actionPerformedMntmNewMenuItem(e);
		}
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
		//DlgPersona dp = new DlgPersona();
		//dp.setLocationRelativeTo(this);
		//dp.setVisible(true);
		
		DlgPersona frm = new DlgPersona(); //Esto llama a la ventana
        desktopPane.add(frm); // agregamos el formulario frm dentro del desktopPane      

        // Dimensionar el frmLibro para que se muestre al centro del desktopPane del frmMain
        Dimension desktopSize = desktopPane.getSize();
        Dimension FrameSize = frm.getSize();
        frm.setLocation((desktopSize.width-FrameSize.width)/2,(desktopSize.height-FrameSize.height)/2);
        frm.setVisible(true); // lo hacemos visible
	}
	protected void actionPerformedMntmNewMenuItem(ActionEvent e) {
		frmAlumno frm = new frmAlumno();
		desktopPane.add(frm); 
		Dimension desktopSize = desktopPane.getSize();
        Dimension FrameSize = frm.getSize();
        frm.setLocation((desktopSize.width-FrameSize.width)/2,(desktopSize.height-FrameSize.height)/2);
        frm.setVisible(true); // lo hacemos visible
		
	}
}