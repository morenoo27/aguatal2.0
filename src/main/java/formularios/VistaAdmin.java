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
	private JTextField textEncabezado;
	private JLabel lblLogo;
	private JButton btnNewButton;
	private JButton btnMostrarTodasLas;
	private JButton btnMostrarTodosLos;

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
		txtBienvenidoDeNuevo.setText(" Bienvenido de nuevo admin");
		txtBienvenidoDeNuevo.setBounds(85, 12, 168, 23);
		txtBienvenidoDeNuevo.setBorder(null);
		txtBienvenidoDeNuevo.setEditable(false);
		txtBienvenidoDeNuevo.setColumns(10);
		contentPane.add(txtBienvenidoDeNuevo);

		panelPedidos = new JScrollPane();
		panelPedidos.setBounds(12, 216, 514, 122);
		contentPane.add(panelPedidos);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.addColumn(new TableColumn(NORMAL));

		panelPedidos.setViewportView(table);

		lblPedidos = new JLabel("");
		lblPedidos.setBackground(Color.WHITE);
		lblPedidos.setBounds(12, 158, 514, 180);
		lblPedidos.setOpaque(true);
		lblPedidos.setVisible(true);

		textEncabezado = new JTextField();
		textEncabezado.setBackground(Color.WHITE);
		textEncabezado.setColumns(10);
		textEncabezado.setBorder(null);
		textEncabezado.setBounds(12, 158, 514, 20);
		textEncabezado.setBorder(null);
		textEncabezado.setEditable(false);
		contentPane.add(textEncabezado);
		contentPane.add(lblPedidos);

		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(VistaUsuario.class.getResource("/imagenes/logo.png")));
		lblLogo.setBounds(12, 12, 58, 36);
		contentPane.add(lblLogo);
		
		btnNewButton = new JButton("Mostrar todos los clientes");
		btnNewButton.setBounds(12, 54, 168, 23);
		contentPane.add(btnNewButton);
		
		btnMostrarTodasLas = new JButton("Mostrar todas las suscripciones");
		btnMostrarTodasLas.setBounds(12, 88, 203, 23);
		contentPane.add(btnMostrarTodasLas);
		
		btnMostrarTodosLos = new JButton("Mostrar todos los pedidos");
		btnMostrarTodosLos.setBounds(12, 122, 168, 23);
		contentPane.add(btnMostrarTodosLos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
