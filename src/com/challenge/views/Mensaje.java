package com.challenge.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Mensaje extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Mensaje dialog = new Mensaje("Mensaje", 1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Mensaje(String mensaje, Integer tipo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mensaje.class.getResource("/imagenes/aH-40px.png")));
		setBounds(100, 100, 440, 258);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setIcon(new ImageIcon(Mensaje.class.getResource("/imagenes/Ha-100px.png")));
			lblNewLabel.setBounds(10, 11, 404, 100);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel(mensaje);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setForeground(new Color (12, 138, 199));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_1.setBounds(10, 122, 404, 21);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnAceptar.setBackground(new Color(0, 156, 223));
					}
				
					@Override
					public void mouseExited(MouseEvent e) {
						btnAceptar.setBackground(SystemColor.textHighlight);
					}
				});
				btnAceptar.setBackground(new Color(12, 138, 199));
				btnAceptar.setForeground(new Color(255, 255, 255));
				btnAceptar.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
				btnAceptar.setBorderPainted(false);
				btnAceptar.setFocusable(false);
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (tipo == 1) {
							dispose();
						} else {
							System.exit(0);
						}
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.setForeground(new Color(255, 255, 255));
			btnCancelar.setFont(new Font("Eras Medium ITC", Font.PLAIN, 16));
			btnCancelar.setBackground(new Color(12, 138, 199));
			btnCancelar.setFocusable(false);
			btnCancelar.setBorderPainted(false);
			if (tipo != 2) {
				btnCancelar.setVisible(false);
			}
			btnCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
					dispose();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					btnCancelar.setBackground(new Color(0, 156, 223));
				}
			
				@Override
				public void mouseExited(MouseEvent e) {
					btnCancelar.setBackground(SystemColor.textHighlight);
				}
			});
			buttonPane.add(btnCancelar);
		}
	}
}
