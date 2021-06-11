package formularios;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import controladores.ControladorDispensadora;
import controladores.ControladorPedido;
import controladores.ControladorUsuario;
import entidades.Dispensadora;
import entidades.Pedido;
import entidades.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VistaUsuario extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private Usuario sesionUsuario;

	private static ControladorPedido cp = new ControladorPedido();
	private static ControladorDispensadora cd = new ControladorDispensadora();
	private static ControladorUsuario cu = new ControladorUsuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControladorUsuario cu = new ControladorUsuario();
					VistaUsuario frame = new VistaUsuario(cu.findByPK(1));
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

		setTitle("AGUATAL CORPORATION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 624);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane panelPedidos = new JScrollPane();
		panelPedidos.setBounds(36, 181, 350, 122);
		contentPane.add(panelPedidos);

		table = generarTabla(sesionUsuario);
		table.setColumnSelectionAllowed(true);
		table.addColumn(new TableColumn(NORMAL));

		panelPedidos.setViewportView(table);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(36, 123, 350, 180);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setVisible(true);

		JButton btnNPedido = new JButton("Solicitar pedido");
		btnNPedido.setBounds(215, 147, 142, 23);
		btnNPedido.addActionListener(this);
		contentPane.add(btnNPedido);

		JButton btnActualizarPedido = new JButton("Actualizar pedidos");
		btnActualizarPedido.setBounds(49, 147, 142, 23);
		btnActualizarPedido.addActionListener(this);
		contentPane.add(btnActualizarPedido);

		textField = new JTextField();
		textField.setText("  Pedidos solicitados por usted:");
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBounds(36, 123, 176, 20);
		contentPane.add(textField);
		contentPane.add(lblNewLabel);

		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(VistaUsuario.class.getResource("/imagenes/logo.png")));
		lblLogo.setBounds(12, 12, 58, 36);
		contentPane.add(lblLogo);

//		JOptionPane.showMessageDialog(null, usuario.getCodUsuario());

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton boton = (JButton) e.getSource();

		switch (boton.getText()) {
		case "Actualizar pedidos":
			table = generarTabla(sesionUsuario);
			break;

		case "Solicitar pedido":
			newPedido();
			break;
		}
	}

	public JTable generarTabla(Usuario usuario) {

		String[] cabecera = { "Numero de pedido", "Usuario", "Dispensadora" };

		ArrayList<Pedido> pedidos = new ArrayList<>();

		pedidos = (ArrayList<Pedido>) cp.findByUser(usuario.getCodUsuario());

		Object[][] matriz = new Object[pedidos.size()][3];

		for (int i = 0; i < matriz.length; i++) {
			matriz[i][0] = pedidos.get(i).getCodPedido();
			matriz[i][1] = pedidos.get(i).getUsuario().getCodUsuario();
			matriz[i][2] = pedidos.get(i).getDispensadoras().getCodDispensadora();
		}

		return new JTable(matriz, cabecera);
	}

	private void newPedido() {

		Dispensadora dispensadoraPedido = new Dispensadora();
		Pedido nuevoPedido = new Pedido();

		String[] opciones = { "Grande", "Mediana", "Pequeña" };

		dispensadoraPedido.setTamanio((String) JOptionPane.showInputDialog(null, "Seleccione tamaño", "NUEVO PEDIDO", 0,
				new ImageIcon("/imagenes/logo.png"), opciones, 1));
		
		nuevoPedido.setDispensadoras(dispensadoraPedido);
		nuevoPedido.setUsuario(cu.findByPK(sesionUsuario.getCodUsuario()));
		
		cd.insertDis(dispensadoraPedido);
		
		cp.insertPed(nuevoPedido);
	}
}
