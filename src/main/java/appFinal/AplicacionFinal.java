package appFinal;

import java.awt.EventQueue;

import formularios.Principal;

public class AplicacionFinal {

	public static void main(String[] args) {

		//INICIAMOS LA VENTANA PRINCIPAL Y AQUI ES DONDE COMIENZA TODO EL PROGRAMA
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal ventanaPrincipal = new Principal();
					ventanaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
