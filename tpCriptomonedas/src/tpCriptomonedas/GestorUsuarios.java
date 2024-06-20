package tpCriptomonedas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class GestorUsuarios {

	private List<Usuario> usuarios;
	private Scanner scanner;

	public GestorUsuarios() {

		try {
			this.usuarios = Archivo.leerArchivoUsuarios();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.scanner = new Scanner(System.in);
	}

	public Usuario buscarUsuario(String nombreUsuario) {
		for (Usuario usuario : usuarios) {
			if (usuario.getNombre().equals(nombreUsuario)) {
				return usuario;
			}
		}
		return null;
	}

	public void registrarTrader(String nombreUsuario) throws IOException {

		System.out.println("\nIngrese su numero de cuenta bancaria:");
		String numeroCuentaBancaria = scanner.nextLine();

		System.out.println("\nIngrese el nombre de su banco:");
		String nombreBanco = scanner.nextLine();

		System.out.println("\nIngrese el saldo de su cuenta bancaria:");
		Double saldoCuenta = Double.parseDouble(scanner.nextLine());

		Trader trader = new Trader(nombreUsuario, numeroCuentaBancaria, nombreBanco, saldoCuenta);

		this.usuarios.add(trader);

		actualizarArchivoUsuarios();
	}

	private synchronized void actualizarArchivoUsuarios() throws IOException {

		FileWriter fileWriter = new FileWriter("archivos/in/Usuarios.in");
		PrintWriter printWriter = new PrintWriter(fileWriter);

		for (Usuario usuario : usuarios) {
			if (usuario instanceof Trader) {
				Trader trader = (Trader) usuario;
				printWriter.println(trader.getNombre() + "|" + trader.getNumeroCuentaBancaria() + "|"
						+ trader.getNombreBanco() + "|" + trader.getSaldoActual());
			} else if (usuario instanceof Administrador) {
				Administrador administrador = (Administrador) usuario;
				printWriter.println(administrador.getNombre() + "|" + administrador.getPerfil());
			}
		}

		printWriter.close();
	}
}