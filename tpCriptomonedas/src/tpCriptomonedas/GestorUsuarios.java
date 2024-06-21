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

	public void actualizarSaldoUsuario(String usuario, double monto, String type) throws IOException {
		for (Usuario user : this.usuarios) {
			if (user.getNombre().equals(usuario)) {
				if (user instanceof Trader) {
					Trader trader = (Trader) user;
					if(type.equals("compra")) {
						trader.restarSaldo(monto);
					}else {
						trader.sumarSaldo(monto);
					}
					break;
				}
			}
		}

		actualizarArchivoUsuarios();
	}

	public boolean validarSaldoUsuario(String usuario, double monto) {
		for (Usuario user : this.usuarios) {
			if (user.getNombre().equals(usuario)) {
				if (user instanceof Trader) {
					Trader trader = (Trader) user;
					return trader.puedeComprar(monto);
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public void registrarCompra(String usuario, String simbolo, double cantidad) throws IOException {

		for (Usuario user : usuarios) {
			if (user.getNombre().equals(usuario)) {
				if (user instanceof Trader) {
					((Trader) user).actualizarHistorico(simbolo, cantidad,"compra");
				}
			}
		}
	}
	
	public void registrarVenta(String nombreUsuario, String simbolo, double cantidad) throws IOException {
		for (Usuario user : usuarios) {
			if (user.getNombre().equals(nombreUsuario)) {
				if (user instanceof Trader) {
				((Trader) user).actualizarHistorico(simbolo, cantidad,"venta");
				}
			}
		}
	}

	public boolean validarExistenciaCriptoHistorico(String usuario, String simbolo) {

		for (Usuario user : usuarios) {
			if (user.getNombre().equals(usuario)) {
				if (user instanceof Trader) {
					boolean rest = ((Trader) user).buscarSimboloHistorico(simbolo.toUpperCase());
					return rest;
				}
			}
		}
		return false;
	}

	public double obtenerCantidadMaxima(String nombreUsuario, String simbolo) {
		for (Usuario user : usuarios) {
			if (user.getNombre().equals(nombreUsuario)) {
				if (user instanceof Trader) {
					Trader trader = (Trader) user;
					return trader.getCantidadCripto(simbolo);
				}
			}
		}
		return 0;
	}

	public void consultarHistorico(String usuario, int ordenamiento) throws IOException {
		
		for (Usuario user : usuarios) {
			if (user.getNombre().equals(usuario)) {
				if (user instanceof Trader) {
					Trader trader = (Trader) user;
					trader.mostrarHistorico(ordenamiento);
				}
			}
		}
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