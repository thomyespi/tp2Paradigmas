package tpCriptomonedas;

import java.util.Scanner;

public class GestorSistema {

	private GestorCripto gestor;

	public GestorSistema() {

		this.gestor = new GestorCripto();
	}

	public void mostrarMenu() {

		final Scanner scanner = new Scanner(System.in);
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
			}finally {
				scanner.close();
			}

			switch (opcion) {
			case 1:
				 this.gestor.getCriptomonedas();
				break;
			case 2:
				this.gestor.getMercados();
				break;
			case 3:
				// this.addCryptoMenu();
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

	}
}
