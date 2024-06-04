package tpCriptomonedas;

import java.util.Scanner;

public class GestorSistema {

	private GestorCripto gestor;
	private Scanner scanner;

	public GestorSistema() {
		this.gestor = new GestorCripto();
		this.scanner = new Scanner(System.in);
	}

	public void mostrarMenu() {
		int opcion;

		do {
			System.out.println("================== MENU =================");
			System.out.println("=========================================\n");

			System.out.println("[1] Mostrar todas las Criptomonedas");
			System.out.println("[2] Mostrar todos los Mercados");
			System.out.println("[3] Agregar Criptomoneda");
			System.out.println("[4] Modificar Criptomoneda");
			System.out.println("[5] Eliminar Criptomoneda");
			System.out.println("[0] Finalizar Programa");
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
				// this.updateCryptoMenu();
				break;
			case 5:
				// this.removeCryptoMenu();
				break;
			case 0:
				System.out.println("Terminando...");
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
		System.out.println("================= [1] TODOS LOS MERCADOS ================\n");
		System.out.println(this.gestor.getMercados());
	}

	private void agregarCripto() {
		int opcion;
		String simbolo;
		String nombre;
		double precio;

		do {
			System.out.println("================= [3] AGREGAR CRIPTOMONEDA ================\n");
			System.out.println("[1] Agregar criptomoneda");
			System.out.println("[0] Volver atras");
			System.out.println("Elegir opcion: ");

			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				opcion = -1;
			}

			switch (opcion) {
			case 1:
				System.out.println("Simbolo: ");
				simbolo = scanner.nextLine();
				System.out.println("Nombre:");
				nombre = scanner.nextLine();
				System.out.println("Valor: ");

				try {
					precio = Double.parseDouble(scanner.nextLine());
				} catch (Exception e) {
					System.out.println("Valor no valido!\n");
					break;
				}

				Criptomoneda nuevaCripto = new Criptomoneda(nombre, simbolo.toUpperCase(), precio);

				try {
					this.gestor.agregarCriptomoneda(nuevaCripto);
				} catch (Exception e) {
					System.out.println("Ocurrio un error!\n");
				}

				System.out.println(nuevaCripto == null ? "No se pudo Agregar!" : "\nSe agrego correctamente\n");
				break;

			case 0:
				break;

			default:
				System.out.println("Opcion NO valida!!\n");

			}
		} while (opcion != 0);
	}
}
