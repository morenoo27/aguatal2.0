package formularios;

import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controladores.ControladorPedido;
import controladores.ControladorUsuario;
import entidades.Pedido;
import entidades.Usuario;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class VistaUsuario extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControladorUsuario cu = new ControladorUsuario();
					VistaUsuario frame = new VistaUsuario(cu.findByUsuario("moreno27"));
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

		setTitle("AGUATAL CORPORATION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 624);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.darkShadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = generarTabla(usuario);
		table.setColumnSelectionAllowed(true);
		table.addColumn(new TableColumn(NORMAL));
		table.setBounds(38, 53, 190, 275);
		contentPane.add(table);

		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(VistaUsuario.class.getResource("/imagenes/logo.png")));
		lblLogo.setBounds(12, 12, 58, 36);
		contentPane.add(lblLogo);

		JOptionPane.showMessageDialog(null, usuario.getCodUsuario());

	}

	public JTable generarTabla(Usuario usuario) {

		ControladorPedido cp = new ControladorPedido();
		
		
		String[] cabecera = { "Numero de pedido", "Usuario", "Dispensadora" };
		
		ArrayList<Pedido> pedidos = new ArrayList<>();

		pedidos = (ArrayList<Pedido>) cp.findByUser(usuario.getCodUsuario());

		Object[][] matriz = new Object[pedidos.size()][3];
		
		for (int i = 0; i < matriz.length; i++) {
			matriz[i][0] = pedidos.get(i).getCodPedido(); 
			matriz[i][1] = pedidos.get(i).getUsuario();
			matriz[i][2] = pedidos.get(i).getDispensadoras();
		}
		
		DefaultTableModel tablaModelo = new DefaultTableModel(matriz, cabecera);

		return tablaModelo;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
