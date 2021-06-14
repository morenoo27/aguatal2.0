package formularios;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import controladores.ControladorDispensadora;
import controladores.ControladorPedido;
import controladores.ControladorSuscripcion;
import controladores.ControladorUsuario;
import entidades.Dispensadora;
import entidades.Pedido;
import entidades.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VistaUsuario extends JFrame implements ActionListener {

	private static ControladorPedido cp = new ControladorPedido();
	private static ControladorDispensadora cd = new ControladorDispensadora();
	private static ControladorUsuario cu = new ControladorUsuario();
	private static ControladorSuscripcion cs = new ControladorSuscripcion();

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField txtBienvenidoDeNuevo;
	private JTextField txtSus;
	private JButton btnActualizarUsuario;
	private Usuario sesionUsuario;
	private JButton btnMostrarDatos;
	private JScrollPane panelPedidos;
	private JLabel lblPedidos;
	private JButton btnNPedido;
	private JButton btnActualizarPedido;
	private JLabel lblLogo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControladorUsuario cu = new ControladorUsuario();
					VistaUsuario frame = new VistaUsuario(cu.findByPK(2));
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
	public VistaUsuario(Usuario usuario) {

		this.sesionUsuario = usuario;

		initComponents();
	}

	private void initComponents() {
//		Mensaje para asegurar la salida de la aplicacion
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				// Se pide una confirmaci�n antes de finalizar el programa
				int option = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres cerrar sesion?",
						"Confirmacion de cierre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else if (option == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null, "Cierre de sesion cancelado");
				}
			}
		});

		setTitle("AGUATAL CORPORATION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 399);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtBienvenidoDeNuevo = new JTextField();
		String nombre = " Bienvenido de nuevo " + sesionUsuario.getNombreUsuario() + ",";
		txtBienvenidoDeNuevo.setText(nombre);
		txtBienvenidoDeNuevo.setBounds(85, 12, 277, 23);
		txtBienvenidoDeNuevo.setBorder(null);
		txtBienvenidoDeNuevo.setEditable(false);
		txtBienvenidoDeNuevo.setColumns(10);
		contentPane.add(txtBienvenidoDeNuevo);

		panelPedidos = new JScrollPane();
		panelPedidos.setBounds(12, 216, 350, 122);
		contentPane.add(panelPedidos);

		table = generarTabla();
		table.setColumnSelectionAllowed(true);
		table.addColumn(new TableColumn(NORMAL));

		panelPedidos.setViewportView(table);

		lblPedidos = new JLabel("");
		lblPedidos.setBackground(Color.WHITE);
		lblPedidos.setBounds(12, 158, 350, 180);
		lblPedidos.setOpaque(true);
		lblPedidos.setVisible(true);

		btnNPedido = new JButton("Solicitar pedido");
		btnNPedido.setBounds(191, 182, 142, 23);
		btnNPedido.addActionListener(this);
		contentPane.add(btnNPedido);

		btnActualizarPedido = new JButton("Actualizar pedidos");
		btnActualizarPedido.setBounds(25, 182, 142, 23);
		btnActualizarPedido.addActionListener(this);
		contentPane.add(btnActualizarPedido);

		textField = new JTextField();
		textField.setText("  Pedidos solicitados por usted:");
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBounds(12, 158, 176, 20);
		textField.setBorder(null);
		textField.setEditable(false);
		contentPane.add(textField);
		contentPane.add(lblPedidos);

		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(VistaUsuario.class.getResource("/imagenes/logo.png")));
		lblLogo.setBounds(12, 12, 58, 36);
		contentPane.add(lblLogo);

		txtSus = new JTextField();
		String suscripcion = " Suscripcion: " + cs.findByUserPK(sesionUsuario.getCodUsuario()).getCodSuscripcion() + " -> "
				+ cs.findByUserPK(sesionUsuario.getCodUsuario()).getPrecioMensual();
		txtSus.setText(suscripcion);
		txtSus.setColumns(10);
		txtSus.setBounds(85, 35, 277, 23);
		txtSus.setBorder(null);
		txtSus.setEditable(false);
		contentPane.add(txtSus);

		btnActualizarUsuario = new JButton("Actaulizar datos");
		btnActualizarUsuario.setBounds(372, 11, 154, 23);
		btnActualizarUsuario.addActionListener(this);
		contentPane.add(btnActualizarUsuario);

		btnMostrarDatos = new JButton("Mostrar datos");
		btnMostrarDatos.setBounds(372, 45, 154, 23);
		btnMostrarDatos.addActionListener(this);
		contentPane.add(btnMostrarDatos);
		
		JButton btnCancelarSuscripcion = new JButton("Cancelar suscripcion");
		btnCancelarSuscripcion.setBounds(372, 80, 154, 23);
		btnCancelarSuscripcion.addActionListener(this);
		contentPane.add(btnCancelarSuscripcion);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton boton = (JButton) e.getSource();

		switch (boton.getText()) {
		case "Actualizar pedidos":
			VistaUsuario nuevaVista = new VistaUsuario(sesionUsuario);
			setVisible(false);
			nuevaVista.setVisible(true);
			break;

		case "Solicitar pedido":
			newPedido();
			break;
		case "Actaulizar datos":
			ActualizarUsuario au = new ActualizarUsuario(sesionUsuario);
			setVisible(false);
			au.setVisible(true);
			break;
		case "Mostrar datos":
			JOptionPane.showMessageDialog(null, sesionUsuario.toString());
			break;
		case "Cancelar suscripcion":
			int option = JOptionPane.showConfirmDialog(null,
					"¿Estas seguro de que quieres cancelar tu suscripcion y borrar esta cuenta?",
					"Confirmacion de eliminacion de cuenta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				cs.deleteSus(cs.findByUserPK(sesionUsuario.getCodUsuario()));
				cu.deleteUser(sesionUsuario);
				setVisible(false);
				Principal p = new Principal();
				p.setVisible(true);
			}
			break;
		}
	}

	/**
	 * Metodo que genera una tabla coin la informacion necesaria de cada pedido
	 * que ha realizado un uusario
	 * 
	 * @param usuario Objeto usuario con el que estamos trabajando en la ventana
	 * @return Tab;la con todos los pedidos realizados por ese usaurio en cuestion
	 */
	public JTable generarTabla() {

		String[] cabecera = { "Numero de pedido", "Usuario", "Dispensadora" };

		ArrayList<Pedido> pedidos = new ArrayList<>();

		pedidos = (ArrayList<Pedido>) cp.findByUser(sesionUsuario.getCodUsuario());

		Object[][] matriz = new Object[pedidos.size()][3];

		for (int i = 0; i < matriz.length; i++) {
			matriz[i][0] = pedidos.get(i).getCodPedido();
			matriz[i][1] = pedidos.get(i).getUsuario().getCodUsuario();
			matriz[i][2] = pedidos.get(i).getDispensadoras().getCodDispensadora();
		}

		return new JTable(matriz, cabecera);
	}

	/**
	 * Metodo que gestiona el registro de un nuevo pedido, generado [por el usuario
	 * de la ventana actual
	 */
	private void newPedido() {

		Dispensadora dispensadoraPedido = new Dispensadora();
		Pedido nuevoPedido = new Pedido();

		String[] opciones = { "Grande", "Mediana" };

		dispensadoraPedido.setTamanio((String) JOptionPane.showInputDialog(null, "Seleccione tama�o", "NUEVO PEDIDO", 0,
				new ImageIcon("/imagenes/logo.png"), opciones, 1));
		
		nuevoPedido.setDispensadoras(dispensadoraPedido);
		nuevoPedido.setUsuario(sesionUsuario);
		
		cp.insertPed(nuevoPedido);
		//CHAPUZA
//		COJO ESE PEDIDO
		Pedido pedidoCorregido = cp.findByPK(cp.findAll().size());
//		LO MODIFICO METIENDOLE LA DISPENSADORA Y EL CLIENTE
		pedidoCorregido.setDispensadoras(cd.findByPK(cd.findAll().size()));
		pedidoCorregido.setUsuario(sesionUsuario);
		
		cp.modifyPed(pedidoCorregido);
	}
}
