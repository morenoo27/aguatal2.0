package formularios;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import controladores.ControladorDispensadora;
import controladores.ControladorPedido;
import controladores.ControladorSuscripcion;
import controladores.ControladorUsuario;
import entidades.Pedido;
import entidades.Suscripcion;
import entidades.Usuario;

@SuppressWarnings("serial")
public class VistaAdmin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtBienvenidoDeNuevo;
	private JScrollPane panelUsuarios;
	private JScrollPane panelSuscripciones;
	private JScrollPane panelPedidos;
	private JTable tableUsuarios;
	private JTable tableSuscripciones;
	private JTable tablePedidos;
	private JLabel lblUsuarios;
	private JLabel lblSuscripciones;
	private JLabel lblPedidos;
	private JLabel lblLogo;
	private JTextField textEncabezado;
	private JTextField txtSuscripcionesEnEl;
	private JTextField txtPedidosenElSistema;
	private JButton btnSistema;
	private JButton btnTODO;

	private static ControladorUsuario cu = new ControladorUsuario();
	private static ControladorPedido cp = new ControladorPedido();
	private static ControladorSuscripcion cs = new ControladorSuscripcion();
	private static ControladorDispensadora cd = new ControladorDispensadora();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAdmin frame = new VistaAdmin();
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
	public VistaAdmin() {
		setResizable(false);
//		Mensaje para asegurar la salida de la aplkicacion
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				// Se pide una confirmaci�n antes de finalizar el programa
				int option = JOptionPane.showConfirmDialog(null, "�Est�s seguro de que quieres cerrar sesion?",
						"Confirmaci�n de cierre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		setTitle("AGUATAL CORPORATION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 658);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtBienvenidoDeNuevo = new JTextField();
		txtBienvenidoDeNuevo.setText(" Bienvenido de nuevo admin");
		txtBienvenidoDeNuevo.setBounds(85, 12, 168, 23);
		txtBienvenidoDeNuevo.setBorder(null);
		txtBienvenidoDeNuevo.setEditable(false);
		txtBienvenidoDeNuevo.setColumns(10);
		contentPane.add(txtBienvenidoDeNuevo);

		textEncabezado = new JTextField();
		textEncabezado.setText("  Usuarios en el sistema:\r\n");
		textEncabezado.setBackground(Color.WHITE);
		textEncabezado.setColumns(10);
		textEncabezado.setBorder(null);
		textEncabezado.setBounds(12, 88, 514, 20);
		textEncabezado.setBorder(null);
		textEncabezado.setEditable(false);
		contentPane.add(textEncabezado);

		txtSuscripcionesEnEl = new JTextField();
		txtSuscripcionesEnEl.setText("  Suscripciones en el sistema:\r\n");
		txtSuscripcionesEnEl.setEditable(false);
		txtSuscripcionesEnEl.setColumns(10);
		txtSuscripcionesEnEl.setBorder(null);
		txtSuscripcionesEnEl.setBackground(Color.WHITE);
		txtSuscripcionesEnEl.setBounds(12, 279, 514, 20);
		contentPane.add(txtSuscripcionesEnEl);

		txtPedidosenElSistema = new JTextField();
		txtPedidosenElSistema.setText("  Pedidos en el sistema:\r\n");
		txtPedidosenElSistema.setEditable(false);
		txtPedidosenElSistema.setColumns(10);
		txtPedidosenElSistema.setBorder(null);
		txtPedidosenElSistema.setBackground(Color.WHITE);
		txtPedidosenElSistema.setBounds(12, 470, 514, 20);
		contentPane.add(txtPedidosenElSistema);

		panelUsuarios = new JScrollPane();
		panelUsuarios.setBounds(12, 119, 514, 149);
		contentPane.add(panelUsuarios);

		tableUsuarios = tablaClientes();
		tableUsuarios.setColumnSelectionAllowed(true);
		tableUsuarios.addColumn(new TableColumn(NORMAL));
		panelUsuarios.setViewportView(tableUsuarios);

		panelSuscripciones = new JScrollPane();
		panelSuscripciones.setBounds(12, 310, 514, 149);
		contentPane.add(panelSuscripciones);

		tableSuscripciones = tablaSuscripciones();
		panelSuscripciones.setViewportView(tableSuscripciones);

		panelPedidos = new JScrollPane();
		panelPedidos.setBounds(12, 497, 514, 122);
		contentPane.add(panelPedidos);

		tablePedidos = tablaPedidos();
		panelPedidos.setViewportView(tablePedidos);

		lblUsuarios = new JLabel();
		lblUsuarios.setBackground(Color.WHITE);
		lblUsuarios.setBounds(12, 88, 514, 180);
		lblUsuarios.setOpaque(true);
		lblUsuarios.setVisible(true);
		contentPane.add(lblUsuarios);

		lblSuscripciones = new JLabel();
		lblSuscripciones.setBackground(Color.WHITE);
		lblSuscripciones.setBounds(12, 279, 514, 180);
		lblSuscripciones.setOpaque(true);
		contentPane.add(lblSuscripciones);

		lblPedidos = new JLabel();
		lblPedidos.setOpaque(true);
		lblPedidos.setBackground(Color.WHITE);
		lblPedidos.setBounds(12, 470, 514, 149);
		contentPane.add(lblPedidos);

		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(VistaUsuario.class.getResource("/imagenes/logo.png")));
		lblLogo.setBounds(12, 12, 58, 36);
		contentPane.add(lblLogo);

		btnSistema = new JButton("Actualizar sistema");
		btnSistema.setBounds(12, 54, 213, 23);
		btnSistema.addActionListener(this);
		contentPane.add(btnSistema);

		JButton btnNewButton = new JButton("Crear usuario");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(235, 54, 139, 23);
		contentPane.add(btnNewButton);

		btnTODO = new JButton("ELIMINAR SISTEMA");
		btnTODO.setForeground(Color.RED);
		btnTODO.setBounds(370, 11, 168, 23);
		btnTODO.addActionListener(this);
		contentPane.add(btnTODO);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton boton = (JButton) e.getSource();

		switch (boton.getText()) {
		case "Actualizar sistema":
			VistaAdmin nuevaVista = new VistaAdmin();
			setVisible(false);
			nuevaVista.setVisible(true);
			break;
		case "Crear usuario":
			Registro r = new Registro();
			r.setVisible(true);
			r.registrarse.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					r.setVisible(false);
				}
			});
			break;
		case "ELIMINAR SISTEMA":

			// Se pide una confirmaci�n antes de finalizar el programa
			int option = JOptionPane.showConfirmDialog(null,
					"�Est�s seguro de que quieres eliminar TODO el sistema?" + "\nNo habra vuelta atras",
					"Confirmaci�n de borrado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				System.out.println("Se han eliminado:\n"
						+ cu.deleteAll() + " usuarios\n"
						+ cs.deleteAll() + " suscripciones\n"
						+ cp.deleteAll() + " pedidos\n"
						+ cd.deleteAll() + " dispensadoras\n");
				
				
				
				
			}

			break;

		}

	}

	private JTable tablaPedidos() {
		String[] cabecera = { "Numero de pedido", "Usuario", "Dispensadora" };

		ArrayList<Pedido> pedidos = new ArrayList<>();

		pedidos = (ArrayList<Pedido>) cp.findAll().stream().collect(Collectors.toCollection(ArrayList::new));

		if (pedidos.isEmpty()) {
			Object[][] matriz = new Object[1][3];

			matriz[0][0] = "-";
			matriz[0][1] = "-";
			matriz[0][2] = "-";

			return new JTable(matriz, cabecera);
		}

		Object[][] matriz = new Object[pedidos.size()][3];

		for (int i = 0; i < matriz.length; i++) {
			matriz[i][0] = pedidos.get(i).getCodPedido();

			try {
				matriz[i][1] = pedidos.get(i).getUsuario().getCodUsuario();
			} catch (NullPointerException e) {
				matriz[i][1] = "NULL";
			}

			try {
				matriz[i][2] = pedidos.get(i).getDispensadoras().getCodDispensadora();
			} catch (NullPointerException e) {
				matriz[i][2] = "NULL";
			}
		}
		return new JTable(matriz, cabecera);
	}

	private JTable tablaSuscripciones() {
		String[] cabecera = { "Numero de suscripcion", "Precio", "Usuario" };

		ArrayList<Suscripcion> suscripciones = new ArrayList<>();

		suscripciones = (ArrayList<Suscripcion>) cs.findAll().stream().collect(Collectors.toCollection(ArrayList::new));

		if (suscripciones.isEmpty()) {
			Object[][] matriz = new Object[1][3];

			matriz[0][0] = "-";
			matriz[0][1] = "-";
			matriz[0][2] = "-";

			return new JTable(matriz, cabecera);
		}

		Object[][] matriz = new Object[suscripciones.size()][3];

		for (int i = 0; i < matriz.length; i++) {
			matriz[i][0] = suscripciones.get(i).getCodSuscripcion();
			matriz[i][1] = suscripciones.get(i).getPrecioMensual();
			try {
				matriz[i][2] = suscripciones.get(i).getUsuario().getCodUsuario();
			} catch (NullPointerException e) {
				matriz[i][2] = "NULL";
			}
		}

		return new JTable(matriz, cabecera);
	}

	private JTable tablaClientes() {
		String[] cabecera = { "Numero de usurio", "Usuario", "Contrasenia", "Nombre", "Apellidos", "Direccion",
				"Email" };

		ArrayList<Usuario> usuarios = new ArrayList<>();

		usuarios = (ArrayList<Usuario>) cu.findAll().stream().collect(Collectors.toCollection(ArrayList::new));

		if (usuarios.isEmpty()) {
			Object[][] matriz = new Object[1][7];

			matriz[0][0] = "-";
			matriz[0][1] = "-";
			matriz[0][2] = "-";
			matriz[0][3] = "-";
			matriz[0][4] = "-";
			matriz[0][5] = "-";
			matriz[0][6] = "-";

			return new JTable(matriz, cabecera);
		}

		Object[][] matriz = new Object[usuarios.size()][7];

		for (int i = 0; i < matriz.length; i++) {
			matriz[i][0] = usuarios.get(i).getCodUsuario();
			matriz[i][1] = usuarios.get(i).getUsuario();
			matriz[i][2] = usuarios.get(i).getPass();
			matriz[i][3] = usuarios.get(i).getNombreUsuario();
			matriz[i][4] = usuarios.get(i).getApellidosUsuario();
			matriz[i][5] = usuarios.get(i).getDireccion();
			matriz[i][6] = usuarios.get(i).getEmail();
		}

		return new JTable(matriz, cabecera);
	}
}
