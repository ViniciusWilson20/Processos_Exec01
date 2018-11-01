package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

public class RedesController {

	StringBuffer buffer = new StringBuffer();
	String[] vet_cmd;
	String[] vet_term;
	String filtro_cmd, filtro_term;

	public void filtra_IP(String command) {

		/*byte[] var = linha.getBytes();  
		String var2 = new String(var, "UTF-8"); */
		
		filtro_cmd = "Ethernet VirtualBox Wi-Fi IPv4";
		filtro_term = "flags inet";
		vet_cmd = filtro_cmd.split(" ");
		vet_term = filtro_term.split(" ");
		
		try {

			Process pcs = Runtime.getRuntime().exec(command);
			InputStream fluxo = pcs.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo, "UTF-8");
			BufferedReader read = new BufferedReader(leitor);

			String linha = "";
			
			
			while (linha != null) {

				for (String filtro_palavra : vet_cmd) {

					if (linha.contains(filtro_palavra)) {		
						System.out.println(linha);
						linha = read.readLine();
					}
				}

				for (String filtro_palavra : vet_term) {

					if (linha.contains(filtro_palavra)) {
						System.out.println(linha);
						linha = read.readLine();
					}
				}

				linha = read.readLine();
			}


		} catch (IOException e) {

			String erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro, "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void filtra_Ping(String command, String os_name) {

		try {

			Process pcs = Runtime.getRuntime().exec(command);
			InputStream fluxo = pcs.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo, "UTF-8");
			BufferedReader read = new BufferedReader(leitor);
			String linha = read.readLine();
			while (linha != null) {

				if (os_name.contains("Windows")) {
					if (linha.contains("dia")) {

						int ini = linha.lastIndexOf("=");
						int fim = linha.lastIndexOf("ms");
						linha.substring(ini, fim);
						System.out.println(linha);
					}
				}
				if (os_name.contains("Linux")) {

					if (linha.contains("mdev")) {
						System.out.println(linha);
					}
				}
				linha = read.readLine();

			}

		} catch (IOException e) {

			String erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro, "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public void ip(String os_name) {

		String command;
		System.out.println(os_name);

		if (os_name.contains("Windows")) {

			command = "IPCONFIG";
			filtra_IP(command);

		}
		if (os_name.contains("Linux")) {

			command = "ifconfig";
			filtra_IP(command);
		}

	}

	public void ping(String os_name) {

		String command;
		if (os_name.contains("Windows")) {
			command = "ping -n 10 www.google.com.br";
			filtra_Ping(command, os_name);
		}
		if (os_name.contains("Linux")) {

			command = "ping -c 10 www.google.com.br";
			filtra_Ping(command, os_name);
		}

	}

}
