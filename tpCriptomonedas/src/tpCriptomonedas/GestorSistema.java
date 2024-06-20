package tpCriptomonedas;

import java.io.IOException;
import java.util.Scanner;

public class GestorSistema {

	private GestorCripto gestor;
	private Scanner scanner;
	private GestorUsuarios gestorUsuarios;

	public GestorSistema() {
		this.gestor = new GestorCripto();
		this.gestorUsuarios = new GestorUsuarios();
		this.scanner = new Scanner(System.in);
	}

	public void iniciarSesion() throws IOException {

		System.out.println("Ingrese su nombre de usuario:");
		String nombreUsuario = scanner.nextLine();
		Usuario usuario = gestorUsuarios.buscarUsuario(nombreUsuario);

		if (usuario != null) {
			if (usuario instanceof Administrador) {
				System.out.println("¡Bienvenido, administrador " + nombreUsuario + "!\n");
				mostrarMenuAdmin();
				
			} else {
				System.out.println("¡Bienvenido, " + nombreUsuario + "!");
				mostrarMenuTrader(usuario);
			}
		} else {
			System.out.println("El usuario no existe. ¿Desea registrarse como trader? (S/N)");
			String respuesta = scanner.nextLine().toUpperCase();

			if (respuesta.equals("S")) {
				gestorUsuarios.registrarTrader(nombreUsuario);
				usuario = gestorUsuarios.buscarUsuario(nombreUsuario);

				System.out.println("\n¡Usuario registrado como Trader!");
				System.out.println("\n¡Bienvenido, " + nombreUsuario + "!");
				mostrarMenuTrader(usuario);
			} else {
				System.out.println("Muchas gracias.");
				System.exit(0);
			}
		}
	}
	

	private void mostrarMenuTrader(Usuario usuario) {
		int opcion;

		do {
			System.out.println("================== MENU TRADER =================");
			System.out.println("=========================================\n");

			System.out.println("[1] Comprar Criptomonedas");
			System.out.println("[2] Vender Criptomonedas");
			System.out.println("[3] Consultar Criptomoneda");
			System.out.println("[4] Recomendar Criptomonedas");
			System.out.println("[5] Consultar estado actual del mercado");
			System.out.println("[6] Visualizar archivo de transacciones (histórico)");
			System.out.println("[0] Salir");
			System.out.println("\nSeleccionar Opcion: ");
			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				opcion = -1;
			}

			switch (opcion) {
			case 1:
			//	this.mostrarCriptomonedas();
			//	break;
			case 2:
			//	this.mostrarMercados();
			//	break;
			case 3:
			//	this.agregarCripto();
			//	break;
			case 4:
			//	this.modificarNombreCripto();
			//	break;
			case 5:
			//	this.modificarPrecioCripto();
			//	break;
			case 6:
			//	this.eliminarCripto();
			//	break;
			case 0:
				System.out.println("Programa terminado");
				break;
			default:
				System.out.println("La opcion elegida no es Valida!!\n");
			}
		} while (opcion != 0);

