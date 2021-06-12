package formularios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

@SuppressWarnings("serial")
public class VistaAdmin extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtBienvenidoDeNuevo;
	private JScrollPane panelPedidos;
	private JTable table;
	private JLabel lblPedidos;
	private JButton btnNPedido;
	private JButton btnActualizarPedido;
	private JTextField textField;
	private JLabel lblLogo;

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
//		Mensaje para asegurar la salida de la aplkicacion
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				// Se pide una confirmación antes de finalizar el programa
				int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres cerrar sesion?",
						"Confirmación de cierre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
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
		String nombre = " Bienvenido de nuevo admin,";
		txtBienvenidoDeNuevo.setText(nombre);
		txtBienvenidoDeNuevo.setBounds(85, 12, 277, 23);
		txtBienvenidoDeNuevo.setBorder(null);
		txtBienvenidoDeNuevo.setEditable(false);
		txtBienvenidoDeNuevo.setColumns(10);
		contentPane.add(txtBienvenidoDeNuevo);

		panelPedidos = new JScrollPane();
		panelPedidos.setBounds(12, 216, 350, 122);
		contentPane.add(panelPedidos);

		table = new JTable();
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
