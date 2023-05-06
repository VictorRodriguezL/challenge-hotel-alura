package com.challenge.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.challenge.controller.HuespedController;
import com.challenge.controller.ReservaController;
import com.challenge.modelo.Huesped;
import com.challenge.modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	int xMouse, yMouse;
	private ReservaController reservaController;
	private HuespedController huespedController;
	private TableRowSorter<DefaultTableModel> sorterReservas;
	private TableRowSorter<DefaultTableModel> sorterHuespedes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda("");
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
	public Busqueda(String usr) {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Eras Medium ITC", Font.PLAIN, 18));
		txtBuscar.setBounds(536, 127, 217, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("Sistema de Búsqueda");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Eras Bold ITC", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Entrada");
		modelo.addColumn("Fecha Salida");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		tbReservas.setAutoCreateRowSorter(true);
		sorterReservas = new TableRowSorter<>(modelo);
		tbReservas.setRowSorter(sorterReservas);
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		tbHuespedes.setAutoCreateRowSorter(true);
		sorterHuespedes = new TableRowSorter<>(modeloHuesped);
		tbHuespedes.setRowSorter(sorterHuespedes);
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		cargaReservas();
		cargaHuespedes();
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
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
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		// Botón Regresar
		JButton btnRegresar = new JButton("<");
		btnRegresar.setBorderPainted(false);
		btnRegresar.setFocusable(false);
		btnRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegresar.setBackground(new Color(12, 138, 199));
				btnRegresar.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnRegresar.setBackground(Color.white);
				btnRegresar.setForeground(Color.black);
			}
		});
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario(usr);
				usuario.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setFont(new Font("Eras Medium ITC", Font.PLAIN, 23));
		btnRegresar.setBackground(new Color(255, 255, 255));
		btnRegresar.setBounds(0, 0, 53, 36);
		header.add(btnRegresar);
		// Botón Salir
		JButton btnSalir = new JButton("X");
		btnSalir.setBorderPainted(false);
		btnSalir.setFocusable(false);
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSalir.setBackground(Color.red);
				btnSalir.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnSalir.setBackground(Color.white);
				btnSalir.setForeground(Color.black);
			}
		});
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}
		});
		btnSalir.setFont(new Font("Eras Medium ITC", Font.PLAIN, 18));
		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.setBounds(857, 0, 53, 36);
		header.add(btnSalir);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		// Botón Buscar
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBorderPainted(false);
		btnBuscar.setFocusable(false);
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBuscar.setBackground(new Color(0, 156, 223));
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnBuscar.setBackground(SystemColor.textHighlight);
			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (panel.getSelectedIndex()) {
					case 0:
						buscar(sorterReservas);
						break;
					case 1:
						buscar(sorterHuespedes);
						break;
				}
			}
		});
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setFont(new Font("Eras Medium ITC", Font.PLAIN, 18));
		btnBuscar.setBackground(new Color(12, 138, 199));
		btnBuscar.setBounds(763, 125, 122, 35);
		contentPane.add(btnBuscar);
		// Botón Editar
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBorderPainted(false);
		btnEditar.setFocusable(false);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(0, 156, 223));
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(SystemColor.textHighlight);
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (panel.getSelectedIndex()) {
					case 0:
						editarReserva();
						break;
					case 1:
						editarHuesped();
						break;
				}
			}
		});
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setFont(new Font("Eras Medium ITC", Font.PLAIN, 18));
		btnEditar.setBounds(631, 508, 122, 35);
		contentPane.add(btnEditar);
		// Botón Eliminar
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBorderPainted(false);
		btnEliminar.setFocusable(false);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEliminar.setBackground(new Color(0, 156, 223));
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnEliminar.setBackground(SystemColor.textHighlight);
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (panel.getSelectedIndex()) {
					case 0:
						eliminarReserva();
						break;
					case 1:
						eliminarHuesped();
						break;
				}
			}
		});
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new Font("Eras Medium ITC", Font.PLAIN, 18));
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(763, 508, 122, 35);
		contentPane.add(btnEliminar);
		setResizable(false);
	}
	// Cargar Reservas
	private void cargaReservas() {
		List<Reserva> reservas = this.reservaController.listar();
		reservas.forEach(reserva -> modelo.addRow(new Object[] {reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
				reserva.getValor(), reserva.getFormaPago()}));
	}
	// Cargar Huespedes
	private void cargaHuespedes() {
		List<Huesped> huespedes = this.huespedController.listar();
		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] {huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFechaNacimiento(),
				huesped.getNacionalidad(), huesped.getTelefono(), huesped.getReservaId()}));
	}
	// Busqueda
	private void buscar(TableRowSorter<DefaultTableModel> tabla) {
		tabla.setRowFilter(RowFilter.regexFilter(txtBuscar.getText()));
	}
	// Verificar si tiene una fila elegida
	private boolean filaElegida(JTable tabla) {
		return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
	}
	// Editar Reserva
	private void editarReserva() {
		if (filaElegida(tbReservas)) {
			Mensaje mensaje = new Mensaje("Elije un item", 1);
			mensaje.setVisible(true);
		}
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresent(fila -> {
			Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			String fechaE = modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString();
			String fechaS = modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString();
			String valor = modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString();
			String formaPago = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();
			
			int filasModificadas = this.reservaController.modificar(java.sql.Date.valueOf(fechaE), java.sql.Date.valueOf(fechaS), new BigDecimal(valor), formaPago, id);
			Mensaje mensaje = new Mensaje(String.format("%d item editado con Éxito", filasModificadas), 1);
			mensaje.setVisible(true);
		});
	}
	// Editar Huesped
	private void editarHuesped() {
		if (filaElegida(tbHuespedes)) {
			Mensaje mensaje = new Mensaje("Elije un item", 1);
			mensaje.setVisible(true);
		}
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresent(fila -> {
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
			String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
			String fechaN = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();
			String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
			String telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
			Integer reservaId = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
			
			int filasModificadas = this.huespedController.modificar(nombre, apellido, java.sql.Date.valueOf(fechaN), nacionalidad, telefono, reservaId, id);
			Mensaje mensaje = new Mensaje(String.format("%d item editado con Éxito", filasModificadas), 1);
			mensaje.setVisible(true);
		});
	}
	// Eliminar Reserva
	private void eliminarReserva() {
		if (filaElegida(tbReservas)) {
			Mensaje mensaje = new Mensaje("Elije un item", 1);
			mensaje.setVisible(true);
		}
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresent(fila -> {
			Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					
			int cantidadEliminada = this.reservaController.eliminar(id);
			modelo.removeRow(tbReservas.getSelectedRow());
			Mensaje mensaje = new Mensaje(String.format("%d item eliminado con Éxito", cantidadEliminada), 1);
			mensaje.setVisible(true);
		});
	}
	// Eliminar Huesped
	private void eliminarHuesped() {
		if (filaElegida(tbHuespedes)) {
			Mensaje mensaje = new Mensaje("Elije un item", 1);
			mensaje.setVisible(true);
		}
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresent(fila -> {
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						
			int cantidadEliminada = this.huespedController.eliminar(id);
			modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
			Mensaje mensaje = new Mensaje(String.format("%d item eliminado con Éxito", cantidadEliminada), 1);
			mensaje.setVisible(true);
		});
	}
	// Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
