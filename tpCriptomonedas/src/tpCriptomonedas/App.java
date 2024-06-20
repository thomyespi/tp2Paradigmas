package tpCriptomonedas;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException {

		GestorSistema sistema = new GestorSistema();

		sistema.iniciarSesion();

		System.out.println("\nEl programa finalizo bien. Gracias por participar");

	}
}
