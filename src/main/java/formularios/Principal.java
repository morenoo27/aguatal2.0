package formularios;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.ControladorUsuario;
import entidades.Usuario;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class Principal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField campoUsuario;
	private JTextField txtUsuario;
	private JTextField txtContrasea;
	private JPasswordField passwordField;
	private JPanel panelCentral;
	private JTextPane mensajeBienvenida;
	private JSeparator separator;
	private JButton iniciarSesion;
	private JButton registrar;
	private JButton botonAdmin;
	private JLabel fondo;

	private Usuario usuario;

	private static ControladorUsuario cu = new ControladorUsuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {

		initComponents();

	}

	private void initComponents() {

		setTitle("AGUATAL CORPORATION");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setBounds(206, 102, 276, 236);
		panelCentral.setLayout(null);
		contentPane.add(panelCentral);

		campoUsuario = new JTextField();
		campoUsuario.setForeground(Color.BLACK);
		campoUsuario.setBackground(Color.LIGHT_GRAY);
		campoUsuario.setBounds(121, 84, 127, 20);
		campoUsuario.setBorder(null);
		campoUsuario.setColumns(10);
		panelCentral.add(campoUsuario);

		mensajeBienvenida = new JTextPane();
		mensajeBienvenida.setBounds(62, 11, 167, 23);
		mensajeBienvenida.setText(" Bienvenido! Inicie Sesion");
		mensajeBienvenida.setEditable(false);
		panelCentral.add(mensajeBienvenida);

		separator = new JSeparator();
		separator.setBounds(10, 41, 251, 2);
		panelCentral.add(separator);

		iniciarSesion = new JButton("Iniciar Sesion");
		iniciarSesion.setBounds(83, 168, 120, 23);
		iniciarSesion.addActionListener(this);
		panelCentral.add(iniciarSesion);

		txtUsuario = new JTextField();
		txtUsuario.setBackground(Color.WHITE);
		txtUsuario.setText("           Usuario:");
		txtUsuario.setBounds(41, 84, 70, 20);
		txtUsuario.setEditable(false);
		txtUsuario.setBorder(null);
		txtUsuario.setColumns(10);
		panelCentral.add(txtUsuario);

		txtContrasea = new JTextField();
		txtContrasea.setBackground(Color.WHITE);
		txtContrasea.setText("           Contrase\u00F1a:");
		txtContrasea.setBounds(25, 126, 86, 20);
		txtContrasea.setEditable(false);
		txtContrasea.setBorder(null);
		txtContrasea.setColumns(10);
		panelCentral.add(txtContrasea);

		registrar = new JButton("\u00BFAun no estas resgistrado?");
		registrar.setBounds(41, 202, 207, 23);
		registrar.addActionListener(this);
		panelCentral.add(registrar);

		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(121, 126, 127, 19);
		passwordField.setBorder(null);
		panelCentral.add(passwordField);

		botonAdmin = new JButton("Acceder modo administrador");
		botonAdmin.setBounds(463, 407, 203, 23);
		botonAdmin.addActionListener(this);
		contentPane.add(botonAdmin);

		fondo = new JLabel();
		fondo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/fondoYLogoAguatal.jpg")));
		fondo.setBounds(0, 0, 676, 441);
		contentPane.add(fondo);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton boton = (JButton) e.getSource();

		switch (boton.getText()) {

		case "\u00BFAun no estas resgistrado?":
			Registro r = new Registro();
			r.setVisible(true);
			break;

		case "Acceder modo administrador":

			InicioAdmin admin = new InicioAdmin();

			setVisible(false);

			admin.setVisible(true);

			break;

		case "Iniciar Sesion":

			if (comprobarUsuario()) {

				usuario = cu.findByUsuario(campoUsuario.getText());
				VistaUsuario v = new VistaUsuario(usuario);
				setVisible(false);
				v.setVisible(true);

			} else {

				// mensaje "intentelo de nuevo"
				JOptionPane.showMessageDialog(null, "Usuario o contrase???a incorrectos\nIntentelo de nuevo");
			}

			break;
		}

	}

	/**
	 * Metodo que comprueba que elk inicio de sesion con las credenciales
	 * introducidas en los campos de texto existen en la base de datos
	 * 
	 * @return true(coinciden credenciales) | false(coinciden credenciales)
	 */
	private boolean comprobarUsuario() {

		try {

//			buscamos el usuario por el nombre de usuario
//			si tienen la misma contrase???a...

			Usuario inicio = cu.findByUsuario(campoUsuario.getText());
			return inicio.getPass().equals(new String(passwordField.getPassword()));

		} catch (NullPointerException e) {
			return false;
		}
	}
}