		scanner.close();
	}

	private void mostrarMenuAdmin() {
		int opcion;

		do {
			System.out.println("================== MENU ADMINISTRADOR =================");
			System.out.println("=========================================\n");

			System.out.println("[1] Mostrar todas las Criptomonedas");
			System.out.println("[2] Mostrar todos los Mercados");
			System.out.println("[3] Agregar Criptomoneda");
			System.out.println("[4] Modificar Nombre Criptomoneda");
			System.out.println("[5] Modificar Precio Criptomoneda");
			System.out.println("[6] Eliminar Criptomoneda");
			System.out.println("[0] Salir");
			System.out.println("\nSeleccionar Opcion: ");
			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				opcion = -1;
			}

			switch (opcion) {
			case 1:
				this.mostrarCriptomonedas();
				break;
			case 2:
				this.mostrarMercados();
				break;
			case 3:
				this.agregarCripto();
				break;
			case 4:
				this.modificarNombreCripto();
				break;
			case 5:
				this.modificarPrecioCripto();
				break;
			case 6:
				this.eliminarCripto();
				break;
			case 0:
				System.out.println("Programa terminado");
				break;
			default:
				System.out.println("La opcion elegida no es Valida!!\n");
			}
		} while (opcion != 0);

		scanner.close();
	}

	private void mostrarCriptomonedas() {
		System.out.println("================= [1] TODAS LAS CRIPTOMONEDAS ================\n");
		System.out.println(this.gestor.getCriptomonedas());
	}

	private void mostrarMercados() {
		System.out.println("================= [2] TODOS LOS MERCADOS ================\n");
		System.out.println(this.gestor.getMercados());
	}

	private void agregarCripto() {
		String simbolo;
		String nombre;
		double precio;

		System.out.println("================= [3] AGREGAR CRIPTOMONEDA ================\n");
		System.out.println("Simbolo: ");
		simbolo = scanner.nextLine();

		if (gestor.buscarCriptomoneda(simbolo.toUpperCase())) {
			System.out.println("La Criptomoneda con el símbolo '" + simbolo + "' ya existe!\n");
			return;
		}

		System.out.println("Nombre:");
		nombre = scanner.nextLine();
		System.out.println("Valor: ");
		try {
			precio = Double.parseDouble(scanner.nextLine());
			if (precio < 0) {
				System.out.println("El precio no puede ser negativo!\n");
				return;
			}
		} catch (Exception e) {
			System.out.println("Valor no valido!\n");
			return;
		}

		Criptomoneda nuevaCripto = new Criptomoneda(nombre, simbolo.toUpperCase(), precio);
		try {
			this.gestor.agregarCriptomoneda(nuevaCripto);
			System.out.println("\nSe agrego correctamente\n");
		} catch (Exception e) {
			System.out.println("Ocurrió un error al agregar la Criptomoneda!\\n");
		}
	}

	private void modificarNombreCripto() {
		System.out.println("================= [4] MODIFICAR NOMBRE CRIPTOMONEDA ================\n");
		System.out.println("Simbolo: ");
		String simbolo = scanner.nextLine();

		if (!gestor.buscarCriptomoneda(simbolo.toUpperCase())) {
			System.out.println("La Criptomoneda con el símbolo '" + simbolo + "' NO existe!\n");
			return;
		}

		System.out.println("Nuevo Nombre:");
		String nuevoNombre = scanner.nextLine();
		try {
			this.gestor.modificarNombreCriptomoneda(simbolo.toUpperCase(), nuevoNombre);
			System.out.println("\nNombre modificado correctamente\n");
		} catch (Exception e) {
			System.out.println("Ocurrio un error!\n");
		}
	}

	private void modificarPrecioCripto() {
		System.out.println("================= [5] MODIFICAR PRECIO CRIPTOMONEDA ================\n");
		System.out.println("Simbolo: ");
		String simbolo = scanner.nextLine();

		if (!gestor.buscarCriptomoneda(simbolo.toUpperCase())) {
			System.out.println("La Criptomoneda con el símbolo '" + simbolo + "' NO existe!\n");
			return;
		}

		System.out.println("Nuevo Precio:");
		double nuevoPrecio;
		try {
			nuevoPrecio = Double.parseDouble(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("Valor no valido!\n");
			return;
		}
		try {
			this.gestor.modificarPrecioCriptomoneda(simbolo.toUpperCase(), nuevoPrecio);
			System.out.println("Precio modificado correctamente\n");
		} catch (Exception e) {
			System.out.println("Ocurrio un error!\n");
		}
	}

	private void eliminarCripto() {
		int opcion;
		String simbolo;

		do {
			System.out.println("================= [6] ELIMINAR CRIPTOMONEDA ================\n");
			System.out.println("[1] Ingresar el simbolo del elemento");
			System.out.println("[0] Volver atras");
			System.out.println("Elegir una opcion: ");

			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				opcion = -1;
			}

			switch (opcion) {
			case 1:
				System.out.println("Simbolo: ");
				simbolo = scanner.nextLine();

				if (!gestor.buscarCriptomoneda(simbolo.toUpperCase())) {
					System.out.println("La Criptomoneda con el símbolo '" + simbolo + "' NO existe!\n");
					return;
				}

				System.out.println("¿Estas seguro que queres eliminar la criptomoneda '" + simbolo + "'?");
				System.out.println("[1] SI\n[2] NO");
				int confirmar;

				try {
					confirmar = Integer.parseInt(scanner.nextLine());
				} catch (Exception e) {
					confirmar = -1;
				}

				if (confirmar != 1) {
					System.out.println("Cancelaste la accion!\n");
					break;
				}

				try {
					this.gestor.eliminarCriptomoneda(simbolo.toUpperCase());
					System.out.println("Cripto eliminada correctamente\n");
				} catch (Exception e) {
					System.out.println("La Cripto no se ha podido eliminar\n");
				}
				break;
			case 0:
				break;
			default:
				System.out.println("La opcion elegida no es valida!!\n");
			}
		} while (opcion != 0);
	}
}
