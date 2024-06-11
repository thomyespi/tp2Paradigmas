package tpCriptomonedas;

public class App {
	public static void main(String[] args) {

		GestorSistema sistema = new GestorSistema();

		sistema.iniciarSesion();

		System.out.println("\nEl programa finalizo bien. Gracias por participar");

	}
}
