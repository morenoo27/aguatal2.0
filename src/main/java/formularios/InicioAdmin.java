package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class InicioAdmin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField campoUsuario;
	private JTextField campoPass;
	private JTextField txtUsuario;
	private JTextField txtContrasea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioAdmin frame = new InicioAdmin();
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
	public InicioAdmin() {

		setTitle("AGUATAL CORPORATION");

		initComponents();
	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setBounds(220, 136, 238, 144);
		contentPane.add(panelCentral);
		panelCentral.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.GRAY);
		separator.setBounds(10, 45, 266, 0);
		panelCentral.add(separator);

		campoUsuario = new JTextField();
		campoUsuario.setForeground(Color.BLACK);
		campoUsuario.setBackground(Color.LIGHT_GRAY);
		campoUsuario.setBounds(106, 26, 127, 20);
		campoUsuario.setBorder(null);
		panelCentral.add(campoUsuario);
		campoUsuario.setColumns(10);

		campoPass = new JTextField();
		campoPass.setForeground(Color.BLACK);
		campoPass.setBackground(Color.LIGHT_GRAY);
		campoPass.setBounds(106, 68, 127, 20);
		campoPass.setBorder(null);
		panelCentral.add(campoPass);
		campoPass.setColumns(10);

		JButton iniciarSesion = new JButton("Entrar");
		iniciarSesion.setBounds(68, 110, 120, 23);
		iniciarSesion.addActionListener(this);
		panelCentral.add(iniciarSesion);

		txtUsuario = new JTextField();
		txtUsuario.setBackground(Color.WHITE);
		txtUsuario.setText("           Usuario:");
		txtUsuario.setBounds(26, 26, 70, 20);
		txtUsuario.setEditable(false);
		txtUsuario.setBorder(null);
		panelCentral.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtContrasea = new JTextField();
		txtContrasea.setBackground(Color.WHITE);
		txtContrasea.setText("           Contrase\u00F1a:");
		txtContrasea.setBounds(10, 68, 86, 20);
		txtContrasea.setEditable(false);
		txtContrasea.setBorder(null);
		panelCentral.add(txtContrasea);
		txtContrasea.setColumns(10);

		JButton botonVolver = new JButton("<");
		botonVolver.addActionListener(this);
		botonVolver.setBounds(10, 407, 48, 23);
		botonVolver.addActionListener(this);
		contentPane.add(botonVolver);

		JLabel fondo = new JLabel();
		fondo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/fondoYLogoAguatal.jpg")));
		fondo.setBounds(0, 0, 676, 441);
		contentPane.add(fondo);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton boton = (JButton) e.getSource();

		switch (boton.getText()) {

		case "Entrar":
			if (comprobarAdmin()) {
				VistaAdmin va = new VistaAdmin();
				setVisible(false);
				va.setVisible(true);
			} else {
				// mensaje "intentelo de nuevo"
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos\nIntentelo de nuevo");
			}
			break;

		case "<":
			Principal prin = new Principal();
			setVisible(false);
			prin.setVisible(true);
			break;
		}
	}

	/**
	 * Metodo que comprueba que elk inicio de sesion con las credenciales
	 * introducidas en los campos de texto existen en la base de datos
	 * 
	 * @return true(coinciden credenciales) | false(coinciden credenciales)
	 */
	private boolean comprobarAdmin() {
		
		String condicion = "usuario";
		return campoUsuario.getText().equals(condicion) && campoPass.getText().equals(condicion);
	}

}
