package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {

		RedesController rdc = new RedesController();
		String os_name = System.getProperty("os.name");

		int operador;
		operador = 0;

		try {

			while (operador != 9) {

				operador = Integer.parseInt(JOptionPane.showInputDialog("Menu \n " 
				+ "Escolha a Operação que deseja realizar: \n"
				+ "1) Mostrar Adaptadores IPv4 \n" 
				+ "2) Exibir Média de Ping do Sistema"));

				switch (operador) {

				case 1:
					rdc.ip(os_name);
					break;
					
					
				case 2:
					
					rdc.ping(os_name);
					break;
				}

			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Operação Abortada" , "Atenção" , JOptionPane.WARNING_MESSAGE);
		}

	}

}
