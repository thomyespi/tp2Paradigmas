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

		System.out.println("\n\nIngrese su nombre de usuario:");
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
				iniciarSesion();
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
			System.out.println("[7] Obtener datos de cuenta");
			System.out.println("[8] Ingresar con otro usuario");
			System.out.println("[0] Salir");
			System.out.println("\nSeleccionar Opcion: ");
			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				opcion = -1;
			}

			switch (opcion) {
			case 1:
				this.comprarCriptomonedas(usuario.getNombre());
				break;
			case 2:
				this.venderCriptomonedas(usuario.getNombre());
				break;
			case 3:
				this.mostrarUnaCripto();
				break;
			case 4:
				this.recomendarCripto();
				break;
			case 5:
				this.mostrarMercados("5");
				break;
			case 6:
				this.visualizarHistorico(usuario.getNombre());
				break;
			case 7:
				this.obtenerDatosCuenta(usuario.getNombre());
				break;
			case 8:
				this.ingresarConOtroUsuario();
				;
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

	private void comprarCriptomonedas(String usuario) {

		System.out.println("================= [1] COMPRAR CRIPTOMONEDA ================\n");

		System.out.println("Ingresa el Simbolo: ");
		String simbolo = scanner.nextLine();

		if (!gestor.buscarCriptomoneda(simbolo.toUpperCase())) {
			System.out.println("La Criptomoneda con el símbolo '" + simbolo + "' NO existe!\n");
			return;
		}

		this.gestor.mostrarUnaCripto(simbolo.toUpperCase());

		System.out.println("\nIngresa el monto a comprar: ");
		double monto = Double.parseDouble(scanner.nextLine());

		System.out.println("\nEsta seguro que quiere realizar la compra? (S/N)");
		String respuesta = scanner.nextLine().toUpperCase();

		if (respuesta.equals("S")) {

			if (!this.gestorUsuarios.validarSaldoUsuario(usuario, monto)) {
				System.out.println("Saldo insuficiente. Ingrese el dinero faltante en su cuenta bancaria.");
				 scanner.nextLine();
				return;
			}
			try {
				this.gestorUsuarios.actualizarSaldoUsuario(usuario, monto, "compra");
				this.gestor.comprarCripto(simbolo.toUpperCase(), monto);
				this.gestorUsuarios.registrarCompra(usuario, simbolo.toUpperCase(), monto);
			} catch (Exception e) {
				System.out.println("Ocurrio un error");
			}

			System.out.println("\nLa compra fue realizada con exito");
			scanner.nextLine();

		} else
			return;

	}

	private void venderCriptomonedas(String usuario) {

		System.out.println("================= [2] VENDER CRIPTO ================\n");

		System.out.println("Ingresa el Simbolo de la Cripto a vender: ");
		String simbolo = scanner.nextLine();

		if (!gestor.buscarCriptomoneda(simbolo.toUpperCase())) {
			System.out.println("La Criptomoneda con el símbolo '" + simbolo.toUpperCase() + "' NO existe!\n");
			return;
		}

		if (!gestorUsuarios.validarExistenciaCriptoHistorico(usuario, simbolo.toUpperCase())) {
			System.out.println("No posee la Criptomoneda con el símbolo  " + simbolo + "\n");
			 scanner.nextLine();

			return;
		}

		double cantidadMaxima = gestorUsuarios.obtenerCantidadMaxima(usuario, simbolo.toUpperCase());

		if (cantidadMaxima == 0) {
			System.out.println("No tienes suficientes criptomonedas para vender.\n");
			 scanner.nextLine();

			return;
		}

		System.out.println("La cantidad máxima que puedes vender es: " + cantidadMaxima);
		System.out.println("\nIngresa la cantidad a vender: ");
		double cantidad = Double.parseDouble(scanner.nextLine());

		if (cantidad > cantidadMaxima) {
			System.out.println(
					"Cantidad ingresada superior a la cantidad máxima que puedes vender. Operación cancelada.\n");
			 scanner.nextLine();
			return;
		}

		System.out.println("\nEstá seguro que quiere realizar la venta? (S/N)");
		String respuesta = scanner.nextLine().toUpperCase();

		if (respuesta.equals("S")) {
			try {
				this.gestorUsuarios.actualizarSaldoUsuario(usuario, cantidad, "venta");
				this.gestor.venderCripto(simbolo.toUpperCase(), cantidad);
				this.gestorUsuarios.registrarVenta(usuario, simbolo.toUpperCase(), cantidad);
				System.out.println("\nLa venta fue realizada con éxito");
				 scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Ocurrió un error durante la venta.");
				e.printStackTrace();
			}

		} else {
			System.out.println("Operación cancelada.");
		}

	}

	private void recomendarCripto() {

		System.out.println("================= [4] RECOMENDAR CRIPTO ================\n");

		Mercado mercado = null;

		try {
			mercado = this.gestor.recomendarCompra();

			System.out.println("Luego de realizar una evaluacion estadistica se indica que: \n");
			System.out.println("La Cripto recomendada es:" + mercado);
			 scanner.nextLine();

		} catch (IOException e) {
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}

	}

	private void visualizarHistorico(String usuario) {

		System.out.println("================= [6] VISUALIZAR HISTORICO ================\n");

		int opcion;

		System.out.println("\nSeleccione el criterio de ordenamiento:");
		System.out.println("1. Alfabéticamente por símbolo");
		System.out.println("2. Por cantidad en modo descendente");

		opcion = Integer.parseInt(scanner.nextLine());

		if (opcion > 2 || opcion < 1) {
			return;
		}

		try {
			this.gestorUsuarios.consultarHistorico(usuario, opcion);
			 scanner.nextLine();
		} catch (IOException e) {
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}

	}

	private void obtenerDatosCuenta(String id) {

		System.out.println("================= [7] OBTENER DATOS CUENTA ================\n");
		this.gestorUsuarios.obtenerDatosCuenta(id);
		 scanner.nextLine();
	}

	private void mostrarMenuAdmin() {
		int opcion;

		do {
			System.out.println("================== MENU ADMINISTRADOR =================");
			System.out.println("=========================================\n");

			System.out.println("[1] Mostrar todas las Criptomonedas");
			System.out.println("[2] Mostrar el Mercado");
			System.out.println("[3] Consultar una criptomoneda");
			System.out.println("[4] Agregar Criptomoneda");
			System.out.println("[5] Modificar Nombre Criptomoneda");
			System.out.println("[6] Modificar Simbolo Criptomoneda");
			System.out.println("[7] Modificar Precio Criptomoneda");
			System.out.println("[8] Eliminar Criptomoneda");
			System.out.println("[9] Ingresar con otro usuario");
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
				this.mostrarMercados("2");
				break;
			case 3:
				this.mostrarUnaCripto();
				break;
			case 4:
				this.agregarCripto();
				break;
			case 5:
				this.modificarNombreCripto();
				break;
			case 6:
				this.modificarSimboloCripto();
				break;
			case 7:
				this.modificarPrecioCripto();
				break;
			case 8:
				this.eliminarCripto();
				break;
			case 9:
				this.ingresarConOtroUsuario();
				;
				break;
			case 0:
				System.out.println("Programa terminado");
				break;
			default:
				System.out.println("La opcion elegida no es Valida!!\n");
				 scanner.nextLine();
			}
		} while (opcion != 0);

		scanner.close();
	}

	private void mostrarCriptomonedas() {
		System.out.println("================= [1] TODAS LAS CRIPTOMONEDAS ================\n");
		System.out.println(this.gestor.getCriptomonedas());
		 scanner.nextLine();
	}

	private void mostrarMercados(String numero) {
		System.out.println("================= [" + numero + "] MERCADO ================\n");
		System.out.println(this.gestor.getMercados());
		 scanner.nextLine();
	}

	private void mostrarUnaCripto() {
		System.out.println("================= [3] MOSTRAR UNA CRIPTOMONEDA ================\n");

		System.out.println("Ingresa el Simbolo: ");
		String simbolo = scanner.nextLine();

		if (!gestor.buscarCriptomoneda(simbolo.toUpperCase())) {
			System.out.println("La Criptomoneda con el símbolo '" + simbolo + "' NO existe!\n");
			 scanner.nextLine();
			return;
		}

		this.gestor.mostrarUnaCripto(simbolo.toUpperCase());
		 scanner.nextLine();
	}

	private void agregarCripto() {
		String simbolo;
		String nombre;
		double precio;

		System.out.println("================= [4] AGREGAR CRIPTOMONEDA ================\n");
		System.out.println("Simbolo: ");
		simbolo = scanner.nextLine();

		if (gestor.buscarCriptomoneda(simbolo.toUpperCase())) {
			System.out.println("La Criptomoneda con el símbolo '" + simbolo + "' ya existe!\n");

			System.out.println("Desea modificar algun parametro de la misma? S/N ");

			String respuesta = scanner.nextLine().toUpperCase();

			if (respuesta.equals("S")) {

				System.out.println("================= MODIFICAR CRIPTOMONEDA ================\n");
				System.out.println("[1] Modificar Nombre");
				System.out.println("[2] Modificar Precio");
				System.out.println("[3] Modificar Simbolo");
				System.out.println("Elegir una opcion: ");

				int opcion;

				try {
					opcion = Integer.parseInt(scanner.nextLine());
				} catch (Exception e) {
					opcion = -1;
				}

				switch (opcion) {

				case 1:
					modificarNombreCripto();
				case 2:
					modificarPrecioCripto();
				case 3:
					modificarSimboloCripto();
				}
			} else {
				return;
			}
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
		System.out.println("================= [5] MODIFICAR NOMBRE CRIPTOMONEDA ================\n");
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
			System.out.println("\nNombre modificado correctamente!!!\n");
			 scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Ocurrio un error!\n");
		}
	}

	private void modificarSimboloCripto() {
		System.out.println("================= [6] MODIFICAR SIMBOLO CRIPTOMONEDA ================\n");
		System.out.println("Ingrese el Simbolo de la cripto a modificar: ");
		String simbolo = scanner.nextLine();

		if (!gestor.buscarCriptomoneda(simbolo.toUpperCase())) {
			System.out.println("La Criptomoneda con el símbolo '" + simbolo + "' NO existe!\n");
			 scanner.nextLine();
			return;
		}

		System.out.println("Ingrese Nuevo Simbolo:");
		String nuevoSimbolo = scanner.nextLine();
		try {
			this.gestor.modificarSimboloCriptomoneda(simbolo.toUpperCase(), nuevoSimbolo.toUpperCase());
			System.out.println("\nSimbolo modificado correctamente\n");
			 scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Ocurrio un error!\n");
		}
	}

	private void modificarPrecioCripto() {
		System.out.println("================= [7] MODIFICAR PRECIO CRIPTOMONEDA ================\n");
		System.out.println("Simbolo: ");
		String simbolo = scanner.nextLine();

		if (!gestor.buscarCriptomoneda(simbolo.toUpperCase())) {
			System.out.println("La Criptomoneda con el símbolo '" + simbolo + "' NO existe!\n");
			 scanner.nextLine();
			return;
		}
		
		this.gestor.mostrarUnaCripto(simbolo.toUpperCase());

		System.out.println("\n\nNuevo Precio:");
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
			 scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Ocurrio un error!\n");
		}
	}

	private void eliminarCripto() {
		int opcion;
		String simbolo;

		do {
			System.out.println("================= [8] ELIMINAR CRIPTOMONEDA ================\n");
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
					 scanner.nextLine();
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

	private void ingresarConOtroUsuario() {

		try {
			iniciarSesion();
		} catch (IOException e) {
			System.out.println("Error al iniciar sesion");
			e.printStackTrace();
		}

	}
}
