package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.ControladorSuscripcion;
import controladores.ControladorUsuario;
import entidades.Suscripcion;
import entidades.Usuario;

import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class Registro extends JFrame implements ActionListener {

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
	private JTextField txtSeleccionaUnPlan;
	private ButtonGroup grupoPlan;
	private JPasswordField passwordField;

	private double[] precios;
	private double sus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					Registro frame = new Registro();

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
	public Registro() {

		setTitle("AGUATAL CORPORATION");

		initComponents();
	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 579);
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

		JButton botonVolver = new JButton("<");
		botonVolver.addActionListener(this);
		botonVolver.setBounds(10, 11, 48, 23);
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

		registrarse = new JButton("Registrarse");
		registrarse.setBounds(160, 506, 107, 23);
		registrarse.setEnabled(false);
		registrarse.addActionListener(this);
		contentPane.add(registrarse);

		JRadioButton precio1 = new JRadioButton("Plan basico");
		precio1.setBounds(28, 392, 109, 23);
		precio1.addActionListener(this);
		contentPane.add(precio1);

		JRadioButton precio2 = new JRadioButton("Plan premium");
		precio2.setBounds(160, 392, 109, 23);
		precio2.addActionListener(this);
		contentPane.add(precio2);

		JRadioButton precio3 = new JRadioButton("Plan Nova");
		precio3.setBounds(294, 392, 109, 23);
		precio3.addActionListener(this);
		contentPane.add(precio3);

		txtSeleccionaUnPlan = new JTextField();
		txtSeleccionaUnPlan.setText("      SELECCIONA UN PLAN");
		txtSeleccionaUnPlan.setEditable(false);
		txtSeleccionaUnPlan.setColumns(10);
		txtSeleccionaUnPlan.setBackground(Color.WHITE);
		txtSeleccionaUnPlan.setBounds(140, 365, 143, 20);
		contentPane.add(txtSeleccionaUnPlan);

		grupoPlan = new ButtonGroup();
		grupoPlan.add(precio1);
		grupoPlan.add(precio2);
		grupoPlan.add(precio3);

		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(162, 173, 107, 19);
		contentPane.add(passwordField);

//		instanciamos los precios
		precios = new double[] { 5.99, 7.99, 9.99};
		sus = 0;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();

		if (o instanceof JRadioButton) {

			botonesRadio(o);

		} else if (o instanceof JButton) {

			botones(o);
		}

	}

	private void botonesRadio(Object o) {

		JRadioButton radio = (JRadioButton) o;

		switch (radio.getText()) {
		case "Plan basico":

			sus = precios[0];
			break;
		case "Plan premium":
			sus = precios[1];
			break;
		case "Plan Nova":
			sus = precios[2];
			break;
		}
	}
	
//	JOptionPane.showMessageDialog(null, "");
	
	private void botones(Object o) {

//		casting
		JButton boton = (JButton) o;

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

		case "Registrarse":

			// crear el usaurio en la base de datos
			registrarUsuario();

			break;

		case "<":

			Principal p = new Principal();

			setVisible(false);
			p.setVisible(true);

			break;
		}

	}

	/**
	 * Metdo que realiza el registro de un nuevo usuario en la base de datos
	 * 
	 * <p>
	 * Primero, con el controlador usuario se introduce el usuario con el usuario
	 * insanciado segun los campos de textos de la vista
	 * 
	 * <p>
	 * Acto seguido, con el controlador suscripcion se introduce la suscripcion ligada a ese usuario
	 */
	private void registrarUsuario() {

		ControladorUsuario cu = new ControladorUsuario();
		ControladorSuscripcion cs = new ControladorSuscripcion();

		if (sus != 0) {

			Usuario nuevoUsuario = new Usuario();

			nuevoUsuario.setUsuario(campUser.getText());
			nuevoUsuario.setPass(new String(passwordField.getPassword()));// String construido a partir de un char
			nuevoUsuario.setNombreUsuario(campNombre.getText());
			nuevoUsuario.setApellidosUsuario(campApellidos.getText());
			nuevoUsuario.setDireccion(campDireccion.getText());
			nuevoUsuario.setEmail(campEmail.getText());

			cu.insertUser(nuevoUsuario);

			Suscripcion susUsuario = new Suscripcion();

			susUsuario.setPrecioMensual(sus);
			susUsuario.setUsuario(cu.findByUsuario(nuevoUsuario.getUsuario()));

			cs.insertSus(susUsuario);

			if (cu.findByUsuario(nuevoUsuario.getUsuario()).getUsuario().equals(nuevoUsuario.getUsuario())) {

				JOptionPane.showMessageDialog(null, "Se ha creado y registrado tu usuario correctamente");
				
				setVisible(false);
				Principal p = new Principal();
				p.setVisible(true);
				
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"No has seleccionado un plan de suscriocion\n" + "Por favor, seleccione uno");
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