package formularios;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controladores.ControladorUsuario;
import entidades.Usuario;

@SuppressWarnings("serial")
public class ActualizarUsuario extends JFrame implements ActionListener {

	private static ControladorUsuario cu = new ControladorUsuario();

	private JPanel contentPane;
	private JTextField txtMensajeBienbenidaEquipo;
	private JTextField textusaurio;
	private JTextField txtContrasea;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textDireccion;
	private JButton comprobar;
	private JButton ayudaUsuario;
	private JTextField campUser;
	private JTextField campNombre;
	private JTextField campApellidos;
	private JTextField campDireccion;
	private JLabel labelUsaurio;
	private JTextField txtEmail;
	private JTextField campEmail;
	private JButton registrarse;
	private JPasswordField passwordField;

	private Usuario sesionUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActualizarUsuario frame = new ActualizarUsuario(cu.findByPK(1));
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
	public ActualizarUsuario(Usuario usaurio) {

		sesionUsuario = usaurio;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(SystemColor.activeCaption);
		panelCentral.setBounds(10, 36, 440, 65);
		contentPane.add(panelCentral);
		panelCentral.setLayout(null);

		txtMensajeBienbenidaEquipo = new JTextField();
		txtMensajeBienbenidaEquipo.setBackground(SystemColor.activeCaption);
		txtMensajeBienbenidaEquipo.setEditable(false);
		txtMensajeBienbenidaEquipo.setText("mensaje bienvenida equipo directivo");
		txtMensajeBienbenidaEquipo.setBounds(0, 0, 440, 20);
		panelCentral.add(txtMensajeBienbenidaEquipo);
		txtMensajeBienbenidaEquipo.setColumns(10);

		JButton botonVolver = new JButton("Cancelar");
		botonVolver.addActionListener(this);
		botonVolver.setBounds(10, 11, 91, 20);
		contentPane.add(botonVolver);

		textusaurio = new JTextField();
		textusaurio.setBackground(Color.WHITE);
		textusaurio.setEditable(false);
		textusaurio.setText("           Usuario:");
		textusaurio.setBounds(64, 142, 86, 20);
		contentPane.add(textusaurio);
		textusaurio.setColumns(10);

		txtContrasea = new JTextField();
		txtContrasea.setBackground(Color.WHITE);
		txtContrasea.setEditable(false);
		txtContrasea.setText("    Contrase\u00F1a:");
		txtContrasea.setBounds(64, 173, 86, 20);
		contentPane.add(txtContrasea);
		txtContrasea.setColumns(10);

		textNombre = new JTextField();
		textNombre.setBackground(Color.WHITE);
		textNombre.setText("          Nombre:");
		textNombre.setEditable(false);
		textNombre.setBounds(64, 204, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		textApellidos = new JTextField();
		textApellidos.setBackground(Color.WHITE);
		textApellidos.setText("        Apellidos:");
		textApellidos.setEditable(false);
		textApellidos.setBounds(64, 235, 86, 20);
		contentPane.add(textApellidos);
		textApellidos.setColumns(10);

		textDireccion = new JTextField();
		textDireccion.setBackground(Color.WHITE);
		textDireccion.setEditable(false);
		textDireccion.setText("        Direccion:");
		textDireccion.setBounds(64, 266, 86, 20);
		contentPane.add(textDireccion);
		textDireccion.setColumns(10);

		comprobar = new JButton("Comprobar");
		comprobar.setBounds(277, 142, 118, 20);
		comprobar.addActionListener(this);
		contentPane.add(comprobar);

		ayudaUsuario = new JButton("?");
		ayudaUsuario.addActionListener(this);
		ayudaUsuario.setBounds(11, 141, 47, 23);
		contentPane.add(ayudaUsuario);

		campUser = new JTextField();
		campUser.setBackground(Color.LIGHT_GRAY);
		campUser.setColumns(10);
		campUser.setBounds(160, 142, 107, 20);
		contentPane.add(campUser);

		campNombre = new JTextField();
		campNombre.setBackground(Color.LIGHT_GRAY);
		campNombre.setColumns(10);
		campNombre.setBounds(160, 204, 107, 20);
		contentPane.add(campNombre);

		campApellidos = new JTextField();
		campApellidos.setBackground(Color.LIGHT_GRAY);
		campApellidos.setColumns(10);
		campApellidos.setBounds(160, 235, 107, 20);
		contentPane.add(campApellidos);

		campDireccion = new JTextField();
		campDireccion.setBackground(Color.LIGHT_GRAY);
		campDireccion.setColumns(10);
		campDireccion.setBounds(160, 266, 107, 20);
		contentPane.add(campDireccion);

		labelUsaurio = new JLabel("");
		labelUsaurio.setVisible(false);
		labelUsaurio.setBounds(405, 130, 26, 39);
		contentPane.add(labelUsaurio);

		txtEmail = new JTextField();
		txtEmail.setText("              Email:");
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(64, 297, 86, 20);
		contentPane.add(txtEmail);

		campEmail = new JTextField();
		campEmail.setColumns(10);
		campEmail.setBackground(Color.LIGHT_GRAY);
		campEmail.setBounds(160, 297, 107, 20);
		contentPane.add(campEmail);

		registrarse = new JButton("Actualizar");
		registrarse.setBounds(160, 339, 107, 23);
		registrarse.setEnabled(true);
		registrarse.addActionListener(this);
		contentPane.add(registrarse);

		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(160, 173, 107, 20);
		contentPane.add(passwordField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		JButton boton = (JButton)  e.getSource();

		switch (boton.getText()) {

		case "?":
			JOptionPane.showMessageDialog(null,
					"El usaurio debe ser unico, por lo tanto para asegurarnos de que el usuario que ha elegido es unico \n debe pulsar en comprobar para asegurarse de que es unico\n"
							+ "Una vez el usuario sea unico saldra un tick verde de confirmacion\n\n"
							+ "Ademas, para generar la contraseï¿½a, debe saber que cuanto mas larga, mas segura sera");
			break;
		case "Comprobar":
//			comprobacion
			botonComprobar();
			break;
		case "Actualizar":
			actualizarUsuaio();
			break;
		case "Cancelar":
			VistaUsuario vu = new VistaUsuario(sesionUsuario);
			setVisible(false);
			vu.setVisible(true);
			break;
		}

	}

	//PREGUNTA POR QUE SE ME PONE TODO A ""
	private void actualizarUsuaio() {


		// PREGUNTAR
		if (!(campUser.equals(null))) {
			sesionUsuario.setUsuario(campUser.getText());
		}

		if (!(passwordField.equals(null) || passwordField.equals(""))) {
			sesionUsuario.setPass(new String(passwordField.getPassword()));// String construido a partir de un char
		}

		if (!(campNombre.equals(null) || campNombre.equals(""))) {
			sesionUsuario.setNombreUsuario(campNombre.getText());
		}

		if (!(campApellidos.equals(null) || campApellidos.equals(""))) {
			sesionUsuario.setApellidosUsuario(campApellidos.getText());
		}

		if (!(campDireccion.equals(null) || campDireccion.equals(""))) {
			sesionUsuario.setDireccion(campDireccion.getText());
		}

		if (!(campEmail.equals(null) || campEmail.equals(""))) {
			sesionUsuario.setEmail(campEmail.getText());
		}

		cu.modifyuser(sesionUsuario);

		if (cu.findByUsuario(sesionUsuario.getUsuario()).getUsuario().equals(sesionUsuario.getUsuario())) {

			JOptionPane.showMessageDialog(null, "Tu usuario se ha actualizado correctamente");
			
			setVisible(false);
			VistaUsuario p = new VistaUsuario(sesionUsuario);
			p.setVisible(true);
		}
	}

	/**
	 * Metodo que se ejecuta cuando se pulsa el boton con texto "Comprobar"
	 * 
	 * <p>
	 * Lo que realiza es una comprobacion para que no exista ninguna coincidencia
	 * con el nuevo usuario que se va a introducir en el sistema
	 */
	private void botonComprobar() {

//		mostramos un label donde saldra el mensaje de aprobacion
		labelUsaurio.setVisible(true);
		if (comprobar()) {
//			ponemos tick verde(aprobamos usuario)
			labelUsaurio.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/1tickVerde.jpg")));
//			activamos boton registrarse
			registrarse.setEnabled(true);
		} else {
//			ponemos criz roja error (denegamos usuario)
			labelUsaurio.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/1error.png")));
			registrarse.setEnabled(false);
		}
	}

	/**
	 * Metodo que realiza un checkeo de cada uno de los usuarios ya insertados en la
	 * base de datos para asegurarse de que el usuario que se va a crear es
	 * completamente nuevo
	 * 
	 * <p>
	 * Como el usuario debe ser unico esto debe ser estrictamente true
	 * 
	 * <p>
	 * Al encontrar coincidencia con uno de los usuarios ya introducidos en el
	 * sistema, salta una excepcion NullPointer
	 * 
	 * @return true (no encuentra coincidencias) | false(existe coincidencia)
	 */
	private boolean comprobar() {

//		Creamos controlador
		ControladorUsuario cu = new ControladorUsuario();

//		Salta un nullpointer exception cuando matcheacon algun usuario ¿Explicacion?

		try {
//			Busqueda
			return cu.findAll().stream().noneMatch(usuario -> usuario.getUsuario().equals(campUser.getText()));
		} catch (NullPointerException e) {
			return false;
		}
	}
}
