package com.challenge.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class MenuUsuario extends JFrame {

	private JPanel contentPane;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUsuario frame = new MenuUsuario("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuUsuario(String usr) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUsuario.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 609);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(12, 138, 199));
		panelMenu.setBounds(0, 0, 257, 609);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(50, 58, 150, 150);
		panelMenu.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/aH-150px.png")));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 219, 201, 2);
		panelMenu.add(separator);
		
		JButton btnRegistro = new JButton("Registro de Reservas");
		btnRegistro.setBorderPainted(false);
		btnRegistro.setFocusable(false);
		btnRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistro.setBackground(new Color(118, 187, 223));				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRegistro.setBackground(new Color(12, 138, 199));	
			}
		});
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReservasView reservas = new ReservasView(usr);
				reservas.setVisible(true);
				dispose();
			}
		});
		btnRegistro.setForeground(new Color(255, 255, 255));
		btnRegistro.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/icon-reservas.png")));
		btnRegistro.setFont(new Font("Eras Medium ITC", Font.PLAIN, 18));
		btnRegistro.setBackground(new Color(12, 138, 199));
		btnRegistro.setBounds(0, 255, 257, 56);
		panelMenu.add(btnRegistro);
		
		JButton btnBusqueda = new JButton("Búsqueda");
		btnBusqueda.setBorderPainted(false);
		btnBusqueda.setFocusable(false);
		btnBusqueda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBusqueda.setBackground(new Color(118, 187, 223));				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBusqueda.setBackground(new Color(12, 138, 199));	
			}
		});
		btnBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Busqueda busqueda = new Busqueda(usr);
				busqueda.setVisible(true);
				dispose();
			}
		});
		btnBusqueda.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/pessoas.png")));
		btnBusqueda.setHorizontalAlignment(SwingConstants.LEFT);
		btnBusqueda.setForeground(new Color(255, 255, 255));
		btnBusqueda.setFont(new Font("Eras Medium ITC", Font.PLAIN, 18));
		btnBusqueda.setBackground(new Color(12, 138, 199));
		btnBusqueda.setBounds(0, 312, 257, 56);
		panelMenu.add(btnBusqueda);
		
		JButton btnSalir = new JButton("Salir del Sistema");
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBorderPainted(false);
		btnSalir.setFocusable(false);
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSalir.setBackground(new Color(118, 187, 223));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				 btnSalir.setBackground(new Color(12, 138, 199));
			}
		});
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/icon-salir.png")));
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalir.setFont(new Font("Eras Medium ITC", Font.PLAIN, 18));
		btnSalir.setBackground(new Color(12, 138, 199));
		btnSalir.setBounds(0, 369, 257, 56);
		panelMenu.add(btnSalir);
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 944, 36);
		contentPane.add(header);
		
	    JPanel panelFecha = new JPanel();
	    panelFecha.setBackground(new Color(118, 187, 223));
	    panelFecha.setBounds(256, 84, 688, 121);
	    contentPane.add(panelFecha);
	    panelFecha.setLayout(null);
	    
	    JLabel lblNewLabel_1 = new JLabel("Sistema de reservas Hotel Alura");
	    lblNewLabel_1.setBounds(180, 11, 356, 42);
	    panelFecha.add(lblNewLabel_1);
	    lblNewLabel_1.setForeground(Color.WHITE);
	    lblNewLabel_1.setFont(new Font("Eras Medium ITC", Font.PLAIN, 24));
	    
	    JLabel labelFecha = new JLabel("New label");
	    labelFecha.setBounds(35, 64, 294, 36);
	    panelFecha.add(labelFecha);
	    labelFecha.setForeground(Color.WHITE);
	    labelFecha.setFont(new Font("Eras Medium ITC", Font.PLAIN, 33));
	    Date fechaActual = new Date(); //fecha y hora actual
	    String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual); //formatear la fecha en una cadena
	    labelFecha.setText("Hoy es " + fecha); //setear la representacion en cadena de la fecha
	    
	    JLabel lblNewLabel = new JLabel("Bienvenido " + usr);
	    lblNewLabel.setFont(new Font("Eras Bold ITC", Font.BOLD, 24));
	    lblNewLabel.setBounds(302, 234, 419, 46);
	    contentPane.add(lblNewLabel);
	    
	    String textoDescripcion = "<html><body>Sistema de reserva de hotel. Controle y administre de forma óptima y fácil <br> el flujo de reservas y de huespédes del hotel   </body></html>";
	    JLabel labelDescripcion = new JLabel(textoDescripcion);
	    labelDescripcion.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
	   
	    labelDescripcion.setBounds(312, 291, 598, 66);
	    contentPane.add(labelDescripcion);
	    
	    String textoDescripcion1 = "<html><body> Esta herramienta le permitirá llevar un control completo y detallado de sus reservas y huéspedes, tendrá acceso a heramientas especiales para tareas específicas como lo son:</body></html>";
	    JLabel labelDescripcion_1 = new JLabel(textoDescripcion1);
	    labelDescripcion_1.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
	    labelDescripcion_1.setBounds(311, 345, 569, 88);
	    contentPane.add(labelDescripcion_1);
	    
	    JLabel lblNewLabel_3 = new JLabel("- Registro de Reservas y Huéspedes");
	    lblNewLabel_3.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
	    lblNewLabel_3.setBounds(312, 444, 295, 27);
	    contentPane.add(lblNewLabel_3);
	    
	    JLabel lblNewLabel_3_1 = new JLabel("- Edición de Reservas y Huéspedes existentes");
	    lblNewLabel_3_1.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
	    lblNewLabel_3_1.setBounds(312, 482, 355, 27);
	    contentPane.add(lblNewLabel_3_1);
	    
	    JLabel lblNewLabel_3_2 = new JLabel("- Eliminar todo tipo de registros");
	    lblNewLabel_3_2.setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
	    lblNewLabel_3_2.setBounds(312, 520, 295, 27);
	    contentPane.add(lblNewLabel_3_2);
	}
	
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
