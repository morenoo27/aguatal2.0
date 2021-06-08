package formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import controladores.ControladorUsuario;
import entidades.Usuario;

import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JRadioButton;

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
	private JButton ayudaPass;
	private JTextField campUser;
	private JTextField campPass;
	private JTextField campNombre;
	private JTextField campApellidos;
	private JTextField campDireccion;
	private JLabel labelUsaurio;
	private JTextField txtEmail;
	private JTextField campEmail;
	private JButton registrarse;
	private JTextField txtSeleccionaUnPlan;

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
		txtMensajeBienbenidaEquipo.setBounds(0, 0, 457, 20);
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

		campPass = new JTextField();
		campPass.setBackground(Color.LIGHT_GRAY);
		campPass.setColumns(10);
		campPass.setBounds(160, 173, 107, 20);
		contentPane.add(campPass);

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
		contentPane.add(precio1);
		
		JRadioButton precio2 = new JRadioButton("Plan premium");
		precio2.setBounds(160, 392, 109, 23);
		contentPane.add(precio2);
		
		JRadioButton precio3 = new JRadioButton("Plan Nova");
		precio3.setBounds(294, 392, 109, 23);
		contentPane.add(precio3);
		
		txtSeleccionaUnPlan = new JTextField();
		txtSeleccionaUnPlan.setText("      SELECCIONA UN PLAN");
		txtSeleccionaUnPlan.setEditable(false);
		txtSeleccionaUnPlan.setColumns(10);
		txtSeleccionaUnPlan.setBackground(Color.WHITE);
		txtSeleccionaUnPlan.setBounds(140, 365, 143, 20);
		contentPane.add(txtSeleccionaUnPlan);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton boton = (JButton) e.getSource();

		switch (boton.getText()) {

		case "?":

			JOptionPane.showMessageDialog(null,
					"El usaurio debe ser unico, por lo tanto para asegurarnos de que el usuario que ha elegido es unico \n debe pulsar en comprobar para asegurarse de que es unico\n"
					+ "Una vez el usuario sea unico saldra un tick verde de confirmacion\n\n"
					+ "Ademas, para generar la contraseña, debe saber que cuanto ams larga mas segura sera");

			break;
			
		case "Comprobar":
			
			labelUsaurio.setVisible(true);
			
			if (comprobar()) {
				
				labelUsaurio.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/1tickVerde.jpg")));
				registrarse.setEnabled(true);
				
			} else {
				
				labelUsaurio.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/1error.png")));
				
			}
			
			break;
			
		case "Registrarse":
			
			//crear el; usaurio en la base de datos
			
			ControladorUsuario cu = new ControladorUsuario();
			
			Usuario nuevoUsuario = new Usuario();
			
			nuevoUsuario.setUsuario(campUser.getText());
			nuevoUsuario.setPass(campPass.getText());
			nuevoUsuario.setNombreUsuario(campNombre.getText());
			nuevoUsuario.setApellidosUsuario(campApellidos.getText());
			nuevoUsuario.setDireccion(campDireccion.getText());
			nuevoUsuario.setEmail(campEmail.getText());
			
			cu.insertUser(nuevoUsuario);
			
			
			JOptionPane.showMessageDialog(null, "Se ha creado y registrado tu usuario correctamente");
			
			break;

		}

		

	}

	private boolean comprobar() {
		
		ControladorUsuario cu = new ControladorUsuario();
		
		try {
			return cu.findAll().stream().noneMatch(usuario -> usuario.getUsuario().equals(campUser.getText()));
		}catch (NullPointerException e) {
			return false;
		}
	}
}
